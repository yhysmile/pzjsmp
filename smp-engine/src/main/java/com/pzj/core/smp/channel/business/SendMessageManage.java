package com.pzj.core.smp.channel.business;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.smp.channel.common.ChannelAccessConstant;
import com.pzj.core.smp.channel.enums.ChannelIndentityEnum;
import com.pzj.core.smp.channel.enums.ChannelTypeEnum;
import com.pzj.core.smp.channel.enums.HLQXTRespCodeEnum;
import com.pzj.core.smp.channel.model.ChannelExceptionInfo;
import com.pzj.core.smp.channel.model.ChannelInfo;
import com.pzj.core.smp.channel.model.ChannelRetryParam;
import com.pzj.core.smp.channel.model.ChannelSendMessage;
import com.pzj.core.smp.channel.model.SendSmsChannelResp;
import com.pzj.core.smp.common.exception.SmpException;
import com.pzj.core.smp.common.exception.SmpExceptionCode;
import com.pzj.framework.converter.JSONConverter;

@Component("sendMessageManage")
public class SendMessageManage {
	private final Logger logger = LoggerFactory.getLogger(SendMessageManage.class);
	@Resource(name = "hlqxtChannelManage")
	private HLQXTChannelManage hlqxtChannelManage;
	@Resource(name = "gstChannelManage")
	private GSTChannelManage gstChannelManage;

	/**
	 * 检查是否走重试服务
	 * @param sendSmsChannelResp
	 * @param channelIdentity
	 * @return Boolean true:重试；false:不需要重试
	 */
	public Boolean checkGoFailRetry(SendSmsChannelResp sendSmsChannelResp, Integer channelIdentity) {
		Boolean flag = Boolean.FALSE;
		if (null == sendSmsChannelResp || sendSmsChannelResp.getRestryFlag()) {
			flag = Boolean.TRUE;
			return flag;
		}

		if (ChannelIndentityEnum.HLQXT.getKey() == channelIdentity
				&& !sendSmsChannelResp.getContent().equals(HLQXTRespCodeEnum.SUCCESS.getCode())) {
			flag = Boolean.TRUE;
		} else if (ChannelIndentityEnum.GST.getKey() == channelIdentity
				&& !sendSmsChannelResp.getContent().contains(ChannelAccessConstant.GST_SEND_SUCCESS_FLAG)) {
			flag = Boolean.TRUE;
		}
		return flag;
	}

	/**
	 * 检查短信是否发送成功
	 * @param sendSmsChannelResp
	 * @param channelIdentity
	 * @return Boolean true:成功，false：失败
	 */
	public Boolean checkSendWheatherSuccess(SendSmsChannelResp sendSmsChannelResp, Integer channelIdentity) {
		Boolean flag = Boolean.FALSE;
		if (null == sendSmsChannelResp || null == sendSmsChannelResp.getContent()) {
			return flag;
		}
		if (ChannelIndentityEnum.HLQXT.getKey() == channelIdentity
				&& !sendSmsChannelResp.getContent().equals(HLQXTRespCodeEnum.SUCCESS.getCode())) {
			flag = Boolean.TRUE;
		} else if (ChannelIndentityEnum.GST.getKey() == channelIdentity
				&& !sendSmsChannelResp.getContent().contains(ChannelAccessConstant.GST_SEND_SUCCESS_FLAG)) {
			flag = Boolean.TRUE;
		}
		return flag;
	}

	/**
	 * 重试发送参数初始化
	 * @param sendMessage
	 * @param sendSmsChannelResp
	 * @param channelIdentity
	 * @param channelInfo
	 * @return ChannelRetryParam
	 */
	public ChannelRetryParam firstInitFailRetryParam(ChannelSendMessage sendMessage,
			SendSmsChannelResp sendSmsChannelResp, Integer channelIdentity, ChannelInfo channelInfo) {
		ChannelRetryParam channelRetry = new ChannelRetryParam(sendMessage.getPhoneNumber(), sendMessage.getContent(),
				channelIdentity);
		if (null != sendSmsChannelResp) {
			channelRetry.setRespCode(sendSmsChannelResp.getCode());
			channelRetry.setRespContent(sendSmsChannelResp.getContent());
			channelRetry.setRetryNum(sendSmsChannelResp.getRetryNum());
		}
		channelRetry.setChannelId(channelInfo.getId());

		return channelRetry;
	}

