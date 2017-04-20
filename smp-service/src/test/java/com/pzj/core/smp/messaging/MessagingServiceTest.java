package com.pzj.core.smp.messaging;

import java.io.IOException;
import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.pzj.core.smp.delivery.MessageBean;
import com.pzj.core.smp.delivery.MessageHead;

/**
 * Created by Administrator on 2017-1-9.
 */

public class MessagingServiceTest {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring.xml");
	}

	static DefaultMQProducer producer;

	@BeforeClass
	public static void setUpClass() throws MQClientException {
		producer = new DefaultMQProducer("ProducerGroupName");
		producer.setNamesrvAddr("10.0.6.25:9876");
		producer.start();
	}

	@AfterClass
	public static void tearDownClass() throws IOException {
		producer.shutdown();
	}

	@Test
	public void test() {
		MessageHead messageHead = new MessageHead("wlq_test_q", "A", 60000L);
		MessageBean messageBean1 = new MessageBean(messageHead, Arrays.asList("15210147640"), "老朋友土好和034");
		System.out.println("content : " + messageBean1.getContent());

		/*MessageBean messageBean2 = new MessageBean(messageHead, "15210147640",  "adfasdfasdfasdfasf");
		MessageBean messageBean3 = new MessageBean(messageHead, "15210147640",  "etyeytryeryeryerye");
		MessageBean messageBean4 = new MessageBean(messageHead, "15210147640",  "cbncncncvncvncvncv");
		MessageBean messageBean5 = new MessageBean(messageHead, "15210147640",  "sdfgsdgsdfgsgfdgsd");*/

		publishMessage(messageBean1);
		/*publishMessage(messageBean2);
		publishMessage(messageBean3);
		publishMessage(messageBean4);
		publishMessage(messageBean5);*/
	}

	private void publishMessage(MessageBean messageBean) {
		String jsonString = JSON.toJSONString(messageBean);
		// 构建消息
		Message msg = new Message("aafefe", jsonString.getBytes());
		// 发送消息
		SendResult sendResult = null;

		try {
			sendResult = producer.send(msg);
			System.out.println(sendResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
