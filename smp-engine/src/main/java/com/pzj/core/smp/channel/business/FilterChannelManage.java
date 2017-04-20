package com.pzj.core.smp.channel.business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pzj.core.smp.channel.common.ObjUtils;
import com.pzj.core.smp.channel.enums.ChannelStateEnum;
import com.pzj.core.smp.channel.enums.ChannelTypeEnum;
import com.pzj.core.smp.channel.model.ChannelInfo;
import com.pzj.core.smp.common.exception.SmpException;
import com.pzj.core.smp.common.exception.SmpExceptionCode;

@Component("filterChannelManage")
public class FilterChannelManage {

	@Resource(name = "channelQueryManage")
	private ChannelQueryManage channelQueryManage;

	private static Integer defaultPos = 0;

	/**
	 * 默认通道轮询获取通道信息
	 * @return ChannelInfo
	 */
	public ChannelInfo filterChannelByDefault() {
		List<ChannelInfo> downlinkChannels = getDownlinkChannels();

		ChannelInfo defaultChannel = null;
		synchronized (FilterChannelManage.class) {
			if (defaultPos >= downlinkChannels.size()) {
				defaultPos = 0;
			}
			defaultChannel = downlinkChannels.get(defaultPos);
			defaultPos++;
		}
		return defaultChannel;
	}

	/**
	 * 按优先级比重获取通道信息
	 * @return ChannelInfo
	 */
	public ChannelInfo filterChannelByWeight() {
		List<ChannelInfo> downlinkChannels = getDownlinkChannels();

		return getChannelByWeight(downlinkChannels);
	}

	/**
	 * 按权重筛选最优通道
	 * @param downlinkChannels
	 * @return ChannelInfo
	 */
	public ChannelInfo getChannelByWeight(List<ChannelInfo> downlinkChannels) {
		if (ObjUtils.checkCollectionIsNull(downlinkChannels)) {
			return null;
		}

		Iterator<ChannelInfo> itera = downlinkChannels.iterator();
		List<ChannelInfo> tempChannels = new ArrayList<ChannelInfo>();
		ChannelInfo tempChannel = null;
		while (itera.hasNext()) {
			tempChannel = itera.next();
			int weight = ObjUtils.checkIntegerIsNull(tempChannel.getPriorityProportion(), true) ? 1
					: tempChannel.getPriorityProportion();
			while (weight-- > 0) {
				tempChannels.add(tempChannel);
			}
		}

		Random random = new Random();
		int rand = random.nextInt(tempChannels.size());
		return tempChannels.get(rand);
	}

	/**
	 * 获取所有下行短信通道
	 * @return List<ChannelInfo> 
	 */
	public List<ChannelInfo> getDownlinkChannels() {
		List<ChannelInfo> channelInfos = channelQueryManage.queryAllChannelInfo();
		if (ObjUtils.checkCollectionIsNull(channelInfos)) {
			throw new SmpException(SmpExceptionCode.NO_AVAIABLE_CHANNEL_ERR);
		}

		Iterator<ChannelInfo> itera = channelInfos.iterator();
		ChannelInfo channelInfo = null;
		while (itera.hasNext()) {
			channelInfo = itera.next();
			if (ObjUtils.checkObjectIsNull(channelInfo) || !judgeDownlinkChannel(channelInfo.getChannelType())
					|| !checkChannelState(channelInfo.getState())) {
				itera.remove();
				continue;
			}
		}

		if (ObjUtils.checkCollectionIsNull(channelInfos)) {
			throw new SmpException(SmpExceptionCode.NO_AVAIABLE_CHANNEL_ERR);
		}
		return channelInfos;
	}

	/**
	 * 检查通道状态是否可用
	 * @param state
	 * @return Boolean
	 */
	private Boolean checkChannelState(Integer state) {
		Boolean flag = Boolean.FALSE;
		if (ObjUtils.checkIntegerIsNull(state, Boolean.TRUE)) {
			return flag;
		}
		if (ChannelStateEnum.AVAILABLE.getState() == state) {
			flag = Boolean.TRUE;
		}
		return flag;
	}

	/**
	 * 判断是否是下行短信通道
	 * @param channelType
	 * @return Boolean
	 */
	private Boolean judgeDownlinkChannel(Integer channelType) {
		Boolean flag = Boolean.FALSE;
		if (ObjUtils.checkIntegerIsNull(channelType, Boolean.TRUE)) {
			return flag;
		}
		if (channelType == ChannelTypeEnum.HLQXT_DOWNLINK.getKey()
				|| channelType == ChannelTypeEnum.GST_DOWNLINK.getKey()) {
			flag = Boolean.TRUE;
		}
		return flag;
	}
}
