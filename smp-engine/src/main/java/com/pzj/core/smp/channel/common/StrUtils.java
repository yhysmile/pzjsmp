package com.pzj.core.smp.channel.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class StrUtils {
	/**
	 * 检查字符串是否为空
	 * 空：true ；非空：false
	 * @param str
	 * @return
	 */
	public static boolean checkStringIsNull(String str) {
		return checkStringIsNotNull(str) ? false : true;
	}

	/**
	 * 严格检查字符串是否为空
	 * 空：true ；非空：false
	 * @param str
	 * @return
	 */
	public static boolean checkStringIsNullStrict(String str) {
		return !checkStringIsNotNull(str) || "null".equals(str) ? true : false;
	}

	/**
	 * 检查字符串是否非空
	 * 非空：true ；空：false
	 * @param str
	 * @return
	 */
	public static boolean checkStringIsNotNull(String... str) {
		boolean flag = Boolean.FALSE;
		if (null == str || str.length == 0) {
			return flag;
		}
		for (String s : str) {
			if (null == s || "".equals(s.trim())) {
				flag = Boolean.FALSE;
			} else {
				flag = Boolean.TRUE;
			}

			if (!flag) {
				break;
			}
		}
		return flag;
	}

	public static void main(String[] args) {
		//		String[] phones = new String[10];
		//		phones[0] = "18210255864";
		//		phones[1] = "18210255864";

		try {
			String content = "浣犲ソ";

			String test1 = URLEncoder.encode(content, ChannelAccessConstant.CHARACTER_ENCODING_UTF8);

			String message = new String(content.getBytes("ISO-8859-1"), ChannelAccessConstant.CHARACTER_ENCODING_UTF8);

			String content2 = "很不错！";
			String message2 = new String(content2.getBytes("ISO-8859-1"), ChannelAccessConstant.CHARACTER_ENCODING_UTF8);

			System.out.println(content + "========" + test1 + "========" + message + "========" + message2);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
