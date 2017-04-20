/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.smp.interceptor;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.pzj.core.smp.common.exception.ParameterException;
import com.pzj.core.smp.common.exception.SmpExceptionCode;
import com.pzj.framework.exception.ServiceException;
import com.pzj.framework.toolkit.Check;

/**
 * 
 * @author Mark
 * @version $Id: ApiRequestSupport.java, v 0.1 2016年7月5日 上午11:56:17 pengliqing Exp $
 */
public class RequestCheck {
	private static final Logger logger = LoggerFactory.getLogger(RequestCheck.class);

	/**
	 * 请求参数权限校验
	 * @param data
	 * @throws UnsupportedEncodingException
	 * */
	public static final void checkData(String data) throws UnsupportedEncodingException {
		if (Check.NuNStrStrict(data) || data.length() == 0) {
			throw new ParameterException(SmpExceptionCode.PARAMS_NULL);
		}
	}

	/**
	 * 获取请求参数实体
	 *
	 * @param data 请求参数字符串
	 * @return
	 * @throws UnsupportedEncodingException
	 * */
	public static final JSONObject getRequestData(String data) throws UnsupportedEncodingException {
		if (Check.NuNStrStrict(data)) {
			throw new ParameterException(SmpExceptionCode.PARAMS_NULL);
		}
		try {
			return JSONObject.parseObject(data);

		} catch (Throwable t) {
			logger.error("RequestCheck getRequestData is error {}", data);
			throw t instanceof ServiceException ? (ServiceException) t : new ParameterException(t);
		}

	}
}
