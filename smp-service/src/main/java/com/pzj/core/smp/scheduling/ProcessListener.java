package com.pzj.core.smp.scheduling;

import com.pzj.core.smp.delivery.DeliveryInfoEntity;
import com.pzj.core.smp.message.MessageEntity;

/**
 * Created by Administrator on 2016-12-31.
 */
interface ProcessListener {

	/**
	 * 处理消息
	 *
	 * @param deliveryInfo
	 * @param message
	 */
	void processMessage(DeliveryInfoEntity deliveryInfo, MessageEntity message);
}