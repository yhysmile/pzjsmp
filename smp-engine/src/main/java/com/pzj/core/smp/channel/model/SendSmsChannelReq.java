/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.smp.channel.model;

import java.util.List;

/**
 * 
 * @author Administrator
 * @version $Id: SendSmsChannelReqModel.java, v 0.1 2016年11月30日 上午11:14:00 Administrator Exp $
 */
public class SendSmsChannelReq implements java.io.Serializable {

	private static final long serialVersionUID = 2419913519511289823L;
	/**
	 * 短信内容
	 */
	private String content;
	/**
	 * 手机号
	 */
	private List<String> phoneNumber;
	/** 短信通道标识*/
	private Integer channelIdentity;

	public Integer getChannelIdentity() {
		return channelIdentity;
	}

	public void setChannelIdentity(Integer channelIdentity) {
		this.channelIdentity = channelIdentity;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(List<String> phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
