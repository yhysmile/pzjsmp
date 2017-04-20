package com.pzj.core.smp.entity;

import java.util.Date;

public class SmsChannelUserinfo implements java.io.Serializable {
	private static final long serialVersionUID = -3663435300689510731L;

	private Long id;

	private Integer channelIdentity;

	private String username;

	private String password;

	private Date createTime;

	private Date updateTime;

	private Integer balance;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
		this.username = username == null ? null : username.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

}