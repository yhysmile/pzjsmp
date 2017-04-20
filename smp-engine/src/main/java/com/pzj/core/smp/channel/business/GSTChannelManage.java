package com.pzj.core.smp.channel.business;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.smp.channel.common.ChannelAccessConstant;
import com.pzj.core.smp.channel.common.ChannelUtils;
import com.pzj.core.smp.channel.common.ObjUtils;
import com.pzj.core.smp.channel.enums.ChannelIndentityEnum;
import com.pzj.core.smp.channel.enums.ChannelTypeEnum;
import com.pzj.core.smp.channel.model.ChannelInfo;
import com.pzj.core.smp.channel.model.ChannelInfoQuery;
import com.pzj.core.smp.channel.model.ChannelSendMessage;
import com.pzj.core.smp.channel.model.ChannelUserinfo;
import com.pzj.core.smp.channel.model.ChannelUserinfoQuery;
import com.pzj.core.smp.channel.model.GSTSendParam;
import com.pzj.core.smp.channel.model.HttpRequest;
import com.pzj.core.smp.channel.model.SendSmsChannelResp;
import com.pzj.core.smp.common.exception.SmpException;
import com.pzj.core.smp.common.exception.SmpExceptionCode;

@Component("gstChannelManage")
public class GSTChannelManage {

	private final Logger logger = LoggerFactory.getLogger(GSTChannelManage.class);
	@Resource(name = "channelUserinfoQueryManage")
	private ChannelUserinfoQueryManage channelUserinfoQueryManage;
	@Resource(name = "channelQueryManage")
	private ChannelQueryManage channelQueryManage;
	@Resource(name = "httpRequestManage")
	private HttpRequestManage httpRequestManage;

	/**
	 * 高斯通发送下行短信
	 * @param sendMessage
	 * @return SendSmsChannelResp
	 * @throws IOException 
	 */
	public SendSmsChannelResp sendDownlinkMessage(ChannelSendMessage sendMessage) throws IOException {
		//获取通道用户信息
		ChannelUserinfo channelUserinfo = getChannelUserinfo();
		if (ObjUtils.checkObjectIsNull(channelUserinfo)) {
			throw new SmpException(SmpExceptionCode.NOT_FOUND_GST_CHANNELUSER_ERR);
		}
		//获取通道信息
		ChannelInfo channelInfo = getChannelInfo();
		if (ObjUtils.checkObjectIsNull(channelInfo)) {
			throw new SmpException(SmpExceptionCode.NOT_FOUND_GST_CHANNEL_ERR);
		}
		//初始化高斯通发送参数
		GSTSendParam gstSendParam = initSendParam(sendMessage, channelUserinfo);

		//调用发送通道
		SendSmsChannelResp smsChannelResp = send(gstSendParam, channelInfo);

		return smsChannelResp;
	}

	/**
	 * 获取通道用户信息
	 * @return ChannelUserinfo
	 */
	private ChannelUserinfo getChannelUserinfo() {
		ChannelUserinfoQuery queryModel = new ChannelUserinfoQuery();
		queryModel.setIndentity(ChannelIndentityEnum.GST.getKey());
		ChannelUserinfo channelUserinfo = channelUserinfoQueryManage.queryUserByChannelIdentity(queryModel);
		return channelUserinfo;
	}

	/**
	 * 获取通道信息
	 * @return ChannelInfo
	 */
	private ChannelInfo getChannelInfo() {
		ChannelInfoQuery channelInfoQuery = new ChannelInfoQuery();
		channelInfoQuery.setType(ChannelTypeEnum.GST_DOWNLINK.getKey());
		ChannelInfo channelInfo = channelQueryManage.queryChannelInfoByType(channelInfoQuery);
		return channelInfo;
	}

	/**
	 * 初始化发送参数
	 * @param sendMessage
	 * @param channelUserinfo
	 * @return GSTSendParam
	 */
	private GSTSendParam initSendParam(ChannelSendMessage sendMessage, ChannelUserinfo channelUserinfo) {
		GSTSendParam gstSendParam = new GSTSendParam();
		gstSendParam.setUsername(channelUserinfo.getUsername());
		gstSendParam.setPassword(channelUserinfo.getPassword());
		gstSendParam.setTo(ChannelUtils.convertSendPhoneFormat(sendMessage.getPhoneNumber()));
		String content = sendMessage.getContent();
		try {
			//测试添加字符串
			sendMessage.setContent(content);

			content = URLEncoder.encode(content, ChannelAccessConstant.CHARACTER_ENCODING_GBK);
		} catch (UnsupportedEncodingException e) {
			logger.error("gst channel initSendParam message content encode error!", e);
			throw new SmpException(e);
		}
		gstSendParam.setContent(content);
		return gstSendParam;
	}

	/**
	 * 调用通道返回响应数据
	 * @param gstSendParam
	 * @param channelInfo
	 * @return SendSmsChannelResp
	 * @throws IOException 
	 */
	private SendSmsChannelResp send(GSTSendParam gstSendParam, ChannelInfo channelInfo) throws IOException {
		StringBuffer urlBuffer = new StringBuffer(channelInfo.getUrl());
		urlBuffer.append("?").append(gstSendParam.getNeedParam());

		logger.info("高斯通，短信发送链接：" + urlBuffer);

		HttpRequest httpRequest = new HttpRequest();
		httpRequest.setUrl(urlBuffer.toString());
		httpRequest.setReqCharacter(getReqHttpCode());
		httpRequest.setRespCharacter(getRespHttpCode());

		SendSmsChannelResp channelResp = httpRequestManage.doGet(httpRequest);

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
