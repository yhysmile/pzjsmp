package com.pzj.core.smp.channel.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChannelRetryParam implements java.io.Serializable {

	private static final long serialVersionUID = -8076420787923572210L;

	/**
	 * 短信内容
	 */
	private String msgContent;
	/**
	 * 手机号
	 */
	private List<String> phoneNumber;

	/** 请求通道响应码*/
	private Integer respCode;

	/** 通道返回响应结果*/
	private String respContent;

	/** 通道信息*/
	private ChannelInfo channelInfo;

	/** 通道id*/
	private Long channelId;

	/** 通道标识*/
	private Integer channelIdentity;

	/** 通道重试map,key:通道id value：重试次数 （次数-1代表此通道当前不能用）*/
	private final Map<Long, Integer> retryChannelMap = new HashMap<Long, Integer>();

	/** 通道重试次数，默认值*/
	private Integer retryNum;

	public ChannelRetryParam() {
	}

	public ChannelRetryParam(List<String> phoneNumber, String msgContent, Integer channelIdentity) {
		this.phoneNumber = phoneNumber;
		this.msgContent = msgContent;
		this.channelIdentity = channelIdentity;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public List<String> getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(List<String> phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getRespCode() {
		return respCode;
	}

	public void setRespCode(Integer respCode) {
		this.respCode = respCode;
	}

	public String getRespContent() {
		return respContent;
	}

	public void setRespContent(String respContent) {
		this.respContent = respContent;
	}

	public Integer getChannelIdentity() {
		return channelIdentity;
	}

	public void setChannelIdentity(Integer channelIdentity) {
		this.channelIdentity = channelIdentity;
	}

	public Map<Long, Integer> getRetryChannelMap() {
		return retryChannelMap;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public Integer getRetryNum() {
		return retryNum;
	}

	public void setRetryNum(Integer retryNum) {
		this.retryNum = retryNum;
	}

	public ChannelInfo getChannelInfo() {
		return channelInfo;
	}

	public void setChannelInfo(ChannelInfo channelInfo) {
		this.channelInfo = channelInfo;
	}

}
