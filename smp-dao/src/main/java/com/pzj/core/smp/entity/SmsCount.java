package com.pzj.core.smp.entity;

import java.util.Date;

public class SmsCount implements java.io.Serializable {
	private static final long serialVersionUID = -14965294765723413L;

	private Long id;

	private Long channelId;

	private Integer okNum;

	private Integer errNum;

	private Integer period;

	private Date createTime;

	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public Integer getOkNum() {
		return okNum;
	}

	public void setOkNum(Integer okNum) {
		this.okNum = okNum;
	}

	public Integer getErrNum() {
		return errNum;
	}

	public void setErrNum(Integer errNum) {
		this.errNum = errNum;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
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
}