package com.pzj.core.smp.channel.business;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pzj.core.smp.channel.common.ObjUtils;
import com.pzj.core.smp.channel.enums.ChannelStateEnum;
import com.pzj.core.smp.channel.model.ChannelInfo;
import com.pzj.core.smp.channel.model.ChannelInfoQuery;
import com.pzj.core.smp.entity.SmsChannel;
import com.pzj.core.smp.read.SmsChannelReadMapper;

@Component("channelQueryManage")
public class ChannelQueryManage {
	@Resource(name = "smsChannelReadMapper")
	private SmsChannelReadMapper smsChannelReadMapper;

	/**
	 * 通过类型获取通道
	 * @param channelInfoQuery
	 * @return ChannelInfo
	 */
	public ChannelInfo queryChannelInfoByType(ChannelInfoQuery channelInfoQuery) {
		SmsChannel smsChannel = smsChannelReadMapper.queryChannelInfoByType(channelInfoQuery.getType());

		return initChannelInfo(smsChannel);
	}

	/**
	 * 查询所有通道
	 * @return List<ChannelInfo>
	 */
	public List<ChannelInfo> queryAllChannelInfo() {
		List<SmsChannel> smsChannels = smsChannelReadMapper.queryAllChannelInfo();

		if (!ObjUtils.checkCollectionIsNull(smsChannels)) {
			List<ChannelInfo> channelInfos = new ArrayList<ChannelInfo>();
			for (SmsChannel smsChannel : smsChannels) {

				channelInfos.add(initChannelInfo(smsChannel));
			}
			return channelInfos;
		}
		return null;
	}

	/**
	 * 通过主键查找通道信息
	 * @param channelInfoQuery
	 * @return ChannelInfo
	 */
	public ChannelInfo queryChannelInfoById(ChannelInfoQuery channelInfoQuery) {
		SmsChannel smsChannel = smsChannelReadMapper.queryChannelInfoById(channelInfoQuery.getId());

		return initChannelInfo(smsChannel);
	}

	/**
	 * 初始化通道信息
	 * @param smsChannel
	 * @return ChannelInfo
	 */
	private ChannelInfo initChannelInfo(SmsChannel smsChannel) {
		if (ObjUtils.checkObjectIsNull(smsChannel) || smsChannel.getState() != ChannelStateEnum.AVAILABLE.getState()) {
			return null;
		}
		ChannelInfo channelInfo = new ChannelInfo();
		channelInfo.setId(smsChannel.getId());
		channelInfo.setName(smsChannel.getName());
		channelInfo.setChannelType(smsChannel.getChannelType());
		channelInfo.setPriorityProportion(smsChannel.getPriorityProportion());
		channelInfo.setState(smsChannel.getState());
		channelInfo.setUrl(smsChannel.getUrl());

		return channelInfo;
	}
}
