package com.pzj.core.smp.channel.model;

public class ChannelUserinfo implements java.io.Serializable {

	private static final long serialVersionUID = -6216921476153876225L;
	/**
	 * 通道标识
	 */
	private Integer channelIdentity;
	/**
	 * 通道账号
	 */
	private String username;
	/**
	 * 通道密码
	 */
	private String password;
	/**
	 * 通道余额
	 */
	private Integer balance;

	public Integer getChannelIdentity() {
		return channelIdentity;
	}

	public void setChannelIdentity(Integer channelIdentity) {
		this.channelIdentity = channelIdentity;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

}
