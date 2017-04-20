package com.pzj.core.smp.message;

import java.util.List;

import com.pzj.core.smp.common.exception.SmpException;
import com.pzj.core.smp.common.exception.SmpExceptionCode;
import com.pzj.core.smp.util.CommonMatches;

/**
 * 消息
 * Created by Administrator on 2016-12-29.
 */
public class MessageEntity {
	// 发送地址：手机号
	private List<String> phoneNums;
	// 消息内容
	private String content;

	public MessageEntity(List<String> phoneNums, String content) {
		setPhoneNums(phoneNums);
		setContent(content);
	}

	public String content() {
		return content;
	}

	public List<String> phoneNums() {
		return phoneNums;
	}

	private void setContent(String content) {
		if (content == null) {
			throw new SmpException(SmpExceptionCode.MESSAGE_CONTENT_NULL);
		}
		this.content = content;
	}

	private void setPhoneNums(List<String> phoneNums) {
		if (phoneNums == null || phoneNums.isEmpty()) {
			throw new SmpException(SmpExceptionCode.MESSAGE_PHONENUM_NULL);
		}
		for (String phoneNum : phoneNums) {
			if (!CommonMatches.checkPhone(phoneNum)) {
				throw new SmpException(SmpExceptionCode.MESSAGE_PHONENUM_NULL);
			}
		}

		this.phoneNums = phoneNums;
	}
}
