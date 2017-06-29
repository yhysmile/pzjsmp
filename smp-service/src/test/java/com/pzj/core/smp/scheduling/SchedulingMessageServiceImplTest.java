package com.pzj.core.smp.scheduling;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pzj.core.smp.delivery.DeliveryAddressEntity;
import com.pzj.core.smp.delivery.DeliveryInfoEntity;
import com.pzj.core.smp.delivery.DeliveryPriorityEnum;
import com.pzj.core.smp.message.MessageEntity;

/**
 * Created by Administrator on 2017-1-5.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:META-INF/spring/applicationContext.xml" })
public class SchedulingMessageServiceImplTest {

	@Resource(name = "schedulingMessageService")
	SchedulingMessageServiceImpl schedulingMessageService;

	//	@Test
	public void publishMessage() {
		DeliveryAddressEntity address = new DeliveryAddressEntity("wlq_test_queue_user", DeliveryPriorityEnum.A);
		DeliveryInfoEntity deliveryInfo = new DeliveryInfoEntity(address, new Date(), 100L);

		MessageEntity message = new MessageEntity(Arrays.asList("15210147640"), "bar123123");

		/*MessageEntity message1 = new MessageEntity(Arrays.asList("15210147640"), "xvb35t63w4t");
		
		MessageEntity message2 = new MessageEntity(Arrays.asList("15210147640"), "gt535ty24523");
		
		MessageEntity message3 = new MessageEntity(Arrays.asList("15210147640"), "bxcbxcvbxcvb");*/

		String transactionId = UUID.randomUUID().toString();

		schedulingMessageService.publishMessage(transactionId, deliveryInfo, message);
		/*schedulingMessageService.publishMessage(deliveryInfo, message1);
		schedulingMessageService.publishMessage(deliveryInfo, message2);
		schedulingMessageService.publishMessage(deliveryInfo, message3);*/
	}

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring.xml");
		/*SchedulingMessageServiceImpl schedulingMessageService = applicationContext.getBean(SchedulingMessageServiceImpl.class);
		schedulingMessageService.startSubscribeMessage("wlq_test_queue_user");*/
	}

}
