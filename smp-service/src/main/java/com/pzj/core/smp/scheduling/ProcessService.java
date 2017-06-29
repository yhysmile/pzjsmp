package com.pzj.core.smp.scheduling;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.pzj.core.smp.delivery.DeliveryAddressEntity;
import com.pzj.core.smp.delivery.DeliveryInfoEntity;
import com.pzj.core.smp.delivery.DeliveryPriorityEnum;
import com.pzj.core.smp.message.MessageEntity;

/**
 * Created by Administrator on 2017-1-5.
 */
class ProcessService {

	private List<ExecutorService> processorPools = null;

	private ProcessListener processor = null;

	public ProcessService(ProcessListener processor) {
		this(processor, DeliveryPriorityEnum.values().length, 20);
	}

	public ProcessService(ProcessListener processor, int poolCount, int poolSize) {
		this.processor = processor;
		this.processorPools = initProcessorPools(poolCount, poolSize);
	}

	/* 初始化每个级别的线程池 */
	private List<ExecutorService> initProcessorPools(int poolCount, int poolSize) {
		List<ExecutorService> processorPools = new ArrayList<>(poolCount);
		for (int i = 0; i < poolCount; i++) {
			ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
			processorPools.add(executorService);
		}
		return processorPools;
	}

	/**
	 * 处理一个队列数据
	 *
	 * @param deliveryInfo
	 * @param message
	 */
	public void processQueueData(String transactionId, DeliveryInfoEntity deliveryInfo, MessageEntity message) {
		// 找到线程池
		ExecutorService processPool = processorPollOf(deliveryInfo.address());
		if (processPool != null) {
			// 创建任务
			ProcessRun run = new ProcessRun();
			run.deliveryInfo = deliveryInfo;
			run.message = message;
			run.transactionId= transactionId;
			// 提交任务
			processPool.submit(run);
		}
	}

	/* 找到对应级别的线程池 */
	private ExecutorService processorPollOf(DeliveryAddressEntity address) {
		ExecutorService processPool = processorPools.get(address.priority().ordinal());
		return processPool;
	}

	/* 处理队列数据的线程任务 */
	private class ProcessRun implements Runnable {
		DeliveryInfoEntity deliveryInfo;
		MessageEntity message;
		String transactionId;

		@Override
		public void run() {
			processor.processMessage(transactionId, deliveryInfo, message);
		}
	}

	public void shutdown(){
		synchronized (processorPools){
			for (int i = 0; i < processorPools.size(); i++) {
				ExecutorService executorService = processorPools.get(i);
				executorService.shutdown();
			}
		}
	}
}
