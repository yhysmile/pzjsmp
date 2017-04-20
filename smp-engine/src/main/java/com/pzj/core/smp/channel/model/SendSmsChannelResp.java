/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.smp.channel.model;

/**
 * 
 * @author Administrator
 * @version $Id: SendSmsChannelRespModel.java, v 0.1 2016年11月30日 上午11:03:11 Administrator Exp $
 */
public class SendSmsChannelResp implements java.io.Serializable {

	private static final long serialVersionUID = -3263752939408211300L;

	/** 请求通道响应码*/
	private Integer code;

	/** 通道返回响应结果*/
	private String content;

	/** 根据发送情况判断是否重试 true:重试 false:不用重试*/
	private Boolean restryFlag;

	/** 通道重试次数，默认值*/
	private Integer retryNum;

	/**
	 * Getter method for property <tt>code</tt>.
	 * 
	 * @return property value of code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * Setter method for property <tt>code</tt>.
	 * 
	 * @param code value to be assigned to property code
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * Getter method for property <tt>content</tt>.
	 * 
	 * @return property value of content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Setter method for property <tt>content</tt>.
	 * 
	 * @param content value to be assigned to property content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getRestryFlag() {
		return restryFlag;
	}

	public void setRestryFlag(Boolean restryFlag) {
		this.restryFlag = restryFlag;
	}

	public Integer getRetryNum() {
		return retryNum;
	}

	public void setRetryNum(Integer retryNum) {
		this.retryNum = retryNum;
	}

	@Override
	public String toString() {
		return "SendSmsChannelResp [code=" + code + ", content=" + content + ", restryFlag=" + restryFlag
				+ ", retryNum=" + retryNum + "]";
	}

}
