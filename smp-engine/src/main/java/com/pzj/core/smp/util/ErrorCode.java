package com.pzj.core.smp.util;

/**
 * Created by Administrator on 2017-1-4.
 * 短信平台错误码
 */
public enum ErrorCode {

	ERROR(16999, "失败");

	private int code;
	private String msg;

	ErrorCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}
