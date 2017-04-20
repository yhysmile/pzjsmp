package com.pzj.core.smp.scheduling;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.pzj.core.smp.delivery.DeliveryAddressEntity;
import com.pzj.core.smp.delivery.DeliveryService;

/**
 * Created by Administrator on 2016-12-29.
 */
public class SchedulingService {
	/**
	 * 指引方向
	 * <p>
	 *     找到消息投递到的队列
	 * </p>
	 * @param deliveryAddress
	 * @return
	 */
	public SchedulingQueueEntity guidanceDirection(DeliveryAddressEntity deliveryAddress) {
		return new SchedulingQueueEntity(deliveryAddress.fullName());
	}

	public List<String> namesOfDeliveryAndOrder(Set<DeliveryAddressEntity> deliveryAddresses) {
		if (deliveryAddresses == null || deliveryAddresses.isEmpty())
			return null;

		TreeSet<DeliveryAddressEntity> addresses = new TreeSet<>(deliveryAddresses);
		List<String> queues = new ArrayList<>();
		for (DeliveryAddressEntity address : addresses) {
			queues.add(address.fullName());
		}

		return queues;
	}

	public String[] namesOfBusiness(String businessName) {
		Set<DeliveryAddressEntity> addresses = DeliveryService.buildAddressFromBussiness(businessName);
		List<String> queueNameList = namesOfDeliveryAndOrder(addresses);
		String[] queueNames = new String[queueNameList.size()];
		queueNameList.toArray(queueNames);
		return queueNames;
	}

}
