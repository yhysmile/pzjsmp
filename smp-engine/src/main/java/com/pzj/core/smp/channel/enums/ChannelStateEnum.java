package com.pzj.core.smp.channel.enums;

public enum ChannelStateEnum {
	/**
	 * 可用
	 */
	AVAILABLE(1, "可用"),
	/**
	 * 不可用
	 */
	DISABLED(2, "停用");

	private Integer state;
	private String desc;

	private ChannelStateEnum(Integer state, String desc) {
		this.state = state;
		this.desc = desc;
	}

	public Integer getState() {
		return state;
	}

	public String getDesc() {
		return desc;
	}

}
