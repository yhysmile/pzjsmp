package com.pzj.core.smp.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pzj.core.smp.entity.SmsChannelUserinfo;

public interface SmsChannelUserinfoReadMapper {
	SmsChannelUserinfo queryUserByChannelIndetity(@Param(value = "channel_identity") Integer channelIdentity);

	List<SmsChannelUserinfo> queryAllChannelUser();
}