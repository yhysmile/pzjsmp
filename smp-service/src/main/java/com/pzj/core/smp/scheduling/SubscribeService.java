package com.pzj.core.smp.scheduling;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2017-1-5.
 */
class SubscribeService {
	private static Logger logger = LoggerFactory.getLogger(SubscribeService.class);
	private ExecutorService receiverPool = null;

	private Map<String, Subscriber> subscribers = null;

	@Resource
	private SchedulingService schedulingService = null;

	@Resource
	private QueueService queueService = null;

	public SubscribeService() {
		init();
	}

	public SubscribeService(SchedulingService schedulingService, QueueService queueService) {
		this();
		this.schedulingService = schedulingService;
		this.queueService = queueService;
	}

	private void init() {
		this.receiverPool = Executors.newCachedThreadPool();
		this.subscribers = new HashMap<>();
	}

	/**
	 * 开始从一个业务订阅消息
	 *
	 * @param businessName
	 */
	public void startSubscribeMessage(String businessName, SubscribeListener subscribeListener) {
		logger.info("消息调度订阅：订阅业务线 {} 的消息：开始", businessName);
		Subscriber subscriber = subscribers.get(businessName);
		if (subscriber != null && subscriber.run) {
			logger.info("消息调度订阅：订阅业务线 {} 已启用，不需要再订阅。", businessName);
			return;
		}

		String[] queueNames = schedulingService.namesOfBusiness(businessName);
		subscriber = new Subscriber(queueNames, this.queueService, subscribeListener);
		subscriber.ready();
		receiverPool.submit(subscriber);
		logger.info("消息调度订阅：订阅业务线 {} 的消息：完成", businessName);
	}

	/**
	 * 停止从一个业务订阅消息
	 * @param businessName
	 */
	public void stopSubscribeMessage(String businessName) {
		Subscriber subscriber = subscribers.get(businessName);
		stopSubscribeMessage(subscriber);
		subscribers.remove(businessName);
	}

	private void stopSubscribeMessage(Subscriber subscriber) {
		if (subscriber == null)
			return;

		subscriber.disable();
	}

	public void stopSubscribeMessageAll() {
		synchronized (subscribers) {
			Iterator<Map.Entry<String, Subscriber>> iterator = subscribers.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, Subscriber> next = iterator.next();
				stopSubscribeMessage(next.getValue());
				iterator.remove();
			}
		}
	}

	public void shutdown(){
		synchronized (receiverPool) {
			if (receiverPool != null && !receiverPool.isShutdown()) {
				stopSubscribeMessageAll();
				receiverPool.shutdown();
			}
		}
	}
}
