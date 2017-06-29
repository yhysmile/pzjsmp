/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.smp.channel.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.pzj.core.smp.channel.enums.ChannelIndentityEnum;
import com.pzj.core.smp.common.exception.SmpException;
import com.pzj.core.smp.common.exception.SmpExceptionCode;

/**
 * 
 * @author Administrator
 * @version $Id: SmpUtils.java, v 0.1 2016年10月18日 下午4:41:20 Administrator Exp $
 */
public class ChannelUtils {
	/**
	 * 校验通道手机号是否可发送,支持多个手机号用逗号隔开
	 * @param phone
	 * @return Boolean true:合法，false:不合法
	 */
	public static Boolean checkPhoneNumber(List<String> phones) {
		Boolean flag = Boolean.TRUE;
		if (ObjUtils.checkCollectionIsNull(phones)) {
			flag = Boolean.FALSE;
			return flag;
		}

		//校验手机号是否合法
		Iterator<String> itera = phones.iterator();
		String phone = null;
		while (itera.hasNext()) {
			phone = itera.next();
			if (!phone.matches("^1[34578]\\d{9}$")) {
				flag = Boolean.FALSE;
				break;
			}
		}

		return flag;
	}

	/**
	 * 校验通道发送短信内容
	 * @param content
	 * @return boolean true:合法，false:不合法
	 */
	public static boolean checkSmsContent(String content) {
		Boolean flag = Boolean.TRUE;
		if (StrUtils.checkStringIsNullStrict(content)) {
			flag = Boolean.FALSE;
			return flag;
		}

		return flag;
	}

	/**
	 * 检查通道发送手机号限制
	 * @param phone
	 * @param channIden
	 * @return boolean
	 */
	public static boolean checkChannelPhoneNum(String phone, ChannelIndentityEnum channIden) {
		Boolean flag = Boolean.TRUE;
		if (StrUtils.checkStringIsNullStrict(phone) || ObjUtils.checkObjectIsNull(channIden)) {
			flag = Boolean.FALSE;
			return flag;
		}

		String[] phones = phone.split(",");
		//通道限制手机发送数量
		int maxNum = 0;
		if (channIden.getKey() == ChannelIndentityEnum.HLQXT.getKey()) {
			maxNum = ChannelAccessConstant.HLQXT_PHONE_MAX;
		}
		if (channIden.getKey() == ChannelIndentityEnum.GST.getKey()) {
			maxNum = ChannelAccessConstant.GST_PHONE_MAX;
		}
		if (phones.length > maxNum) {
			flag = Boolean.FALSE;
			return flag;
		}
		return flag;
	}

	/**
	 * 检查通道内容长度限制
	 * @param content
	 * @param channIden
	 * @return boolean
	 */
	public static boolean checkChannelContentLen(String content, ChannelIndentityEnum channIden) {
		Boolean flag = Boolean.TRUE;
		if (StrUtils.checkStringIsNullStrict(content) || ObjUtils.checkObjectIsNull(channIden)) {
			flag = Boolean.FALSE;
			return flag;
		}

		//通道那边限制短信内容不能超
		int maxLen = 0;
		if (channIden.getKey() == ChannelIndentityEnum.HLQXT.getKey()) {
			maxLen = ChannelAccessConstant.HLQXT_CONTENT_MAX;
		}
		if (channIden.getKey() == ChannelIndentityEnum.GST.getKey()) {
			maxLen = ChannelAccessConstant.GST_CONTENT_MAX;
		}
		if (content.length() > maxLen) {
			flag = Boolean.FALSE;
			return flag;
		}

		return flag;
	}

	/**
	 * 转换电话发送格式
	 * @param phones
	 * @return String
	 */
	public static String convertSendPhoneFormat(List<String> phones) {
		if (ObjUtils.checkCollectionIsNull(phones)) {
			throw new SmpException(SmpExceptionCode.PARAM_ERROR);
		}
		StringBuffer phoneBuffer = new StringBuffer("");
		Iterator<String> itera = phones.iterator();
		String phone = null;
		while (itera.hasNext()) {
			phone = itera.next();
			phoneBuffer.append(",").append(phone);
		}
		phoneBuffer.deleteCharAt(0);
		return phoneBuffer.toString();

	}

	public static void main(String[] args) {
		List<String> phones = new ArrayList<String>();
		phones.add("18210255864");

		System.out.println(convertSendPhoneFormat(phones));
	}
}
