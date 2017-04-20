package com.pzj.core.smp.scheduling;

import javax.annotation.Resource;

import com.pzj.core.smp.cache.SmpCacheKey;
import com.pzj.core.smp.delivery.DeliveryAddressEntity;
import com.pzj.core.smp.delivery.DeliveryInfoEntity;
import com.pzj.core.smp.message.MessageEntity;

/**
 * Created by Administrator on 2016-12-31.
 */
class PublishService {

	@Resource
	private SchedulingService schedulingService = null;

	@Resource
	private QueueService queueService = null;

	public PublishService() {
	}

	public PublishService(SchedulingService schedulingService, QueueService queueService) {
		this.schedulingService = schedulingService;
		this.queueService = queueService;
	}

	/**
	 * 发布指定业务的消息任务
	 * @param deliveryInfo
	 * @param message
	 */
	public void publishMessage(DeliveryInfoEntity deliveryInfo, MessageEntity message) {
		String queueName = guidanceDirection(deliveryInfo.address());
		QueueData data = new QueueData();
		data.setDeliveryInfo(deliveryInfo);
		data.setMessage(message);
		queueService.push(SmpCacheKey.newKey(queueName), data);
	}

	/**
	 * 导向Redis队列的Key
	 * @param deliveryAddress
	 * @return
	 */
	private String guidanceDirection(DeliveryAddressEntity deliveryAddress) {
		SchedulingQueueEntity schedulingQueue = schedulingService.guidanceDirection(deliveryAddress);
		return schedulingQueue.name();
	}
}
