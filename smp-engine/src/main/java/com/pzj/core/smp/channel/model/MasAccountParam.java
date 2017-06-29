package com.pzj.core.smp.channel.model;

public class MasAccountParam implements java.io.Serializable {

	private static final long serialVersionUID = -5909826690693114233L;
	/**
	 * 身份认证地址
	 */
	private String url;
	/**
	 * 用户登录帐号
	 */
	private String userAccount;
	/**
	 * 用户登录密码
	 */
	private String password;
	/**
	 * 用户企业名称
	 */
	private String ecname;

	private static final String defaultEcname = "政企分公司测试";

	public MasAccountParam() {
		this.ecname = defaultEcname;
	}

	public MasAccountParam(String url, String userAccount, String password) {
		this.url = url;
		this.userAccount = userAccount;
		this.password = password;
		this.ecname = defaultEcname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEcname() {
		return ecname;
	}

	public void setEcname(String ecname) {
		this.ecname = ecname;
	}

}
