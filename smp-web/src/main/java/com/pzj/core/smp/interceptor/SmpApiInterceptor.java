/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.smp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.pzj.framework.converter.JSONConverter;

/**
 * 
 * @author Mark
 * @version $Id: SmpApiInterceptor.java, v 0.1 2016年7月4日 下午6:30:45 pengliqing Exp $
 */
public class SmpApiInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(SmpApiInterceptor.class);

	/** 
	* preHandle方法是进行处理器拦截用的，该方法将在Controller处理之前进行调用，SpringMVC中的Interceptor拦截器是链式的，可以同时存在 
	* 多个Interceptor，然后SpringMVC会根据声明的前后顺序一个接一个的执行，而且所有的Interceptor中的preHandle方法都会在 
	* Controller方法调用之前调用。SpringMVC的这种Interceptor链式结构也是可以进行中断的，这种中断方式是令preHandle的返 
	* 回值为false，当preHandle的返回值为false的时候整个请求就结束了。 
	*/
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		if (handler instanceof HandlerMethod) {
			try {
				String data = request.getParameter("data");
				RequestCheck.checkData(data);
				JSONObject requestObject = RequestCheck.getRequestData(data);
				request.setAttribute("requestObject", requestObject);
				logger.debug("SmpApiInterceptor preHandle requestObject is {}", JSONConverter.toJson(requestObject));
			} catch (Throwable t) {
				logger.error("SmpApiInterceptor preHandle data is null");
				request.setAttribute("requestObject", new JSONObject());
			}

		}
		return true;
	}
}