	/**
	 * 重试发送参数初始化
	 * @param sendMessage
	 * @param sendSmsChannelResp
	 * @param channelIdentity
	 * @param channelInfo
	 * @return ChannelRetryParam
	 */
	public ChannelRetryParam initFailRetryParam(ChannelRetryParam orgChannelRetry,
			SendSmsChannelResp sendSmsChannelResp, ChannelInfo channelInfo) {

		if (null != sendSmsChannelResp) {
			orgChannelRetry.setRespCode(sendSmsChannelResp.getCode());
			orgChannelRetry.setRespContent(sendSmsChannelResp.getContent());
			orgChannelRetry.setRetryNum(sendSmsChannelResp.getRetryNum());
		}
		orgChannelRetry.setChannelId(channelInfo.getId());

		return orgChannelRetry;
	}

	/**
	 * 获取通道标识
	 * @param channelInfo
	 * @return Integer
	 */
	public Integer getChannelIdentity(ChannelInfo channelInfo) {
		Integer channelIdentity = null;
		ChannelTypeEnum channelType = ChannelTypeEnum.getChannelType(channelInfo.getChannelType());
		if (ChannelTypeEnum.HLQXT_DOWNLINK == channelType) {
			channelIdentity = ChannelIndentityEnum.HLQXT.getKey();
		} else if (ChannelTypeEnum.GST_DOWNLINK == channelType) {
			channelIdentity = ChannelIndentityEnum.GST.getKey();
		}
		if (null == channelIdentity) {
			throw new SmpException(SmpExceptionCode.NO_AVAIABLE_CHANNEL_ERR);
		}

		return channelIdentity;
	}

	/**
	 * 调用通道发送短信
	 * @param sendMessage
	 * @param channelIdentity
	 * @return SendSmsChannelResp
	 */
	//	public SendSmsChannelResp invokeChannelSendMessage(ChannelSendMessage sendMessage, Integer channelIdentity) {
	//		SendSmsChannelResp sendSmsChannelResp = null;
	//		if (ChannelIndentityEnum.HLQXT.getKey() == channelIdentity) {
	//			sendSmsChannelResp = hlqxtChannelManage.sendDownlinkMessage(sendMessage);
	//		} else if (ChannelIndentityEnum.GST.getKey() == channelIdentity) {
	//			sendSmsChannelResp = gstChannelManage.sendDownlinkMessage(sendMessage);
	//		}
	//		return sendSmsChannelResp;
	//
	//	}

	/**
	 * 调用通道发送短信
	 * @param channelRetry
	 * @param channelInfo
	 */
	//	public SendSmsChannelResp invokeSendMessage(ChannelRetryParam channelRetry) {
	//		SendSmsChannelResp sendSmsChannelResp = null;
	//		try {
	//			ChannelSendMessage sendMessage = initSendMessage(channelRetry);
	//			Integer channelIdentity = channelRetry.getChannelIdentity();
	//			if (ChannelIndentityEnum.HLQXT.getKey() == channelIdentity) {
	//				sendSmsChannelResp = hlqxtChannelManage.sendDownlinkMessage(sendMessage);
	//			} else if (ChannelIndentityEnum.GST.getKey() == channelIdentity) {
	//				sendSmsChannelResp = gstChannelManage.sendDownlinkMessage(sendMessage);
	//			}
	//		} catch (IOException e) {
	//			e.printStackTrace();
	//		}
	//
	//		return sendSmsChannelResp;
	//	}

