package com.pzj.core.smp.delivery;

import com.pzj.core.smp.common.exception.SmpException;
import com.pzj.core.smp.common.exception.SmpExceptionCode;
import com.pzj.framework.exception.ServiceException;

/**
 * Created by Administrator on 2016-12-30.
 */
public enum DeliveryPriorityEnum {
	A(1), B(2), C(3);

	private int value;

	DeliveryPriorityEnum(int value) {
		this.value = value;
	}

	public static DeliveryPriorityEnum valueOf(int value) {
		switch (value) {
		case 1:
			return A;
		case 2:
			return B;
		case 3:
			return C;
		default:
			throw new SmpException(SmpExceptionCode.DELIVERY_PRIORITY_INVALID, (Object) value);
		}
	}

	public static DeliveryPriorityEnum of(String name) {
		try {
			return valueOf(name);
		} catch (Throwable t){
			throw new SmpException(SmpExceptionCode.DELIVERY_PRIORITY_INVALID, (Object) name);
		}
	}
}
