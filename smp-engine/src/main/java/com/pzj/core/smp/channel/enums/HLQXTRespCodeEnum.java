package com.pzj.core.smp.channel.enums;

import com.pzj.core.smp.channel.common.StrUtils;

public enum HLQXTRespCodeEnum {
	/** 00 成功 */
	SUCCESS("00", "成功"),
	/** 1 参数不完整  */
	PARAM_MISSING("1", "参数不完整"),
	/** 2 鉴权失败  （包括：用户状态不正常、密码错误、用户不存在、地址验证失败，黑户）*/
	AUTH_FAIL("2", "鉴权失败"),
	/** 3 号码数量超过50 */
	PHONE_NUMBER_EXCEED_MAX("3", "号码数量超过50"),
	/** 4 发送失败 */
	SEND_FAIL("4", "发送失败"),
	/** 5 余额不足 */
	BALANCE_NOT_ENOUGH("5", "余额不足"),
	/** 6 发送内容含屏蔽词 */
	CONTENT_CONTAIN_SHIELD_WORD("6", "发送内容含屏蔽词"),
	/** 7 内容超过350个字符 */
	CONTENT_EXCEED_MAX("7", " 内容超过350个字符");

	private String code;
	private String detail;

	private HLQXTRespCodeEnum(String code, String detail) {
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
	 * @return HLQXTRespCode
	 */
	public static HLQXTRespCodeEnum getRespCode(String code) {
		if (StrUtils.checkStringIsNullStrict(code)) {
			return null;
		}
		HLQXTRespCodeEnum[] hlqxtRespCodes = HLQXTRespCodeEnum.values();
		for (HLQXTRespCodeEnum respCode : hlqxtRespCodes) {
			if (code.equals(respCode.getCode())) {
				return respCode;
			}
		}
		return null;
	}
}
