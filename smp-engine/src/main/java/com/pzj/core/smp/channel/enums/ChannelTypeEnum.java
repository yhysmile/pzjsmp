package com.pzj.core.smp.channel.enums;

public enum ChannelTypeEnum {
	/**
	 * 鸿联企信通下行通道
	 */
	HLQXT_DOWNLINK(11, "鸿联企信通下行通道"),
	/**
	 * 鸿联企信通余额通道
	 */
	HLQXT_BALANCE(12, "鸿联企信通余额通道"),
	/**
	 * 高斯通下行通道
	 */
	GST_DOWNLINK(21, "高斯通下行通道"),
	/**
	 * 高斯通余额通道
	 */
	GST_BALANCE(22, "高斯通余额通道"),
	/**
	 * MAS政企云下行通道
	 */
	MAS_DOWNLINK(31, "MAS政企云下行通道");

	private Integer key;
	private String value;

	private ChannelTypeEnum(Integer key, String value) {
		this.key = key;
		this.value = value;
	}

	public Integer getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	/**
	 * 获取渠道类型
	 * @param key
	 * @return ChannelTypeEnum
	 */
	public static ChannelTypeEnum getChannelType(Integer key) {
		if (key == null) {
			return null;
		}
		ChannelTypeEnum[] enums = ChannelTypeEnum.values();
		for (ChannelTypeEnum channelType : enums) {
			if (key == channelType.getKey()) {
				return channelType;
			}
		}
		return null;
	}
}
