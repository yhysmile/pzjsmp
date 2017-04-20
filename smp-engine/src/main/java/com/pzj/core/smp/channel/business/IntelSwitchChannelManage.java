package com.pzj.core.smp.channel.business;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.smp.channel.enums.ChannelStateEnum;
import com.pzj.core.smp.channel.model.ChannelInfo;
import com.pzj.core.smp.channel.model.ChannelInfoQuery;
import com.pzj.core.smp.channel.model.ChannelRetryParam;
import com.pzj.framework.converter.JSONConverter;

@Component("intelSwitchChannelManage")
public class IntelSwitchChannelManage {
	private final Logger logger = LoggerFactory.getLogger(IntelSwitchChannelManage.class);
	@Resource(name = "channelQueryManage")
	private ChannelQueryManage channelQueryManage;
	@Resource(name = "filterChannelManage")
	private FilterChannelManage filterChannelManage;

	/**
	 * 智能切换短信通道
	 * @param channelRetry
	 * @return ChannelInfo
	 */
	public ChannelInfo failRetryReboot(ChannelRetryParam channelRetry) {
		logger.info("invoke send message intel switch,request param:{}", JSONConverter.toJson(channelRetry));

		ChannelInfo channelInfo = computeChannel(channelRetry);
		if (logger.isDebugEnabled()) {
			logger.debug("invoke send message intel switch success,result:{}", JSONConverter.toJson(channelInfo));
		}

		return channelInfo;
	}

	/**
	 * 过滤可用通道
	 * @param downlinkChannels
	 * @param channelRetry
	 */
	private void filterChannel(List<ChannelInfo> downlinkChannels, ChannelRetryParam channelRetry) {
		Map<Long, Integer> retryChannelMap = channelRetry.getRetryChannelMap();
		Iterator<ChannelInfo> itera = downlinkChannels.iterator();
		ChannelInfo channelInfo = null;
		Integer sendNum = null;
		while (itera.hasNext()) {
			channelInfo = itera.next();
			sendNum = retryChannelMap.get(channelInfo.getId());
			if (null != sendNum && (sendNum == -1 || sendNum == 2)) {
				itera.remove();
			}
		}
	}

	/**
	 * 智能筛选可用通道
	 * @param channelRetry
	 * @return ChannelInfo
	 */
	public ChannelInfo computeChannel(ChannelRetryParam channelRetry) {
		Integer retryNum = channelRetry.getRetryChannelMap().get(channelRetry.getChannelId());
		boolean isFilterChannel = Boolean.FALSE;

		//重试当前使用通道继续重试
		ChannelInfo channelInfo = null;
		if (null == retryNum || retryNum == 1) {
			isFilterChannel = checkCurChannel(channelInfo, channelRetry.getChannelId());
		} else {//切换最优通道
			isFilterChannel = Boolean.TRUE;
		}

		if (isFilterChannel) {
			//获取所有可用发送通道列表
			List<ChannelInfo> downlinkChannels = filterChannelManage.getDownlinkChannels();
			//过滤发送异常的通道
			filterChannel(downlinkChannels, channelRetry);

			channelInfo = filterChannelManage.getChannelByWeight(downlinkChannels);
		}

		return channelInfo;
	}

	/**
	 * 检查当前通道是否可用
	 * @param channelInfo
	 * @param channelId
	 * @return Boolean true:可用；FALSE:不可用
	 */
	private Boolean checkCurChannel(ChannelInfo channelInfo, Long channelId) {
		ChannelInfoQuery channelInfoQuery = new ChannelInfoQuery();
		channelInfoQuery.setId(channelId);
		channelInfo = channelQueryManage.queryChannelInfoById(channelInfoQuery);
		if (null == channelInfo || channelInfo.getState() != ChannelStateEnum.AVAILABLE.getState()) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
}
