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
import com.pzj.core.smp.channel.model.HLQXTAccountParam;
import com.pzj.core.smp.channel.model.HLQXTSendParam;
import com.pzj.core.smp.channel.model.HttpRequest;
import com.pzj.core.smp.channel.model.SendSmsChannelResp;
import com.pzj.core.smp.common.exception.SmpException;
import com.pzj.core.smp.common.exception.SmpExceptionCode;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.idgen.IDGenerater;

@Component("hlqxtChannelManage")
public class HLQXTChannelManage {

	private final Logger logger = LoggerFactory.getLogger(HLQXTChannelManage.class);

	@Resource(name = "channelUserinfoQueryManage")
	private ChannelUserinfoQueryManage channelUserinfoQueryManage;
	@Resource(name = "channelQueryManage")
	private ChannelQueryManage channelQueryManage;
	@Resource(name = "httpRequestManage")
	private HttpRequestManage httpRequestManage;
	@Resource(name = "idGenerater")
	private IDGenerater idGenerater;

	/**
	 * 鸿联企信通发送下行短信
	 * @param sendMessage
	 * @return SendSmsChannelResp
	 * @throws IOException 
	 */
	public SendSmsChannelResp sendDownlinkMessage(ChannelSendMessage sendMessage) throws IOException {
		//获取通道用户信息
		ChannelUserinfo channelUserinfo = getChannelUserinfo();
		if (ObjUtils.checkObjectIsNull(channelUserinfo)) {
			//			throw new NotFoundHLQXTChannelUserException();
			throw new SmpException(SmpExceptionCode.NOT_FOUND_HLQXT_CHANNELUSER_ERR);
		}

		//获取通道信息
		ChannelInfo channelInfo = getChannelInfo(ChannelTypeEnum.HLQXT_DOWNLINK.getKey());
		if (ObjUtils.checkObjectIsNull(channelInfo)) {
			//			throw new NotFoundHLQXTChannelException();
			throw new SmpException(SmpExceptionCode.NOT_FOUND_HLQXT_CHANNEL_ERR);
		}

		//初始化华联企信通发送参数
		HLQXTSendParam hlqxtSendParam = initSendParam(sendMessage, channelUserinfo);

		//调用发送通道
		SendSmsChannelResp smsChannelResp = send(hlqxtSendParam, channelInfo);

		return smsChannelResp;
	}

	public SendSmsChannelResp getAccountInfo() throws IOException {
		//获取通道用户信息
		ChannelUserinfo channelUserinfo = getChannelUserinfo();
		if (ObjUtils.checkObjectIsNull(channelUserinfo)) {
			throw new SmpException(SmpExceptionCode.NOT_FOUND_HLQXT_CHANNELUSER_ERR);
		}

		//获取通道信息
		ChannelInfo channelInfo = getChannelInfo(ChannelTypeEnum.HLQXT_BALANCE.getKey());
		if (ObjUtils.checkObjectIsNull(channelInfo)) {
			throw new SmpException(SmpExceptionCode.NOT_FOUND_HLQXT_CHANNEL_ERR);
		}
		HLQXTAccountParam hlqxtAccountParam = initAccoutParam(channelUserinfo);

		SendSmsChannelResp smsChannelResp = getAccount(hlqxtAccountParam, channelInfo);

		return smsChannelResp;
	}

	/**
	 * 获取通道用户信息
	 * @return ChannelUserinfo
	 */
	public ChannelUserinfo getChannelUserinfo() {
		ChannelUserinfoQuery queryModel = new ChannelUserinfoQuery();
		queryModel.setIndentity(ChannelIndentityEnum.HLQXT.getKey());
		ChannelUserinfo channelUserinfo = channelUserinfoQueryManage.queryUserByChannelIdentity(queryModel);
		return channelUserinfo;
	}

	/**
	 * 获取通道信息
	 * @return ChannelInfo
	 */
	private ChannelInfo getChannelInfo(Integer type) {
		ChannelInfoQuery channelInfoQuery = new ChannelInfoQuery();
		channelInfoQuery.setType(type);
		ChannelInfo channelInfo = channelQueryManage.queryChannelInfoByType(channelInfoQuery);
		return channelInfo;
	}

