package com.pzj.core.smp.messaging;

import java.io.InputStream;
import java.net.HttpURLConnection;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pzj.commons.utils.HttpUtils;
import com.pzj.core.smp.util.SMSParam;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.message.sms.service.SmsSendService;

@Service("smsSendService")
public class SmsSendServiceImpl implements SmsSendService {

	private final Logger logger = LoggerFactory.getLogger(SmsSendServiceImpl.class);
	private static final String SMS_SENDER_URL = "http://114.255.71.158:8061/?";
	private static final String PREVIOUS_SIGNATURE = "【票之家】";
	private static final int MAX_CONTENT_LENGTH = 300; // 鸿联最长350字节，那么我们的字符串长度最长只能设置到150，因为汉字2字节

	@Override
	public Integer sendSMS(String phone, String message) {
		if (message.contains(PREVIOUS_SIGNATURE))
			message = message.replaceAll(PREVIOUS_SIGNATURE, "");
		if (message.length() <= MAX_CONTENT_LENGTH) {
			SMSParam param = new SMSParam(phone, message);
			sendSMS(param);
		} else {
			for (int i = 0; i < message.length(); i += MAX_CONTENT_LENGTH) {
				SMSParam param;
				String msg = "";
				if (i + MAX_CONTENT_LENGTH >= message.length())
					msg = message.substring(i, message.length());
				else
					msg = message.substring(i, i + MAX_CONTENT_LENGTH);
				if (i > 0)
					msg = "(接上一条)" + msg;
				param = new SMSParam(phone, msg);
				sendSMS(param);
			}
		}

		return 1;
	}

	private void sendSMS(SMSParam param) {
		StringBuilder sb = new StringBuilder(param.toString()).append("\n");
		String url = SMS_SENDER_URL.concat(param.toString());
		logger.info("调用老的发送短信接口，url:{}", url);
		try {
			HttpURLConnection conn = HttpUtils.prepareHttpConnection(url, HttpUtils.GET_METHOD);
			int response_code = conn.getResponseCode();
			sb.append("短信发送response_code:").append(response_code).append("\n");
			if (response_code == HttpsURLConnection.HTTP_OK) {
				InputStream in = conn.getInputStream();
				byte[] bytes = new byte[in.available()];
				in.read(bytes);
				String content = new String(bytes, "UTF-8");
				sb.append("短信发送结果：").append(content).append("\n");
			}
		} catch (Exception ex) {
			logger.error("调用老的短信服务发送接口异常，参数为:{},异常信息为:{}", JSONConverter.toJson(param), ex);
			ex.printStackTrace();
		}
		logger.info("调用老的发送短信接口发送参数以及发送结果信息:{}", sb);
	}

}
