package com.pzj.core.smp.channel.enums;

import com.pzj.core.smp.channel.common.StrUtils;

public enum MASSdkRespCodeEnum {

	/** 0 通道登录失败 */
	LOGIN_FAIL("1", "通道登录失败 "),
	/** 1 成功 */
	SUCCESS("1", "短信发送成功"),
	/** 101	短信内容为空  */
	MESSAGE_CONTENT_NULL("101", "短信内容为空"),
	/** 102	号码数组为空*/
	PHONES_NULL("102", "号码数组为空"),
	/** 103	号码数组为空数组 */
	PHONE_NUMBER_ARRAY_NULL("103", "号码数组为空数组"),
	/** 104	批次短信的号码中存在非法号码 */
	EXIST_ILLEGAL_PHONES("104", "批次短信的号码中存在非法号码"),
	/** 105	未进行身份认证或认证失败 */
	IDENTITY_AUTH_FAIL("105", "身份认证或认证失败"),
	/** 106	网关签名为空 */
	SIGN_NULL("106", "网关签名为空"),
	/** 107	其它错误 */
	OTHER_ERROR("107", "其它错误"),
	/** 109	批次短信号码中存在重复号码 */
	REPEAT_PHONE("109", "批次短信号码中存在重复号码"),
	/** 110	号码过多（最多5000个号码）*/
	PHONE_NUM_ABOVE_MAX("110", "号码过多（最多5000个号码）"),
	/** 112	线程池忙 */
	THREAD_BUSY("112", "线程池忙");

	private String code;
	private String detail;

	private MASSdkRespCodeEnum(String code, String detail) {
		this.code = code;
		this.detail = detail;
	}

	public String getCode() {
		return code;
	}

	public String getDetail() {
		return detail;
	}

	/**
	 * 
	 * @param code
	 * @return MASRespCodeEnum
	 */
	public static MASSdkRespCodeEnum getRespCode(String code) {
		if (StrUtils.checkStringIsNullStrict(code)) {
			return null;
		}
		MASSdkRespCodeEnum[] masRespCodes = MASSdkRespCodeEnum.values();
		for (MASSdkRespCodeEnum respCode : masRespCodes) {
			if (code.equals(respCode.getCode())) {
				return respCode;
			}
		}
		return null;
	}
}
