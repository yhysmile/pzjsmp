package com.pzj.core.smp.delivery;

import com.pzj.core.smp.common.exception.SmpException;
import com.pzj.core.smp.common.exception.SmpExceptionCode;

/**
 * 消息投递地址
 * Created by Administrator on 2016-12-29.
 */
public class DeliveryAddressEntity implements Comparable<DeliveryAddressEntity> {
	// 业务名
	private String businessName;
	// 优先级
	private DeliveryPriorityEnum priority;

	public DeliveryAddressEntity(String businessName, DeliveryPriorityEnum priority) {
		setBusinessName(businessName);
		setPriority(priority);
	}

	public String businessName() {
		return this.businessName;
	}

	public DeliveryPriorityEnum priority() {
		return this.priority;
	}

	public String fullName() {
		return businessName + ":" + priority;
	}

	@Override
	public int compareTo(DeliveryAddressEntity o) {
		if (o == null) {
			return 1;
		}
		int businessNameCompare = this.businessName.compareTo(o.businessName());
		if (businessNameCompare != 0) {
			return businessNameCompare;
		}
		int priorityCompare = this.priority.compareTo(o.priority());
		return priorityCompare;
	}

	private void setBusinessName(String businessName) {
		if (businessName == null || businessName.isEmpty()) {
			throw new SmpException(SmpExceptionCode.DELIVERY_BUSINESSNAME_NULL);
		}
		this.businessName = businessName;
	}

	private void setPriority(DeliveryPriorityEnum priority) {
		if (priority == null) {
			throw new SmpException(SmpExceptionCode.DELIVERY_PRIORITY_NULL);
		}
		this.priority = priority;
	}
}
