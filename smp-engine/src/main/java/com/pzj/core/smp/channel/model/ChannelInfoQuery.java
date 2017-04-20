package com.pzj.core.smp.channel.model;

public class ChannelInfoQuery implements java.io.Serializable {

	private static final long serialVersionUID = -5793525748428731630L;
	/**
	 * 通道类型；11:鸿联企信通下行;12:鸿联企信通余额;13:鸿联企信通上行;21:高斯通下行;22:高斯通余额;23:高斯通上行
	 */
	private Integer type;
	/** 通道id*/
	private Long id;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
