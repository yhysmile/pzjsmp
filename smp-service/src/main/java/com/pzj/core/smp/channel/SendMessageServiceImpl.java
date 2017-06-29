package com.pzj.core.smp.channel;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pzj.core.smp.channel.business.ChannelRetryManage;
import com.pzj.core.smp.channel.business.ChannelSendResultManage;
import com.pzj.core.smp.channel.business.FilterChannelManage;
import com.pzj.core.smp.channel.business.MessageAccountManage;
import com.pzj.core.smp.channel.business.SendMessageManage;
import com.pzj.core.smp.channel.model.AccountWarnResp;
import com.pzj.core.smp.channel.model.ChannelInfo;
import com.pzj.core.smp.channel.model.ChannelRetryParam;
import com.pzj.core.smp.channel.model.ChannelSendMessage;
import com.pzj.core.smp.channel.model.ParamModel;
import com.pzj.core.smp.channel.model.SendSmsChannelResp;
import com.pzj.core.smp.common.exception.SmpException;
import com.pzj.core.smp.common.exception.SmpExceptionCode;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.message.mail.service.MailSendService;

@Service("sendMessageService")
public class SendMessageServiceImpl implements SendMessageService, AccountWarnService {
	private final Logger logger = LoggerFactory.getLogger(SendMessageServiceImpl.class);

	@Resource(name = "filterChannelManage")
	private FilterChannelManage filterChannelManage;
	@Resource(name = "sendMessageManage")
	private SendMessageManage sendMessageManage;
	@Resource(name = "channelRetryManage")
	private ChannelRetryManage channelRetryManage;
	@Resource(name = "channelSendResultManage")
	private ChannelSendResultManage channelSendResultManage;
	@Resource(name = "messageAccountManage")
	private MessageAccountManage messageAccountManage;
	@Resource(name = "mailSendService")
	private MailSendService mailSendService;

	@Resource(name = "sendMessageValidator")
	private SendMessageValidator sendMessageValidator;

	@Value("${mail.accountWarn}")
	String accountWarnEmail;

	@Override
	public Boolean sendMessage(ChannelSendMessage sendMessage) {
		logger.info("invoke channel send message. request param:{}", JSONConverter.toJson(sendMessage));

		Boolean flag = Boolean.FALSE;
		//校验发送参数
		ParamModel paramModel = sendMessageValidator.convert(sendMessage, null);
		if (!paramModel.paramIsOk()) {
			logger.error("invoke send message fail,illegal param. request:{}", JSONConverter.toJson(sendMessage));
			throw new SmpException(SmpExceptionCode.PARAM_ERROR, paramModel.getParamErrorMsg());
		}

		//处理短信过期
		Long expireDiff = handleMessageTime(sendMessage);
		if (expireDiff >= 0) {
			logger.warn("invoke send message fail,message expire. request param:{}", JSONConverter.toJson(sendMessage));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
			String time = sdf.format(sendMessage.getSendDate());
			sendMessage.setErrTip("短信已过期，已丢弃！短信发送时间：" + time + "；短信过期时间设置为：" + sendMessage.getExpireDuration() + "毫秒。"
					+ "；过期时间为：" + expireDiff + "毫秒。");

			//保存发送记录
			channelSendResultManage.saveSendMessageResult(sendMessage, null, null);

			return flag;
		}

		//获取最优通道
		ChannelInfo channelInfo = null;
		try {
			channelInfo = filterChannelManage.filterChannelByDefault();
		} catch (RuntimeException e) {
			logger.error("send message filter channel error. request param:{}", JSONConverter.toJson(sendMessage), e);

			sendMessage.setErrTip("未获取到可用通道！");
			//保存发送记录
			channelSendResultManage.saveSendMessageResult(sendMessage, null, null);

			if (e instanceof SmpException) {
				throw e;
			}

			throw new SmpException(e);

		}

		//获取通道标识
		Integer channelIdentity = sendMessageManage.getChannelIdentity(channelInfo);

		SendSmsChannelResp sendSmsChannelResp = null;
		Boolean excepFlag = Boolean.FALSE;

		//调用通道进行短信投递
		try {
			sendSmsChannelResp = sendMessageManage.invokeSendMessage(null, sendMessage, channelIdentity);
			if (null != sendSmsChannelResp) {
				excepFlag = sendSmsChannelResp.getRestryFlag();
			}
		} catch (Exception e) {
			logger.error("invoke sms channel send error! request param:{}", JSONConverter.toJson(sendMessage), e);

			sendMessage.setErrTip("调用短信通道发送异常！");
			//将短信发送记录存入数据库
			channelSendResultManage.saveSendMessageResult(sendMessage, sendSmsChannelResp, channelInfo);

			if (e instanceof SmpException) {
				throw (SmpException) e;
			}

			throw new SmpException();
		}

		//检测短信是否发送成功，根据通道返回结果采取重试机制
		if (excepFlag || sendMessageManage.checkGoFailRetry(sendSmsChannelResp, channelIdentity)) {
			ChannelRetryParam channelRetryParam = sendMessageManage.firstInitFailRetryParam(sendMessage,
					sendSmsChannelResp, channelIdentity, channelInfo);
			sendSmsChannelResp = channelRetryManage.channelRestry(channelRetryParam);
			//重试后获取最新的通道
			channelInfo = channelRetryParam.getChannelInfo();
			sendMessage.setSendLinkId(channelRetryParam.getSendLinkId());

			//重新发送后计算短信发生状态
			if (null != sendSmsChannelResp) {
				flag = sendMessageManage.checkSendWheatherSuccess(sendSmsChannelResp, channelIdentity);
			}
		} else {
			flag = Boolean.TRUE;
		}

		//将短信发送记录存入数据库
		channelSendResultManage.saveSendMessageResult(sendMessage, sendSmsChannelResp, channelInfo);

		if (logger.isDebugEnabled()) {
			logger.debug("invoke channel send message success. result:{}", flag);
		}

		return flag;
	}

	/**
	 * 检查短信过期时间
	 * @param sendMessage
	 * @return Long 值大于等于零已过期，值小于零则未过期
	 */
	private Long handleMessageTime(ChannelSendMessage sendMessage) {
		Long expireDiff = -1l;
		if (null != sendMessage.getSendDate() && null != sendMessage.getExpireDuration()) {
			Long expireTime = sendMessage.getSendDate().getTime() + sendMessage.getExpireDuration(); //毫秒数 
			Long curTime = System.currentTimeMillis();
			expireDiff = curTime - expireTime;
		}
		return expireDiff;
	}

	@Override
	public void accountWarn() {
		try {
			String[] mailtoArrays = accountWarnEmail.split(",");
			if (mailtoArrays == null || mailtoArrays.length <= 0) {
				logger.warn("获取短信通道账户告警发送目标邮件为空！");
				return;
			}
			List<AccountWarnResp> accountWarns = messageAccountManage.accountWarn();
			for (AccountWarnResp accountWarn : accountWarns) {
				if (null == accountWarn) {
					continue;
				}
				String subject = accountWarn.getSubject();
				String content = accountWarn.getContent();
				mailSendService.sendMail(subject, content, mailtoArrays);
			}
		} catch (Exception e) {
			logger.error("invoke sms channel accountWarn error!", e);

			throw new SmpException();
		}
	}

}
