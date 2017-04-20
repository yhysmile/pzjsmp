/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.smp.channel.model;

import com.pzj.core.smp.channel.common.StrUtils;

/**
 * 
 * @author Administrator
 * @version $Id: ParamModel.java, v 0.1 2016年9月9日 上午10:50:36 Administrator Exp $
 */
public class ParamModel implements java.io.Serializable {

	private static final long serialVersionUID = 8465431748292124271L;
	private int paramCode;
	private String paramErrorMsg;

	/** 参数传入正常code*/
	private static final int PARAM_OK_CODE = 100;
	private static final String PARAM_OK_MSG = "参数OK!";

	/** 参数传入错误code*/
	private static final int PARAM_ERROR_CODE = 101;
	private static final String PARAM_ERROR_MSG = "参数错误!";

	/**
	 * 获取参数对象
	 * @return ParamModel
	 */
	public static final ParamModel getInstance() {
		return new ParamModel();
	}

	/**
	 * 判断参数是否正确
	 * @return boolean true 参数正确 false 参数错误
	 */
	public boolean paramIsOk() {
		return this.getParamCode() == PARAM_OK_CODE;
	}

	/**
	 * 设置默认错误码和错误信息
	 * @param paramErrorMsg
	 */
	public void setErrorModel(String paramErrorMsg) {
		if (StrUtils.checkStringIsNullStrict(paramErrorMsg)) {
			return;
		}
		this.paramCode = PARAM_ERROR_CODE;
		this.paramErrorMsg = PARAM_ERROR_MSG + paramErrorMsg;
	}

	public ParamModel() {
		this(PARAM_OK_CODE, PARAM_OK_MSG);
	}

	public ParamModel(int paramCode, String paramErrorMsg) {
		this.paramCode = paramCode;
		this.paramErrorMsg = paramErrorMsg;
	}

	public int getParamCode() {
		return paramCode;
	}

	public String getParamErrorMsg() {
		return paramErrorMsg;
	}

	public void setParamCode(int paramCode) {
		this.paramCode = paramCode;
	}

	public void setParamErrorMsg(String paramErrorMsg) {
		this.paramErrorMsg = paramErrorMsg;
	}

}
