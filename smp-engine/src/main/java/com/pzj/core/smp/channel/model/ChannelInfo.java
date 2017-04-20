package com.pzj.core.smp.channel.model;

public class ChannelInfo implements java.io.Serializable {

	private static final long serialVersionUID = -684309129922778739L;
	/**
	 * 通道id
	 */
	private Long id;
	/**
	 * 通道名称
	 */
	private String name;
	/**
	 * 通道状态
	 */
	private Integer state;
	/**
	 * 通道访问地址
	 */
	private String url;
	/**
	 * 通道类型；11:鸿联企信通下行;12:鸿联企信通余额;13:鸿联企信通上行;21:高斯通下行;22:高斯通余额;23:高斯通上行
	 */
	private Integer channelType;
	/**
	 * 通道分配权重
	 */
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
		this.name = name;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getChannelType() {
		return channelType;
	}

	public void setChannelType(Integer channelType) {
		this.channelType = channelType;
	}

	public Integer getPriorityProportion() {
		return priorityProportion;
	}

	public void setPriorityProportion(Integer priorityProportion) {
		this.priorityProportion = priorityProportion;
	}

	@Override
	public String toString() {
		return "ChannelInfo [id=" + id + ", name=" + name + ", state=" + state + ", url=" + url + ", channelType="
				+ channelType + ", priorityProportion=" + priorityProportion + "]";
	}

}
