package com.pzj.core.smp.delivery;

import java.util.Date;

import com.pzj.core.smp.common.exception.SmpException;
import com.pzj.core.smp.common.exception.SmpExceptionCode;

/**
 * Created by Administrator on 2017-1-6.
 */
public class DeliveryInfoEntity {
	//
	private DeliveryAddressEntity address;
	// 创建时间
	private Date createDate;
	// 超时时间，毫秒
	private Long timeOut;

	public DeliveryAddressEntity address() {
		return address;
	}

	public Date createDate() {
		return createDate;
	}

	public Long timeOut() {
		return timeOut;
	}

	public DeliveryInfoEntity(DeliveryAddressEntity address, Date createDate, Long timeOut) {
		setAddress(address);
		setCreateDate(createDate);
		setTimeOut(timeOut);
	}

	public void check() {
		long timeDiff = System.currentTimeMillis() - createDate.getTime();
		if (timeDiff >= timeOut)
			throw new DeliveryTimeOutException(this, timeDiff);
	}

	private void setAddress(DeliveryAddressEntity address) {
		if (address == null) {
			throw new SmpException(SmpExceptionCode.DELIVERY_ADDRESS_NULL);
		}
		this.address = address;
	}

	private void setCreateDate(Date createDate) {
		if (createDate == null) {
			throw new SmpException(SmpExceptionCode.DELIVERY_CREATEDATE_Null);
		}
		this.createDate = createDate;
	}

	private void setTimeOut(Long timeOut) {
		if (timeOut == null) {
			throw new SmpException(SmpExceptionCode.DELIVERY_TIMEOUT_Null);
		}
		this.timeOut = timeOut;
	}
}
