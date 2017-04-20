package com.pzj.core.smp.delivery;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016-12-29.
 */
public class MessageBean implements Serializable {
	private MessageHead head;
	// 手机号
	private List<String> phoneNums;
	// 消息内容
	private String content;

	public MessageBean() {
	}

	public MessageBean(MessageHead messageHead, List<String> phoneNums, String content) {
		setHead(messageHead);
		setPhoneNums(phoneNums);
		setContent(content);
	}

	public MessageHead getHead() {
		return head;
	}

	public void setHead(MessageHead head) {
		/*if (head == null)
		    throw new SmpException("head 不能为空。");*/
		this.head = head;
	}

	public List<String> getPhoneNums() {
		return phoneNums;
	}

	public void setPhoneNums(List<String> phoneNums) {
		/*if (phoneNum == null)
		    throw new SmpException("phoneNum 不能为空。");*/
		this.phoneNums = phoneNums;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		/*if (content == null || content.isEmpty())
		    throw new SmpException("content 不能为空。");*/
		this.content = content;
	}

	@Override
	public String toString() {
		return "MessageBean [head=" + head + ", phoneNums=" + phoneNums + ", content=" + content + "]";
	}

	public static void main(String[] args) {
		//		MessageBean bean = new MessageBean();
		//		bean.setContent("你好");
		//		List<String> phones = new ArrayList<String>();
		//		phones.add("18210255864");
		//		bean.setPhoneNums(phones);
		//		System.out.println(bean);

		MessageHead messageHead = new MessageHead();
		messageHead.setBusinessName("wlq_test_queue_user");
		messageHead.setPriority("A");
		messageHead.setCreateDate(new Date());
		messageHead.setTimeOut(210L);

		MessageBean messageBean = new MessageBean();
		//        messageBean.setPhoneNum("15210147789");
		messageBean.setContent("8888888888888888888888");
		messageBean.setHead(messageHead);

		System.out.println(messageHead);
	}
}
