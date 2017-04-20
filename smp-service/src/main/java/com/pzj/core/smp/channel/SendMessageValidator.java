package com.pzj.core.smp.channel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.smp.channel.common.ChannelUtils;
import com.pzj.core.smp.channel.common.ObjUtils;
import com.pzj.core.smp.channel.model.ChannelSendMessage;
import com.pzj.core.smp.channel.model.ParamModel;
import com.pzj.framework.converter.ObjectConverter;

@Component("sendMessageValidator")
public class SendMessageValidator implements ObjectConverter<ChannelSendMessage, Void, ParamModel> {

	private final Logger logger = LoggerFactory.getLogger(SendMessageValidator.class);

	@Override
	public ParamModel convert(ChannelSendMessage reqModel, Void v) {
		ParamModel paramModel = ParamModel.getInstance();
		if (ObjUtils.checkObjectIsNull(reqModel)) {
			paramModel.setErrorModel("调用通道发送短信传入ChannelSendMessage请求对象为空！");
			return paramModel;
		}
		if (!ChannelUtils.checkPhoneNumber(reqModel.getPhoneNumber())) {
			paramModel.setErrorModel("调用通道发送短信传入phoneNumber手机号有误！");
			return paramModel;
		}

		if (!ChannelUtils.checkSmsContent(reqModel.getContent())) {
			paramModel.setErrorModel("调用通道发送短信传入content内容有误！");
			return paramModel;
		}

		//		try {
		//			String message = new String(reqModel.getContent().getBytes(ChannelAccessConstant.CHARACTER_ENCODING_GBK,
		//					ChannelAccessConstant.CHARACTER_ENCODING_UTF8);
		//
		//			reqModel.setContent(message);
		//			logger.info("check send param error! transcode content :{}", message);
		//		} catch (UnsupportedEncodingException e) {
		//			logger.error("invoke channel send message content transcode error! content:{}", reqModel.getContent(), e);
		//			throw new SmpException(e);
		//		}

		return paramModel;
	}
}
