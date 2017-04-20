/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.smp.channel.model;

/**
 * 
 * @author Administrator
 * @version $Id: GSTChannelParam.java, v 0.1 2016年11月30日 下午2:43:15 Administrator Exp $
 */
public class GSTSendParam implements java.io.Serializable {

	private static final long serialVersionUID = -1781010280722712917L;
	/**
	 * 登录名，由机构ID和登录名组成。例：机构ID为10001登录用户名为admin则username为10001:admin
	 */
	private String username;
	/**
	 * ISP登陆密码(公司分配给ISP)
	 */
	private String password;
	/**
	 * from如果为空， 那么返回的messageId为0,反之返回唯一的messageId
	 */
	private String from;
	/**
	 * 接收短信的手机号，支持多个(<=100)手机号码，中间以“,”分割。如当接收手机号码为国际号码时，
	 * 必须以00开头并带有国际区号的国际号码格式进行发送。如：00010187654，其格式为00+010（美国区号）+187654。
	 */
	private String to;
	/**
	 * 短信内容(只支持GBK编码,若使用其它编码需要转换一下)
	 */
	private String content;
	/**
	 * 定时发送时间（格式：yyyy-MM-dd HH:mm:ss）
	 */
	private String presendTime;
	/**
	 * 用户下行短信自扩展端口，其该端口的使用需要相关机构的指定配置。
	 * （该字段单独最大长度为14位，但下行短信实际发送后会根据SP号进行相关的截取操作）
	 */
	private String expandPrefix;
	/**
	 * (非必填)  是否语音短信,若为空默认为普通短信.该参数格式:
	 * 是否语音(0表示普通短信,1表示语音短信,2 表示信令短信,3 表示语音验证码短信)|重听次数|重拨次数|是否回复(0表示不回复,1表示回复)
	 * 如:isvoice=”1|1|2|0” 即:语音短信,重听次数1,重拨次数2,不回复.
	 */
	private String isvoice;

	/**
	 * Getter method for property <tt>username</tt>.
	 * 
	 * @return property value of username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Setter method for property <tt>username</tt>.
	 * 
	 * @param username value to be assigned to property username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Getter method for property <tt>password</tt>.
	 * 
	 * @return property value of password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter method for property <tt>password</tt>.
	 * 
	 * @param password value to be assigned to property password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Getter method for property <tt>from</tt>.
	 * 
	 * @return property value of from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * Setter method for property <tt>from</tt>.
	 * 
	 * @param from value to be assigned to property from
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * Getter method for property <tt>to</tt>.
	 * 
	 * @return property value of to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * Setter method for property <tt>to</tt>.
	 * 
	 * @param to value to be assigned to property to
	 */
	public void setTo(String to) {
		this.to = to;
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

	/**
	 * Getter method for property <tt>presendTime</tt>.
	 * 
	 * @return property value of presendTime
	 */
	public String getPresendTime() {
		return presendTime;
	}

	/**
	 * Setter method for property <tt>presendTime</tt>.
	 * 
	 * @param presendTime value to be assigned to property presendTime
	 */
	public void setPresendTime(String presendTime) {
		this.presendTime = presendTime;
	}

	/**
	 * Getter method for property <tt>expandPrefix</tt>.
	 * 
	 * @return property value of expandPrefix
	 */
	public String getExpandPrefix() {
		return expandPrefix;
	}

	/**
	 * Setter method for property <tt>expandPrefix</tt>.
	 * 
	 * @param expandPrefix value to be assigned to property expandPrefix
	 */
	public void setExpandPrefix(String expandPrefix) {
		this.expandPrefix = expandPrefix;
	}

	/**
	 * Getter method for property <tt>isvoice</tt>.
	 * 
	 * @return property value of isvoice
	 */
	public String getIsvoice() {
		return isvoice;
	}

	/**
	 * Setter method for property <tt>isvoice</tt>.
	 * 
	 * @param isvoice value to be assigned to property isvoice
	 */
	public void setIsvoice(String isvoice) {
		this.isvoice = isvoice;
	}

	/** 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GSTSendParam [username=" + username + ", password=" + password + ", from=" + from + ", to=" + to
				+ ", content=" + content + ", presendTime=" + presendTime + ", expandPrefix=" + expandPrefix
				+ ", isvoice=" + isvoice + "]";
	}

	/**
	 * 获取高斯通发送下行短信必要参数
	 * @return String
	 */
	public String getNeedParam() {
		return "username=" + this.getUsername() + "&password=" + this.getPassword() + "&to=" + this.getTo()
				+ "&content=" + this.getContent();
	}

}
