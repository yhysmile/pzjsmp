package com.pzj.core.smp.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pzj.core.smp.entity.SmsChannel;

public interface SmsChannelReadMapper {
	public SmsChannel queryChannelInfoByType(@Param(value = "channelType") Integer channelType);

	public List<SmsChannel> queryAllChannelInfo();

	public SmsChannel queryChannelInfoById(@Param(value = "id") Long id);

}