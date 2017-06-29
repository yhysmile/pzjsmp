package com.pzj.core.smp.channel.model;

import com.pzj.core.smp.channel.enums.ChannelIndentityEnum;

public class AccountWarnResp implements java.io.Serializable {

	private static final long serialVersionUID = -1606308987949443363L;
	/**
	 * 需要发送邮件的通道告警对象
	 */
	private ChannelIndentityEnum channelIndentity;

	private String subject;

	private String content;

	public ChannelIndentityEnum getChannelIndentity() {
		return channelIndentity;
	}

	public void setChannelIndentity(ChannelIndentityEnum channelIndentity) {
		this.channelIndentity = channelIndentity;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