	/**
	 * 初始化发送参数
	 * @param sendMessage
	 * @param channelUserinfo
	 * @return HLQXTSendParam
	 */
	private HLQXTSendParam initSendParam(ChannelSendMessage sendMessage, ChannelUserinfo channelUserinfo) {
		HLQXTSendParam hlqxtSendParam = new HLQXTSendParam();
		hlqxtSendParam.setUsername(channelUserinfo.getUsername());
		hlqxtSendParam.setPassword(channelUserinfo.getPassword());
		hlqxtSendParam.setPhone(ChannelUtils.convertSendPhoneFormat(sendMessage.getPhoneNumber()));
		String sendLinkId = String.valueOf(idGenerater.nextId());
		hlqxtSendParam.setLinkid(sendLinkId);
		sendMessage.setSendLinkId(sendLinkId);
		String message = sendMessage.getContent();
		try {
			//测试添加字符串
			sendMessage.setContent(message);

			message = URLEncoder.encode(message, ChannelAccessConstant.CHARACTER_ENCODING_GBK);
		} catch (UnsupportedEncodingException e) {
			logger.error("hlqxt channel initSendParam message content encode error!", e);
			throw new SmpException(e);
		}
		hlqxtSendParam.setMessage(message);
		hlqxtSendParam.setEpid(ChannelAccessConstant.HLQXT_EPID);
		return hlqxtSendParam;
	}

	/**
	 * 调用通道返回响应数据
	 * @param hlqxtSendParam
	 * @param channelInfo
	 * @return SendSmsChannelResp
	 * @throws IOException 
	 */
	private SendSmsChannelResp send(HLQXTSendParam hlqxtSendParam, ChannelInfo channelInfo) throws IOException {
		StringBuffer urlBuffer = new StringBuffer(channelInfo.getUrl());
		urlBuffer.append("?").append(hlqxtSendParam.getSendParam());

		logger.info("鸿联企信通，短信发送链接：" + urlBuffer);

		HttpRequest httpRequest = new HttpRequest();
		httpRequest.setUrl(urlBuffer.toString());
		httpRequest.setReqCharacter(getReqHttpCode());
		httpRequest.setRespCharacter(getRespHttpCode());

		SendSmsChannelResp channelResp = httpRequestManage.doGet(httpRequest);

		logger.info("鸿联企信通，短信发送结果：{}", JSONConverter.toJson(channelResp));
		return channelResp;

	}

	/**
	 * 初始化鸿联获取账户参数
	 * @param channelUserinfo
	 * @return
	 */
	private HLQXTAccountParam initAccoutParam(ChannelUserinfo channelUserinfo) {
		HLQXTAccountParam hlqxtAccount = new HLQXTAccountParam();
		hlqxtAccount.setUsername(channelUserinfo.getUsername());
		hlqxtAccount.setPassword(channelUserinfo.getPassword());
		hlqxtAccount.setEpid(ChannelAccessConstant.HLQXT_EPID);
		return hlqxtAccount;
	}

	/**
	 * 获取账户数据
	 * @param hlqxtAccountParam
	 * @param channelInfo
	 * @return
	 * @throws IOException
	 */
	private SendSmsChannelResp getAccount(HLQXTAccountParam hlqxtAccountParam, ChannelInfo channelInfo)
			throws IOException {
		StringBuffer urlBuffer = new StringBuffer(channelInfo.getUrl());
		urlBuffer.append("?").append(hlqxtAccountParam.getAccountNeedParam());
		urlBuffer.append("&epid=").append(hlqxtAccountParam.getEpid());

		logger.info("鸿联企信通，获取账户链接：" + urlBuffer);

		HttpRequest httpRequest = new HttpRequest();
		httpRequest.setUrl(urlBuffer.toString());
		httpRequest.setReqCharacter(getReqHttpCode());
		httpRequest.setRespCharacter(getRespHttpCode());

		SendSmsChannelResp channelResp = httpRequestManage.doGet(httpRequest);

		logger.info("鸿联企信通，获取账户结果：{}", JSONConverter.toJson(channelResp));
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
