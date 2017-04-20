package com.pzj.core.smp.channel.model;

import java.util.Date;
import java.util.List;

public class ChannelSendMessage implements java.io.Serializable {

	private static final long serialVersionUID = 7737803818277369841L;
	/**
	 * 短信内容
	 */
	private String content;
	/**
	 * 手机号
	 */
	private List<String> phoneNumber;
	/** 发送唯一id*/
	private String sendLinkId;
	/**
	 * 短信发送的时间
	 */
	private Date sendDate;
	/**
	 * 过期时长（单位毫秒）
	 */
	private Long expireDuration;
	/** 发送错误信息*/
	private String errTip;

	public ChannelSendMessage() {
	}

	public ChannelSendMessage(List<String> phoneNumber, String content) {
		this.phoneNumber = phoneNumber;
		this.content = content;
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

	public String getSendLinkId() {
		return sendLinkId;
	}

	public void setSendLinkId(String sendLinkId) {
		this.sendLinkId = sendLinkId;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Long getExpireDuration() {
		return expireDuration;
	}

	public void setExpireDuration(Long expireDuration) {
		this.expireDuration = expireDuration;
	}

	public String getErrTip() {
		return errTip;
	}

	public void setErrTip(String errTip) {
		this.errTip = errTip;
	}

	@Override
	public String toString() {
		return "ChannelSendMessage [content=" + content + ", phoneNumber=" + phoneNumber + ", sendLinkId=" + sendLinkId
				+ ", sendDate=" + sendDate + ", expireDuration=" + expireDuration + ", errTip=" + errTip + "]";
	}

}
