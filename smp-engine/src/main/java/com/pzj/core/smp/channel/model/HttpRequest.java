/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.smp.channel.model;

/**
 * 
 * @author Administrator
 * @version $Id: HttpRequestModel.java, v 0.1 2016年12月4日 下午10:42:25 Administrator Exp $
 */
public class HttpRequest implements java.io.Serializable {

	private static final long serialVersionUID = -9174451931868922743L;
	/**
	 * 封装请求路径
	 */
	private String url;
	/**
	 * 针对post请求设置的远程访问输入参数流
	 */
	private String params;
	/** 请求编码*/
	private String reqCharacter;
	/** 响应返回结果解析编码*/
	private String respCharacter;

	//	/** 是否缓冲读取,默认是false，true：走缓冲，false：不走缓冲*/
	//	private Boolean bufferRead;

	/**
	 * Getter method for property <tt>url</tt>.
	 * 
	 * @return property value of url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Setter method for property <tt>url</tt>.
	 * 
	 * @param url value to be assigned to property url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Getter method for property <tt>params</tt>.
	 * 
	 * @return property value of params
	 */
	public String getParams() {
		return params;
	}

	/**
	 * Setter method for property <tt>params</tt>.
	 * 
	 * @param params value to be assigned to property params
	 */
	public void setParams(String params) {
		this.params = params;
	}

	public String getReqCharacter() {
		return reqCharacter;
	}

	public void setReqCharacter(String reqCharacter) {
		this.reqCharacter = reqCharacter;
	}

	public String getRespCharacter() {
		return respCharacter;
	}

	public void setRespCharacter(String respCharacter) {
		this.respCharacter = respCharacter;
	}

}
