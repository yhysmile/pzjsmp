package com.pzj.core.smp.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pzj.core.smp.cache.SmpCacheKey;

/**
 * Created by Administrator on 2017-1-5.
 */
class Subscriber implements Runnable {
	private static Logger logger = LoggerFactory.getLogger(Subscriber.class);

	boolean run = true;

	private SmpCacheKey[] cacheKeys;

	private String[] keys;

	private QueueService queueService;

	private SubscribeListener subscribeListener = null;

	public Subscriber(String[] queueNames, QueueService queueService, SubscribeListener subscribeListener) {
		this.queueService = queueService;
		this.subscribeListener = subscribeListener;
		this.cacheKeys = SmpCacheKey.newKeys(queueNames);
		this.keys = SmpCacheKey.kevStrings(cacheKeys);
	}

	public void ready() {
		if (cacheKeys == null)
			throw new RuntimeException(); // TODO 抛出异常
		if (queueService == null)
			throw new RuntimeException(); // TODO 抛出异常
	}

	/**
	 * 接收消息
	 * <p>
	 *     在任务监听线程池 {@link SubscribeService} 中执行。
	 * </p>
	 */
	public QueueData pullMessageTasks() {
		QueueData data = queueService.poll(6, keys);
		return data;
	}

	public QueueData pullMessageTasks(int timeout) {
		QueueData data = queueService.poll(timeout, keys);
		return data;
	}



	public void enable() {
		this.run = true;
	}

	public void disable() {
		this.run = false;
	}

	@Override
	public void run() {
		while (run) {
			try {
				QueueData data = pullMessageTasks();
				if (data != null) {
					subscribeListener.handQueueData(data);
				}
			} catch (Throwable throwable) {
				logger.error(throwable.getMessage(), throwable);
			}
		}
		run = false;
	}
}