	/**
	 * 调用通道发送短信
	 * @param channelRetry
	 * @param sendMessage
	 * @param channelIdentity
	 * @return SendSmsChannelResp
	 */
	public SendSmsChannelResp invokeSendMessage(ChannelRetryParam channelRetry, ChannelSendMessage sendMessage,
			Integer channelIdentity) {
		SendSmsChannelResp sendSmsChannelResp = null;
		if (null != channelRetry) {
			sendMessage = initSendMessage(channelRetry);
			channelIdentity = channelRetry.getChannelIdentity();
		}
		Boolean excepFlag = Boolean.FALSE;
		Integer retryNum = ChannelExceptionInfo.defaultRetryNum;
		try {
			if (ChannelIndentityEnum.HLQXT.getKey() == channelIdentity) {
				sendSmsChannelResp = hlqxtChannelManage.sendDownlinkMessage(sendMessage);
			} else if (ChannelIndentityEnum.GST.getKey() == channelIdentity) {
				sendSmsChannelResp = gstChannelManage.sendDownlinkMessage(sendMessage);
			}
		} catch (IOException e) {
			logger.error("invoke sms channel send error! request sendMessage:{},channelRetry:{},channelIdentity:{}",
					JSONConverter.toJson(sendMessage), JSONConverter.toJson(channelRetry), channelIdentity, e);

			//判断是否异常重试发送
			ChannelExceptionInfo channelExcep = handleException(e);
			excepFlag = channelExcep.getExcepFlag();
			retryNum = channelExcep.getRetryNum();

			if (!excepFlag) {
				throw new SmpException(e);
			}
		} catch (Exception t) {
			logger.error("invoke sms channel send error! request sendMessage:{},channelRetry:{},channelIdentity:{}",
					JSONConverter.toJson(sendMessage), JSONConverter.toJson(channelRetry), channelIdentity, t);

			//判断是否异常重试发送
			ChannelExceptionInfo channelExcep = handleException(t);
			excepFlag = channelExcep.getExcepFlag();
			retryNum = channelExcep.getRetryNum();

			if (!excepFlag) {
				if (t instanceof SmpException) {
					throw t;
				}
				throw new SmpException(t);
			}
		}

		if (null == sendSmsChannelResp) {
			sendSmsChannelResp = new SendSmsChannelResp();
		}
		sendSmsChannelResp.setRestryFlag(excepFlag);
		sendSmsChannelResp.setRetryNum(retryNum);

		return sendSmsChannelResp;
	}

	/**
	 * 检查异常是否重试
	 * @param e
	 * @return Boolean true:需要重试 false:不需要重试
	 */
	private ChannelExceptionInfo handleException(Exception e) {
		ChannelExceptionInfo channelExcep = new ChannelExceptionInfo(Boolean.FALSE,
				ChannelExceptionInfo.defaultRetryNum);
		if (null != e) {
			//连接超时，需要重试
			if (e instanceof ConnectTimeoutException) {
				logger.warn("invoke channel send message timeout.", e);
				channelExcep.setExcepFlag(Boolean.TRUE);
			} else if (e instanceof FileNotFoundException) {
				logger.warn("invoke channel send message file not found.", e);
				channelExcep.setExcepFlag(Boolean.TRUE);
				channelExcep.setRetryNum(ChannelExceptionInfo.notAvaiRetryNum);
			} else if (e instanceof SmpException) {
				Integer errCode = ((SmpException) e).getCode();
				if (null != errCode && errCode >= 16100 && errCode <= 16104) {
					channelExcep.setExcepFlag(Boolean.TRUE);
					channelExcep.setRetryNum(ChannelExceptionInfo.notAvaiRetryNum);
				}
			}
		}
		return channelExcep;
	}

	/**
	 * 初始化发送短信参数
	 * @param channelRetry
	 * @return ChannelSendMessage
	 */
	public ChannelSendMessage initSendMessage(ChannelRetryParam channelRetry) {
		ChannelSendMessage sendMessage = new ChannelSendMessage();
		sendMessage.setPhoneNumber(channelRetry.getPhoneNumber());
		sendMessage.setContent(channelRetry.getMsgContent());
		return sendMessage;
	}
}
