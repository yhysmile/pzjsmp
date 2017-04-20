package com.pzj.core.smp.channel.enums;

import com.pzj.core.smp.channel.common.StrUtils;

public enum GSTRespCodeEnum {

	/**
	 * OK	String	发送成功的短信的ID
	ERROR	String	错误信息
	举例：
	OK:325689 表示短信发送成功 发送成功的最后一条短信ID为325689；
	ERROR:eUser 表示短信发送不成功，出错原因是因为用户名称有误；
	ERROR:eDate 表示日期错误，出错原因是预发送时间格式不对；
	ERROR:eIllegalPhone 表示发送号码错误；
	ERROR:ePassword 表示密码错误；
	ERROR:eStop 表示用户已经停用；
	ERROR:eDenyDate 表示帐户过期；
	ERROR:eBalance 表示余额不足；
	ERROR:不明错误！表示不明错误，出错原因一般是SQL异常（内容过长或者机构ID填写英文字母等类似错误）；
	ERROR:eFrequent 表示请求频繁；
	ERROR:eContentLen 表示短信内容超长；
	ERROR:nContent 表示短信内容为空；
	ERROR:eContentWrong 表示短信模板拦截
	ERROR:IPWrong 表示客户连接IP与报备IP不符
	ERROR:未知错误 表示未知错误。
	 */

	/** 表示短信发送成功 发送成功的最后一条短信ID为325689；*/
	SUCCESS("OK:", "短信发送成功的短信唯一ID"),
	/** 表示短信发送不成功，出错原因是因为用户名称有误； */
	USERNAME_ERROR("ERROR:eUser", "表示短信发送不成功，出错原因是因为用户名称有误；"),
	/** 表示日期错误，出错原因是预发送时间格式不对；*/
	SEND_DATE_ERROR("ERROR:eDate", "表示日期错误，出错原因是预发送时间格式不对；"),
	/** 表示发送号码错误； */
	ILLEGAL_PHONE_NUMBER("ERROR:eIllegalPhone", "表示发送号码错误；"),
	/** 表示密码错误；*/
	PASSWORD_ERROR("ERROR:ePassword", "表示密码错误；"),
	/** 表示用户已经停用；*/
	USER_DISABLE("ERROR:eStop", "表示用户已经停用；"),
	/** 表示帐户过期；*/
	ACCOUNT_EXPIRES("ERROR:eDenyDate", "表示帐户过期；"),
	/** 表示余额不足；*/
	BALANCE_NOT_ENOUGH("ERROR:eBalance", "表示余额不足；"),
	/** 表示不明错误，出错原因一般是SQL异常（内容过长或者机构ID填写英文字母等类似错误）；*/
	ERROR("ERROR:不明错误！", "表示不明错误，出错原因一般是SQL异常（内容过长或者机构ID填写英文字母等类似错误）；"),
	/** 表示请求频繁； */
	FREQUENT_REQUEST("ERROR:eFrequent", "表示请求频繁；"),
	/** 表示短信内容超长；*/
	CONTENT_EXCEED_MAX("ERROR:eContentLen", "表示短信内容超长；"),
	/** 表示短信内容为空；*/
	CONTENT_NULL("ERROR:nContent", "表示短信内容为空；"),
	/** 表示短信模板拦截*/
	SMS_TEMPLATE_WRONG("ERROR:eContentWrong", "表示短信模板拦截"),
	/** 表示客户连接IP与报备IP不符*/
	IP_BIND_ERROR("ERROR:IPWrong", "表示客户连接IP与报备IP不符"),
	/** */
	ERROR_UNKNOWN("ERROR:未知错误", "表示未知错误。");

	private String code;
	private String detail;

	private GSTRespCodeEnum(String code, String detail) {
		this.code = code;
		this.detail = detail;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * 获取高斯通响应对象
	 * @param code
	 * @return GSTRespCodeEnum
	 */
	public static GSTRespCodeEnum getRespCode(String code) {
		if (StrUtils.checkStringIsNullStrict(code)) {
			return null;
		}
		GSTRespCodeEnum[] respCodes = GSTRespCodeEnum.values();
		for (GSTRespCodeEnum respCode : respCodes) {
			if (respCode.getCode().equals(code)) {
				return respCode;
			}
		}
		return null;
	}
}
