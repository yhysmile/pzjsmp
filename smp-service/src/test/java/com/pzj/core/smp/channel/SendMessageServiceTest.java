package com.pzj.core.smp.channel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	//	@Autowired
	private SendMessageService sendMessageService;
	//	@Autowired
	private AccountWarnService accountWarnService;

	@Before
	public void setUp() {
		sendMessageService = context.getBean(SendMessageService.class);
		accountWarnService = context.getBean(AccountWarnService.class);
	}

	@Test
	public void testSendMessage() {
		List<String> phones = new ArrayList<String>();
		phones.add("18210255864");
		//18600576560 金梁
		//		phones.add("15210147640");
		//		phones.add("13522330020");
		String sendContent = "nichousha,chounizadi ！";
		ChannelSendMessage sendMessage = new ChannelSendMessage();
		//		sendMessage.setContent(sendContent);
		sendMessage.setPhoneNumber(phones);
		sendMessage.setSendDate(new Date());
		sendMessage.setExpireDuration(200000L);

		for (int i = 1; i < 10; i++) {
			String param = (int) (Math.random() * 1000000) + "";
			sendContent += param;
			sendMessage.setContent(sendContent);
			logger.info("send message content:{}", sendContent);
			Boolean flag = sendMessageService.sendMessage(sendMessage);
			logger.info("send message result:{}", flag);

			//			try {
			//				Thread.sleep(1000L);
			//			} catch (InterruptedException e) {
			//				e.printStackTrace();
			//			}
		}

	}

	//	@Test
	public void testGetAccount() {
		accountWarnService.accountWarn();
	}
}
