package com.pzj.core.smp.channel.enums;

public enum ChannelIndentityEnum {
	HLQXT(1, "鸿联企信通短信通道"), GST(2, "高斯通短信通道"), MAS(3, "MAS政企云短信通道");

	private Integer key;
	private String value;

	private ChannelIndentityEnum(Integer key, String value) {
		this.key = key;
		this.value = value;
	}

	public Integer getKey() {
		return key;
	}

	public String getIdentity() {
		return value;
	}

	/**
	 * 根据key 获取渠道标识
	 * @param key
	 * @return ChannelIndentityEnum
	 */
	public static ChannelIndentityEnum getChannelIdentity(Integer key) {
		if (null == key) {
			return null;
		}
		ChannelIndentityEnum[] channelIdentitys = ChannelIndentityEnum.values();
		for (ChannelIndentityEnum channelIden : channelIdentitys) {
			if (key == channelIden.key) {
				return channelIden;
			}
		}
		return null;
	}

}
