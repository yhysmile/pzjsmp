package com.pzj.core.smp.util;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pzj.core.smp.common.exception.SmpException;
import com.pzj.core.smp.common.exception.SmpExceptionCode;
import com.pzj.framework.context.Result;

/**
 * Created by Administrator on 2017-1-12.
 */
public abstract class RpcCaller<T extends Serializable> {
	private static Logger logger = LoggerFactory.getLogger(RpcCaller.class);

	public abstract T call();

	public static <T extends Serializable> Result<T> call(RpcCaller<T> rpcCaller) {
		Result<T> result = new Result<>();
		try {
			result.setData(rpcCaller.call());
		} catch (SmpException e) {
			result.setErrorCode(e.getCode());
			result.setErrorMsg(e.getMessage());
			logger.error(e.getMessage(), e);
		} catch (Throwable throwable) {
			result.setErrorCode(SmpExceptionCode.ERROR.getCode());
			result.setErrorMsg(SmpExceptionCode.ERROR.getMsg());
			logger.error(throwable.getMessage(), throwable);
		}
		return result;
	}

}
