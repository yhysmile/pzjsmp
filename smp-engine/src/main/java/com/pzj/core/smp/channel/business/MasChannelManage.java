package com.pzj.core.smp.channel.business;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.pzj.core.smp.channel.common.ChannelAccessConstant;
import com.pzj.core.smp.channel.common.EncryptUtils;
import com.pzj.core.smp.channel.common.ObjUtils;
import com.pzj.core.smp.channel.enums.ChannelIndentityEnum;
import com.pzj.core.smp.channel.enums.ChannelTypeEnum;
import com.pzj.core.smp.channel.model.ChannelInfo;
import com.pzj.core.smp.channel.model.ChannelInfoQuery;
import com.pzj.core.smp.channel.model.ChannelSendMessage;
import com.pzj.core.smp.channel.model.ChannelUserinfo;
import com.pzj.core.smp.channel.model.ChannelUserinfoQuery;
import com.pzj.core.smp.channel.model.HttpRequest;
import com.pzj.core.smp.channel.model.MasHttpContentResp;
import com.pzj.core.smp.channel.model.MasSendParam;
import com.pzj.core.smp.channel.model.SendSmsChannelResp;
import com.pzj.core.smp.common.exception.SmpException;
import com.pzj.core.smp.common.exception.SmpExceptionCode;
import com.pzj.framework.converter.JSONConverter;

@Component("masChannelManage")
public class MasChannelManage {

	private final Logger logger = LoggerFactory.getLogger(MasChannelManage.class);

	@Resource(name = "channelUserinfoQueryManage")
	private ChannelUserinfoQueryManage channelUserinfoQueryManage;
	@Resource(name = "channelQueryManage")
	private ChannelQueryManage channelQueryManage;
	@Resource(name = "httpRequestManage")
	private HttpRequestManage httpRequestManage;

	public SendSmsChannelResp sendDownlinkMessage(ChannelSendMessage sendMessage) throws IOException {
		//获取通道用户信息
		ChannelUserinfo channelUserinfo = getChannelUserinfo();

		//获取通道信息
		ChannelInfo channelInfo = getChannelInfo();
		if (ObjUtils.checkObjectIsNull(channelInfo)) {
			throw new SmpException(SmpExceptionCode.NOT_FOUND_GST_CHANNEL_ERR);
		}

		//初始化Mas政企云发送参数
		MasSendParam masSendParam = initSendParam(sendMessage, channelUserinfo);

		return send(masSendParam, channelInfo);
	}

	/**
	 * 获取通道用户信息
	 * @return ChannelUserinfo
	 */
	private ChannelUserinfo getChannelUserinfo() {
		ChannelUserinfoQuery queryModel = new ChannelUserinfoQuery();
		queryModel.setIndentity(ChannelIndentityEnum.MAS.getKey());
		ChannelUserinfo channelUserinfo = channelUserinfoQueryManage.queryUserByChannelIdentity(queryModel);
		return channelUserinfo;
	}

	/**
	 * 获取通道信息
	 * @return ChannelInfo
	 */
	private ChannelInfo getChannelInfo() {
		ChannelInfoQuery channelInfoQuery = new ChannelInfoQuery();
		channelInfoQuery.setType(ChannelTypeEnum.MAS_DOWNLINK.getKey());
		ChannelInfo channelInfo = channelQueryManage.queryChannelInfoByType(channelInfoQuery);
		return channelInfo;
	}

	/**
	 * 初始化发送短信参数
	 * @param sendMessage
	 * @return MasSendParam
	 */
	private MasSendParam initSendParam(ChannelSendMessage sendMessage, ChannelUserinfo channelUserinfo) {
		List<String> phoneNums = sendMessage.getPhoneNumber();
		String phones = StringUtils.collectionToDelimitedString(phoneNums, ",");
		MasSendParam masSendParam = new MasSendParam(channelUserinfo.getUsername(), channelUserinfo.getPassword(),
				phones, sendMessage.getContent());
		//		String[] phones = phoneNums.toArray(new String[phoneNums.size()]);
		//		String content = sendMessage.getContent();
		//		String sendLinkId = String.valueOf(idGenerater.nextId());
		//		sendMessage.setSendLinkId(sendLinkId);
		//		MasSdkSendParam masSendParam = new MasSdkSendParam(phones, content, sendLinkId);
		return masSendParam;
	}

	/**
	 * 调用通道发送短信
	 * @param masSendParam
	 * @param channelInfo
	 * @return SendSmsChannelResp
	 * @throws IOException 
	 */
	private SendSmsChannelResp send(MasSendParam masSendParam, ChannelInfo channelInfo) throws IOException {

		HttpRequest httpRequest = new HttpRequest();
		httpRequest.setUrl(channelInfo.getUrl());
		String encrpyParam = EncryptUtils.base64Encoder(JSONConverter.toJson(masSendParam));
		httpRequest.setParams(encrpyParam);
		httpRequest.setReqCharacter(getReqHttpCode());
		httpRequest.setRespCharacter(getRespHttpCode());

		SendSmsChannelResp channelResp = httpRequestManage.doPost(httpRequest);

		if (null != channelResp && channelResp.getCode() == ChannelAccessConstant.HTTP_RESP_SUCCESS_CODE
				&& null != channelResp.getContent()) {
			MasHttpContentResp masResp = JSONConverter.toBean(channelResp.getContent(), MasHttpContentResp.class);
			channelResp.setContent(masResp.getRspcod());
		}

		logger.info("mas政企云，短信发送结果：{}", JSONConverter.toJson(channelResp));

		//		MasAccountParam masAccountParam = new MasAccountParam(channelInfo.getUrl(), channelUserinfo.getUsername(),
		//				channelUserinfo.getPassword());
		//		logger.info("mas短信通道,send start.....");
		//		Client masClient = masClientManage.getInstance().getMasClient(masAccountParam);
		//		logger.info("mas短信通道,get masclient:{}.....", JSONConverter.toJson(masClient));
		//		if (null != masClient) {
		//			logger.info("mas短信通道，短信发送参数 masSendParam：{}", JSONConverter.toJson(masSendParam));
		//
		//			int sendResult = masClient.sendDSMS(masSendParam.getMobiles(), masSendParam.getSmsContent(),
		//					masSendParam.getAddSerial(), masSendParam.getSmsPriority(), masSendParam.getSign(),
		//					masSendParam.getMsgGroup(), masSendParam.isMo());
		//
		//			logger.info("mas短信通道，短信发送结果：" + sendResult);
		//
		//			SendSmsChannelResp sendSmsChannelRespModel = new SendSmsChannelResp();
		//			sendSmsChannelRespModel.setCode(sendResult);
		//			sendSmsChannelRespModel.setContent(String.valueOf(sendResult));
		//			return sendSmsChannelRespModel;
		//		} else {
		//			logger.info("get mas client is null ,client:{}", JSONConverter.toJson(masClient));
		//		}
		return channelResp;
	}

	/**
	 * 获取请求http链接的编码
	 * @return String
	 */
	private String getReqHttpCode() {
		return ChannelAccessConstant.CHARACTER_ENCODING_UTF8;
	}

	/**
	 * 获取响应http链接的编码
	 * @return String
	 */
	private String getRespHttpCode() {
		return ChannelAccessConstant.CHARACTER_ENCODING_GBK;
	}
}
