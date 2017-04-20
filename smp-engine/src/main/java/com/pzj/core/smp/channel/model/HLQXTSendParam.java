/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.smp.channel.model;

/**
 * 
 * @author Administrator
 * @version $Id: HLQXTChannelParam.java, v 0.1 2016年11月30日 下午2:42:36 Administrator Exp $
 */
public class HLQXTSendParam implements java.io.Serializable {

	private static final long serialVersionUID = -5658710465467089503L;

	/** 用户名 */
	private String username;
	/** 系统访问接口密码 */
	private String password;
	/** 要发送的手机号码 */
	private String phone;
	/** 短信内容 */
	private String message;
	/** 企业id */
	private Integer epid;
	/** 备用(发送唯一标识) */
	private String linkid;
	/** 扩展小号 */
	private String subcode;

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
	 * Getter method for property <tt>phone</tt>.
	 * 
	 * @return property value of phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Setter method for property <tt>phone</tt>.
	 * 
	 * @param phone value to be assigned to property phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Getter method for property <tt>message</tt>.
	 * 
	 * @return property value of message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Setter method for property <tt>message</tt>.
	 * 
	 * @param message value to be assigned to property message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Getter method for property <tt>epid</tt>.
	 * 
	 * @return property value of epid
	 */
	public Integer getEpid() {
		return epid;
	}

	/**
	 * Setter method for property <tt>epid</tt>.
	 * 
	 * @param epid value to be assigned to property epid
	 */
	public void setEpid(Integer epid) {
		this.epid = epid;
	}

	/**
	 * Getter method for property <tt>linkid</tt>.
	 * 
	 * @return property value of linkid
	 */
	public String getLinkid() {
		return linkid;
	}

	/**
	 * Setter method for property <tt>linkid</tt>.
	 * 
	 * @param linkid value to be assigned to property linkid
	 */
	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}

	/**
	 * Getter method for property <tt>subcode</tt>.
	 * 
	 * @return property value of subcode
	 */
	public String getSubcode() {
		return subcode;
	}

	/**
	 * Setter method for property <tt>subcode</tt>.
	 * 
	 * @param subcode value to be assigned to property subcode
	 */
	public void setSubcode(String subcode) {
		this.subcode = subcode;
	}

	/** 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HLQXTSendParam [username=" + username + ", password=" + password + ", phone=" + phone + ", message="
				+ message + ", epid=" + epid + ", linkid=" + linkid + ", subcode=" + subcode + "]";
	}

	/**
	 * 获取华联企信通发送下行短信必要参数
	 * @return String
	 */
	public String getSendParam() {
		return "username=" + this.getUsername() + "&password=" + this.getPassword() + "&phone=" + this.getPhone()
				+ "&message=" + this.getMessage() + "&epid=" + this.getEpid() + "&linkid=" + this.getLinkid();
	}

}
