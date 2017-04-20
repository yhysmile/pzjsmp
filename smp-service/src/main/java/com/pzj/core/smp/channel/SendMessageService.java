package com.pzj.core.smp.channel;

import com.pzj.core.smp.channel.model.ChannelSendMessage;

public interface SendMessageService {
	/**
	 * 通道发送短信
	 * @param sendMessage
	 * @return Boolean true:成功，false:失败
	 */
	public Boolean sendMessage(ChannelSendMessage sendMessage);
}
