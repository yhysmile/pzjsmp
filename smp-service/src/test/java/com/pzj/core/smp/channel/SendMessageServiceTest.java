package com.pzj.core.smp.channel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pzj.core.smp.channel.model.ChannelSendMessage;

public class SendMessageServiceTest {
	private final Logger logger = LoggerFactory.getLogger(SendMessageServiceTest.class);
	private static ApplicationContext context;

	@BeforeClass
	public static void setUpClass() {
		context = new ClassPathXmlApplicationContext("applicationContext-test.xml");
	}

	@Autowired
	private SendMessageService sendMessageService;

	@Before
	public void setUp() {
		sendMessageService = context.getBean(SendMessageService.class);
	}

	@Test
	public void testSendMessage() {
		List<String> phones = new ArrayList<String>();
		phones.add("18210255864");
		//		phones.add("15210147640");
		//		phones.add("13522330020");
		ChannelSendMessage sendMessage = new ChannelSendMessage();
		sendMessage.setContent("l！");
		sendMessage.setPhoneNumber(phones);
		sendMessage.setSendDate(new Date());
		sendMessage.setExpireDuration(200000L);

		for (int i = 0; i < 1; i++) {
			Boolean flag = sendMessageService.sendMessage(sendMessage);
			logger.info("send message result:{}", flag);

			try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
