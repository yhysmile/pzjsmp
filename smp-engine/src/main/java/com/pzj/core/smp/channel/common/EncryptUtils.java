package com.pzj.core.smp.channel.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import sun.misc.BASE64Encoder;

public class EncryptUtils {
	/**
	* MD5加密
	* @param origin
	* @return
	*/
	public static String MD5Encode(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {

		}
		return resultString;
	}

	/** 
	 * BASE64编码
	 * @param src 
	 * @return 
	 * @throws Exception 
	 */
	public static String base64Encoder(String src) {
		BASE64Encoder encoder = new BASE64Encoder();
		String encrpyStr = "";
		try {
			encrpyStr = encoder.encode(src.getBytes(ChannelAccessConstant.CHARACTER_ENCODING_UTF8));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encrpyStr;
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };
}
