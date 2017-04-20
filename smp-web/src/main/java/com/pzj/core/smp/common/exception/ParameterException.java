/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.smp.common.exception;

import com.pzj.framework.exception.ServiceException;

/**
 * 
 * @author Administrator
 * @version $Id: ParameterException.java, v 0.1 2016年10月18日 下午4:40:17 Administrator Exp $
 */
public class ParameterException extends ServiceException {
	private static final long serialVersionUID = 4276604059203200253L;

	private final int code;

	public ParameterException() {
		super(SmpExceptionCode.ERROR.getMsg());
		this.code = SmpExceptionCode.ERROR.getCode();
	}

	public ParameterException(SmpExceptionCode smpExceptionCode) {
		super(smpExceptionCode.getMsg());
		this.code = smpExceptionCode.getCode();
	}

	public ParameterException(SmpExceptionCode smpExceptionCode, String message) {
		super(message);
		this.code = smpExceptionCode.getCode();
	}

	/**
	 * @param cause
	 */
	public ParameterException(Throwable cause) {
		super(cause.getMessage(), cause);
		this.code = SmpExceptionCode.ERROR.getCode();
	}

	public Integer getCode() {
		return code;
	}

}
