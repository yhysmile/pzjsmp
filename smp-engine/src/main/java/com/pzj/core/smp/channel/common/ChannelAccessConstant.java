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

	public static final int SINGLE_CHANNEL_RETRY_TIMES = 2;

	public static final String GST_SEND_SUCCESS_FLAG = "OK:";
}
