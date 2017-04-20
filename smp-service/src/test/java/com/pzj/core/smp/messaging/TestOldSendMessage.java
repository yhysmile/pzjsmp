package com.pzj.core.smp.messaging;

import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pzj.message.mail.service.MailSendService;
import com.pzj.message.sms.service.SmsSendService;

public class TestOldSendMessage {

	static ApplicationContext applicationContext = null;

	static SmsSendService smsSendService = null;
	static MailSendService mailSendService = null;

	@BeforeClass
	public static void setUpClass() {
		applicationContext = new ClassPathXmlApplicationContext("classpath*:META-INF/spring/applicationContext.xml");
		System.out.println("加载上下文：：：" + applicationContext);
	}

	@Before
	public void setUp() {
		smsSendService = applicationContext.getBean(SmsSendService.class);
		mailSendService = applicationContext.getBean(MailSendService.class);
	}

	//	@Test
	public void testSendMessage() {
		String phone = "18210255864";
		String message = "hahah";
		try {
			Integer result = smsSendService.sendSMS(phone, message);
			System.out.println("send result :" + result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//	@Test
	public void testSendMail() {
		String subject = "2我是新的短信平台邮件标题";
		String content = "我是测试邮件内容";
		String[] mailtoArrays = new String[] { "yihongying@mftour.cn" };
		try {
			mailSendService.sendMail(subject, content, mailtoArrays);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
