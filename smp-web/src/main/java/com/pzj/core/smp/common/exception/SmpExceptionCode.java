package com.pzj.core.smp.common.exception;

/**
 * Created by Administrator on 2017-1-4.
 * 短信平台错误码
 * 100~149为短信通道异常码
 */
public enum SmpExceptionCode {
	ERROR(16999, "失败"), PARAMS_NULL(16998, "参数为空"), PAGE_NULL(16997, "分页数据为空"), PARAM_ERROR(16996, "参数错误！");

	private int code;
	private String msg;
	private String template;

	SmpExceptionCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	SmpExceptionCode(int code, String msg, String template) {
		this(code, msg);
		this.template = template;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public String getTemplate() {
		return template;
	}

	public String getTemplateMessage(Object... args) {
		return getMsg();
	}
}
