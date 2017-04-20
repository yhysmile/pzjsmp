package com.pzj.core.smp.channel.business;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pzj.core.smp.channel.common.ObjUtils;
import com.pzj.core.smp.channel.model.ChannelUserinfo;
import com.pzj.core.smp.channel.model.ChannelUserinfoQuery;
import com.pzj.core.smp.entity.SmsChannelUserinfo;
import com.pzj.core.smp.read.SmsChannelUserinfoReadMapper;

@Component("channelUserinfoQueryManage")
public class ChannelUserinfoQueryManage {
	@Resource(name = "smsChannelUserinfoReadMapper")
	private SmsChannelUserinfoReadMapper smsChannelUserinfoReadMapper;

	/**
	 * 根据通道标识获取通道用户信息
	 * @param queryModel
	 * @return ChannelUserinfo
	 */
	public ChannelUserinfo queryUserByChannelIdentity(ChannelUserinfoQuery queryModel) {
		SmsChannelUserinfo smsChannelUserinfo = smsChannelUserinfoReadMapper
				.queryUserByChannelIndetity(queryModel.getIndentity());

		return initChannelUserinfo(smsChannelUserinfo);
	}

	/**
	 * 获取所有通道用户信息
	 * @return List<ChannelUserinfo>
	 */
	public List<ChannelUserinfo> queryAllChannelUserinfo() {
		List<SmsChannelUserinfo> smsChannelUserinfos = smsChannelUserinfoReadMapper.queryAllChannelUser();

		if (!ObjUtils.checkCollectionIsNull(smsChannelUserinfos)) {
			List<ChannelUserinfo> channelUserinfos = new ArrayList<ChannelUserinfo>();
			for (SmsChannelUserinfo smsChannelUserinfo : smsChannelUserinfos) {
				channelUserinfos.add(initChannelUserinfo(smsChannelUserinfo));
			}
			return channelUserinfos;
		}
		return null;
	}

	/**
	 * 初始化通道用户信息
	 * @param smsChannelUserinfo
	 * @return ChannelUserinfo
	 */
	private ChannelUserinfo initChannelUserinfo(SmsChannelUserinfo smsChannelUserinfo) {
		if (ObjUtils.checkObjectIsNull(smsChannelUserinfo)) {
			return null;
		}
		ChannelUserinfo channelUserinfo = new ChannelUserinfo();
		channelUserinfo.setBalance(smsChannelUserinfo.getBalance());
		channelUserinfo.setChannelIdentity(smsChannelUserinfo.getChannelIdentity());
		channelUserinfo.setPassword(smsChannelUserinfo.getPassword());
		channelUserinfo.setUsername(smsChannelUserinfo.getUsername());
		return channelUserinfo;
	}
}
