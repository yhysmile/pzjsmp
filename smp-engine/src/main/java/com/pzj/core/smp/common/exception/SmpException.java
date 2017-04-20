/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.smp.common.exception;

import com.pzj.framework.exception.ServiceException;

/**
 * 
 * @author Administrator
 * @version $Id: SmpException.java, v 0.1 2016年10月18日 下午4:40:17 Administrator Exp $
 */
public class SmpException extends ServiceException {
	private static final long serialVersionUID = 4276604059203200253L;

	private final int code;

	public SmpException() {
		super(SmpExceptionCode.ERROR.getMsg());
		this.code = SmpExceptionCode.ERROR.getCode();
	}

	public SmpException(SmpExceptionCode smpExceptionCode) {
		super(smpExceptionCode.getMsg());
		this.code = smpExceptionCode.getCode();
	}

	public SmpException(SmpExceptionCode smpExceptionCode, String message) {
		super(message);
		this.code = smpExceptionCode.getCode();
	}

	public SmpException(SmpExceptionCode smpExceptionCode, Object ... args) {
		super(smpExceptionCode.getTemplateMessage(args));
		this.code = smpExceptionCode.getCode();
	}

	/**
	 * @param cause
	 */
	public SmpException(Throwable cause) {
		super(cause.getMessage(), cause);
		this.code = SmpExceptionCode.ERROR.getCode();
	}

	public Integer getCode() {
		return code;
	}

}
