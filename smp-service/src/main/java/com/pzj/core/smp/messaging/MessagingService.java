package com.pzj.core.smp.messaging;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.pzj.core.smp.delivery.IShortMessageService;
import com.pzj.core.smp.delivery.MessageBean;

/**
 * 从消息服务器（RocketMQ）中接收短信任务。
 *
 * Created by Administrator on 2017-1-7.
 */
public class MessagingService extends MessagingConfig {
	private static Logger logger = LoggerFactory.getLogger(MessagingService.class);

	private IShortMessageService shortMessageService;

	private DefaultMQPushConsumer consumer = null;

	private boolean run = false;

	public void start() throws MQClientException {
		try {
			logger.info("启动RocketMQ消息接收程序：开始");
			if (consumer == null) {
				synchronized (this) {
					if (consumer == null) {
						createConsumer();
						logger.info("启动RocketMQ消息中：Consumer 初始化完成");
					}
				}
			}
			if (!run) {
				synchronized (this) {
					if (!run) {
						consumer.start();
						logger.info("启动RocketMQ消息中：Consumer 启动完成");
						run = true;
					}
				}
			}
			logger.info("启动RocketMQ消息接收程序：结束");
		} catch (Throwable throwable) {
			logger.error(throwable.getMessage(), throwable);
		}
	}

	private void createConsumer() throws MQClientException {
		consumer = new DefaultMQPushConsumer();
		consumer.subscribe(getTopic(), "sendMessage");
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
		consumer.setNamesrvAddr(getNamesrvAddr());
		consumer.setConsumerGroup(getConsumerGroup());

		consumer.registerMessageListener(new MessageListenerConcurrently() {

			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				for (MessageExt msg : msgs)
					sendMessage(msg);

				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
	}

	public void stop() {
		if (consumer != null)
			consumer.shutdown();
	}

	@Resource
	public void setShortMessageService(IShortMessageService shortMessageService) {
		this.shortMessageService = shortMessageService;
	}

	private void sendMessage(MessageExt msg) {
		try {
			String body = new String(msg.getBody());
			MessageBean messageBean = JSONObject.parseObject(body, MessageBean.class);
			shortMessageService.sendMessage(messageBean);
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}

}