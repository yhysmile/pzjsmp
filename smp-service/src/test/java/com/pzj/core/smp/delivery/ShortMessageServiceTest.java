package com.pzj.core.smp.delivery;

import java.io.IOException;
import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pzj.framework.context.Result;

/**
 * Created by Administrator on 2017-1-5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring.xml" })
public class ShortMessageServiceTest {
	private static Logger logger = LoggerFactory.getLogger(ShortMessageServiceTest.class);

	@Resource
	IShortMessageService shortMessageService;

	@Test
	public void sendMessage() throws IOException {
		MessageHead messageHead = new MessageHead("wlq_test_q", "C", 30000L);
		MessageBean messageBean = new MessageBean(messageHead, Arrays.asList("15210147640"), "ab123");

		Result<Boolean> result = shortMessageService.sendMessage(messageBean);


		logger.info("========================= test result =========================");
		logger.info(result.getErrorMsg());

		System.in.read();
	}
}
