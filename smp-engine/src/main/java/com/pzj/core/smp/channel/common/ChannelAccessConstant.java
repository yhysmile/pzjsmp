/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.smp.channel.common;

/**
 * 
 * @author Administrator
 * @version $Id: ChannelAccessConstant.java, v 0.1 2016年11月30日 上午11:57:01 Administrator Exp $
 */
public class ChannelAccessConstant {

	public static final String CHARACTER_ENCODING_GBK = "GBK";
	public static final String CHARACTER_ENCODING_UTF8 = "UTF-8";

	public static final int CONNECTION_TIMEOUT = 3000;
	public static final int READ_TIMEOUT = 3000;

	public static final String REQUEST_POST = "POST";
	public static final String REQUEST_GET = "GET";

	public static final String FORMAT_JSON = "json";
	public static final String FORMAT_XML = "xml";

	public static final String REQUEST_CONTENT_TYPE = "application/x-www-form-urlencoded";

	public static final int HLQXT_EPID = 109625;

	public static final int GST_CONTENT_MAX = 300;
	public static final int GST_PHONE_MAX = 100;

	public static final int HLQXT_CONTENT_MAX = 350;
	public static final int HLQXT_PHONE_MAX = 50;

	public static final int SINGLE_CHANNEL_RETRY_TIMES = 3;
	public static final int RESTRY_SLEEP_TIME = 500;

	public static final String GST_SEND_SUCCESS_FLAG = "OK:";

	public static final String HLQXT_ACCOUNT_WARN_SUBJECT = "鸿联企信通短信异常通知";
	public static final String HLQXT_ACCOUNT_WARN_CONTENT = "鸿联企信通短信通道账户余额不足，#，请及时充值，以免影响短信接收！！！";

	public static final String GST_ACCOUNT_WARN_SUBJECT = "高斯通短信异常通知";
	public static final String GST_ACCOUNT_WARN_CONTENT = "高斯通短信通道账户余额不足，#，请及时充值，以免影响短信接收！！！";

	public static final String GST_ACCOUNT_BALANCE_FLAG = "balance";
	public static final String GST_ACCOUNT_PRICE_FLAG = "smsPrice";

	public static final int HTTP_RESP_SUCCESS_CODE = 200;

}
