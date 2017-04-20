package com.pzj.core.smp.channel.business;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.smp.channel.common.ChannelAccessConstant;
import com.pzj.core.smp.channel.common.ObjUtils;
import com.pzj.core.smp.channel.enums.ChannelIndentityEnum;
import com.pzj.core.smp.channel.enums.GSTRespCodeEnum;
import com.pzj.core.smp.channel.enums.HLQXTRespCodeEnum;
import com.pzj.core.smp.channel.model.ChannelInfo;
import com.pzj.core.smp.channel.model.ChannelRetryParam;
import com.pzj.core.smp.channel.model.SendSmsChannelResp;
import com.pzj.core.smp.common.exception.SmpException;
import com.pzj.framework.converter.JSONConverter;

@Component("channelRetryManage")
public class ChannelRetryManage {
	private final Logger logger = LoggerFactory.getLogger(ChannelRetryManage.class);

	@Resource(name = "intelSwitchChannelManage")
	private IntelSwitchChannelManage intelSwitchChannelManage;
	@Resource(name = "sendMessageManage")
	private SendMessageManage sendMessageManage;

	/**
	 * 重试发送短信内容
	 * @param channelRetry
	 * @return SendSmsChannelResp
	 */
	public SendSmsChannelResp channelRestry(ChannelRetryParam channelRetry) {
		if (null == channelRetry) {
			return null;
		}
		logger.info("invoke send message channel restry,request param:{}", JSONConverter.toJson(channelRetry));

		//处理通道重试机制
		Integer channelIdentity = channelRetry.getChannelIdentity();
		if (ChannelIndentityEnum.HLQXT.getKey() == channelIdentity) {
			hlqxtRespHandle(channelRetry);

		} else if (ChannelIndentityEnum.GST.getKey() == channelIdentity) {
			gstRespHandle(channelRetry);
		}

		//智能筛选获取最优通道
		ChannelInfo channelInfo = intelSwitchChannelManage.failRetryReboot(channelRetry);

		//获取通道后调用通道发送短信
		SendSmsChannelResp sendSmsChannelResp = null;
		if (!ObjUtils.checkObjectIsNull(channelInfo)) {
			//获取通道标识
			channelIdentity = sendMessageManage.getChannelIdentity(channelInfo);

			channelRetry.setChannelIdentity(channelIdentity);
			channelRetry.setChannelInfo(channelInfo);

			sendSmsChannelResp = sendMessageManage.invokeSendMessage(channelRetry, null, null);
		} else {
			return null;
		}

		//检测短信是否发送成功，根据通道返回结果采取重试机制
		if (sendMessageManage.checkGoFailRetry(sendSmsChannelResp, channelIdentity)) {
			//初始化重试参数
			ChannelRetryParam destChannelRetry = sendMessageManage.initFailRetryParam(channelRetry, sendSmsChannelResp,
					channelInfo);

			//调用重拾发送机制
			channelRestry(destChannelRetry);
		}

		return sendSmsChannelResp;
	}

	/**
	 * 鸿联企信通重试处理
	 * @param channelRetry
	 */
	private void hlqxtRespHandle(ChannelRetryParam channelRetry) {
		Long key = channelRetry.getChannelId();
		Integer channelRetryTime = 1;

		HLQXTRespCodeEnum respCode = HLQXTRespCodeEnum.getRespCode(channelRetry.getRespContent());
		if (null != respCode) {
			//短信发送失败，重试机制继续发送
			if (respCode == HLQXTRespCodeEnum.SEND_FAIL) {
				channelRetryTime = channelRetry.getRetryChannelMap().get(key);
				channelRetryTime = ObjUtils.checkIntegerIsNull(channelRetryTime, true) ? 1 : channelRetryTime++;
			} else {
				channelRetryTime = -1;
			}
		} else {
			channelRetryTime = channelRetry.getRetryNum() == null ? 1 : channelRetry.getRetryNum();
		}

		commonChannelMapHandle(channelRetry, key, channelRetryTime);
	}

	/**
	 * 高斯通通道重试处理
	 * @param channelRetry
	 */
	private void gstRespHandle(ChannelRetryParam channelRetry) {
		Long key = channelRetry.getChannelId();
		Integer channelRetryTime = 1;

		GSTRespCodeEnum respCode = GSTRespCodeEnum.getRespCode(channelRetry.getRespContent());
		if (null != respCode) {
			GSTRespCodeEnum frequent = GSTRespCodeEnum.FREQUENT_REQUEST;
			GSTRespCodeEnum error = GSTRespCodeEnum.ERROR;
			GSTRespCodeEnum unknownErr = GSTRespCodeEnum.ERROR_UNKNOWN;
			if (respCode == frequent || respCode == error || respCode == unknownErr) {
				channelRetryTime = channelRetry.getRetryChannelMap().get(key);
				channelRetryTime = ObjUtils.checkIntegerIsNull(channelRetryTime, true) ? 1 : channelRetryTime++;
			} else {
				channelRetryTime = -1;
			}
		} else {
			channelRetryTime = channelRetry.getRetryNum() == null ? 1 : channelRetry.getRetryNum();
		}
		commonChannelMapHandle(channelRetry, key, channelRetryTime);
	}

	/**
	 * 重试等待
	 * @param channelRetry
	 * @param key
	 * @param channelRetryTime
	 */
	private void commonChannelMapHandle(ChannelRetryParam channelRetry, Long key, Integer channelRetryTime) {
		channelRetry.getRetryChannelMap().put(key, channelRetryTime);
		int retryTimes = ChannelAccessConstant.SINGLE_CHANNEL_RETRY_TIMES;
		if (channelRetryTime == --retryTimes) {
			try {
				Thread.sleep(500L);
			} catch (InterruptedException e) {
				logger.error("invoke fail retry sleep interrupted exception", e);
				throw new SmpException(e);
			}
		}
	}
}
