package com.pzj.core.smp.delivery;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

/**
* Created by Administrator on 2016-12-29.
*/
@Service("deliveryService")
public class DeliveryService {
	public static Set<DeliveryAddressEntity> buildAddressFromBussiness(String businessName) {
		Set<DeliveryAddressEntity> addresses = new HashSet<>(3);
		addresses.add(new DeliveryAddressEntity(businessName, DeliveryPriorityEnum.A));
		addresses.add(new DeliveryAddressEntity(businessName, DeliveryPriorityEnum.B));
		addresses.add(new DeliveryAddressEntity(businessName, DeliveryPriorityEnum.C));
		return addresses;
	}
}
