package com.pzj.core.smp.scheduling;

import com.pzj.core.smp.delivery.DeliveryInfoEntity;
import com.pzj.core.smp.message.MessageEntity;

/**
 * Created by Administrator on 2017-1-3.
 */
class QueueData {
	private String transactionId = null;
	private MessageEntity message = null;
	private DeliveryInfoEntity deliveryInfo = null;

	public MessageEntity getMessage() {
		return message;
	}

	public void setMessage(MessageEntity message) {
		this.message = message;
	}

	public DeliveryInfoEntity getDeliveryInfo() {
		return deliveryInfo;
	}

	public void setDeliveryInfo(DeliveryInfoEntity deliveryInfo) {
		this.deliveryInfo = deliveryInfo;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
}
