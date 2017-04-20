/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.smp.channel.model;

/**
 * 
 * @author Administrator
 * @version $Id: HLQXTAccountParam.java, v 0.1 2016年11月30日 下午2:48:37 Administrator Exp $
 */
public class HLQXTAccountParam implements java.io.Serializable {

	private static final long serialVersionUID = 8850557651297180014L;
	/** 用户名 */
	private String username;
	/** 系统访问接口密码 */
	private String password;
	/** 企业id */
	private Integer epid;

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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HLQXTAccountParam [username=" + username + ", password=" + password + ", epid=" + epid + "]";
	}

	/**
	 * 获取鸿联企信通账户必要参数
	 * @return String
	 */
	public String getAccountNeedParam() {
		return "username=" + this.getUsername() + "&password=" + this.getPassword();
	}
}
