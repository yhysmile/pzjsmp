package com.pzj.core.smp.common.exception;

public class ParameterException extends SmpException {
	public ParameterException(SmpExceptionCode smpExceptionCode) {
		super(smpExceptionCode);
	}

	public ParameterException(Throwable cause) {
		super(cause);
	}
}
