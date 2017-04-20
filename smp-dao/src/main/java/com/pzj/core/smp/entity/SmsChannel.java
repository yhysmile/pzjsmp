package com.pzj.core.smp.entity;

import java.util.Date;

public class SmsChannel implements java.io.Serializable {
	private static final long serialVersionUID = 1280408397496714087L;

	private Long id;

	private String name;

	private Integer state;

	private String url;

	private Date createTime;

	private Date updateTime;

	private Integer channelType;

	private Integer priorityProportion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
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

	public Integer getPriorityProportion() {
		return priorityProportion;
	}

	public void setPriorityProportion(Integer priorityProportion) {
		this.priorityProportion = priorityProportion;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getChannelType() {
		return channelType;
	}

	public void setChannelType(Integer channelType) {
		this.channelType = channelType;
	}

}