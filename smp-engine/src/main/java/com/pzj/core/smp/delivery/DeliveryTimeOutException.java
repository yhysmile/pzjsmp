package com.pzj.core.smp.delivery;

import com.pzj.core.smp.common.exception.SmpException;
import com.pzj.core.smp.common.exception.SmpExceptionCode;

/**
 * Created by Administrator on 2017-1-6.
 */
public class DeliveryTimeOutException extends SmpException {

	public DeliveryTimeOutException(DeliveryInfoEntity deliveryInfo, Long timeOutDiff) {
		super(SmpExceptionCode.DELIVERY_TIMEOUT, SmpExceptionCode.DELIVERY_TIMEOUT
				.getTemplateMessage(deliveryInfo.createDate(), deliveryInfo.timeOut(), timeOutDiff));
	}
}
