package com.pzj.core.smp.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pzj.core.smp.delivery.DeliveryAddressEntity;
import com.pzj.core.smp.delivery.DeliveryInfoEntity;
import com.pzj.core.smp.delivery.DeliveryPriorityEnum;
import com.pzj.core.smp.delivery.MessageHead;
import com.pzj.core.smp.message.MessageEntity;

/**
 * Created by Administrator on 2016-12-29.
 */
public class ModelBuilder {
	private static final Logger logger = LoggerFactory.getLogger(ModelBuilder.class);

	public static DeliveryInfoEntity createDeliveryInfo(MessageHead messageHead) {
		DeliveryAddressEntity address = createDeliveryAddress(messageHead);
		DeliveryInfoEntity deliveryInfo = new DeliveryInfoEntity(address, messageHead.getCreateDate(),
				messageHead.getTimeOut());
		return deliveryInfo;
	}

	public static DeliveryAddressEntity createDeliveryAddress(MessageHead messageHead) {
		DeliveryPriorityEnum priority = DeliveryPriorityEnum.of(messageHead.getPriority());
		DeliveryAddressEntity channel = new DeliveryAddressEntity(messageHead.getBusinessName(), priority);
		return channel;
	}

	public static MessageEntity createShortMessage(List<String> phoneNums, String renderContent) {
		MessageEntity message = new MessageEntity(phoneNums, renderContent);
		return message;
	}

	public static DeliveryAddressEntity createDeliveryAddress(String busniessName, String priority) {
		DeliveryAddressEntity address = new DeliveryAddressEntity(busniessName, DeliveryPriorityEnum.valueOf(priority));
		return address;
	}
}
