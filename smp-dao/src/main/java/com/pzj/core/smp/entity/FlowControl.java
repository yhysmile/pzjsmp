package com.pzj.core.smp.entity;

import java.util.Date;

public class FlowControl implements java.io.Serializable {
	private static final long serialVersionUID = 4955433131939384484L;

	private Long id;

	private Long channelId;

	private Boolean timeUnit;

	private Boolean category;

	private Integer limitThreshold;

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

	public Boolean getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(Boolean timeUnit) {
		this.timeUnit = timeUnit;
	}

	public Boolean getCategory() {
		return category;
	}

	public void setCategory(Boolean category) {
		this.category = category;
	}

	public Integer getLimitThreshold() {
		return limitThreshold;
	}

	public void setLimitThreshold(Integer limitThreshold) {
		this.limitThreshold = limitThreshold;
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