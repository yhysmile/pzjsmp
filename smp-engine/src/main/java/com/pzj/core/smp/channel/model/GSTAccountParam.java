/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.smp.channel.model;

/**
 * 
 * @author Administrator
 * @version $Id: GSTAccountParam.java, v 0.1 2016年11月30日 下午2:48:51 Administrator Exp $
 */
public class GSTAccountParam implements java.io.Serializable {

	private static final long serialVersionUID = -9090012539092136608L;
	/**
	 * ISP登陆名(公司分配给ISP)，如果为机构版，则应以“机构ID:登陆名”方式赋值
	 */
	private String username;
	/**
	 * ISP登陆密码(公司分配给ISP)
	 */
	private String password;
	/**
	 * 内容返回格式,可选值xml(小写)
	 */
	private String msgType;

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
	 * Getter method for property <tt>msgType</tt>.
	 * 
	 * @return property value of msgType
	 */
	public String getMsgType() {
		return msgType;
	}

	/**
	 * Setter method for property <tt>msgType</tt>.
	 * 
	 * @param msgType value to be assigned to property msgType
	 */
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	/** 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GSTAccountParam [username=" + username + ", password=" + password + ", msgType=" + msgType + "]";
	}

	public String getNeedParam() {
		return "username=" + this.getUsername() + "&password=" + this.getPassword();
	}
}
