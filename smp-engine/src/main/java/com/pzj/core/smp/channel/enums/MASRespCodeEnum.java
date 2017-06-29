package com.pzj.core.smp.channel.enums;

import com.pzj.core.smp.channel.common.StrUtils;

public enum MASRespCodeEnum {

	/** IllegalMac	无效mac */
	ILLEGAL_MAC("IllegalMac", "无效mac"),
	/** InvalidMessage	非法消息 */
	INVALID_MESSAGE("InvalidMessage", "非法消息"),
	/** InvalidUsrOrPwd	非法用户名或密码 */
	INVALID_USER_OR_PWD("InvalidUsrOrPwd", "非法用户名或密码"),
	/** NoSignId	未找到签名 */
	NO_SIGNID("NoSignId", "未找到签名"),
	/** IllegalSignId	无效的签名 */
	ILLEGAL_SIGNID("IllegalSignId", "无效的签名"),
	/** success	成功 */
	SUCCESS("success", "成功"),
	/** TooManyMobiles	手机号超出最大上限（5000） */
	TOO_MANY_MOBILES("TooManyMobiles", "手机号超出最大上限（5000）");

	private String code;
	private String detail;

	private MASRespCodeEnum(String code, String detail) {
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
	public static MASRespCodeEnum getRespCode(String code) {
		if (StrUtils.checkStringIsNullStrict(code)) {
			return null;
		}
		MASRespCodeEnum[] masRespCodes = MASRespCodeEnum.values();
		for (MASRespCodeEnum respCode : masRespCodes) {
			if (code.equals(respCode.getCode())) {
				return respCode;
			}
		}
		return null;
	}
}
