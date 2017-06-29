package com.pzj.core.smp.scheduling;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pzj.core.smp.business.BusinModel;
import com.pzj.core.smp.business.BusinService;
import com.pzj.core.smp.cache.SmpCacheKey;
import com.pzj.core.smp.channel.SendMessageService;
import com.pzj.core.smp.channel.model.ChannelSendMessage;
import com.pzj.core.smp.delivery.DeliveryAddressEntity;
import com.pzj.core.smp.delivery.DeliveryInfoEntity;
import com.pzj.core.smp.message.MessageEntity;
import com.pzj.core.smp.util.ModelBuilder;
import com.pzj.framework.cache.core.CacheService;

/**
 * Created by Administrator on 2017-1-5.
 */
public class SchedulingMessageServiceImpl implements ProcessListener, SubscribeListener, QueueService<SmpCacheKey> {
	private static Logger logger = LoggerFactory.getLogger(SchedulingMessageServiceImpl.class);
	@Resource
	private final PublishService publishService = null;
	@Resource
	private final SubscribeService subscribeService = null;
	@Resource
	private final ProcessService processService = null;
	@Resource
	private CacheService cacheService = null;
	@Resource
	private SendMessageService sendMessageService = null;

	@Resource
	private final BusinService businService;

	public SchedulingMessageServiceImpl(CacheService cacheService, SendMessageService sendMessageService,
			BusinService businService) {
		this.cacheService = cacheService;
		this.sendMessageService = sendMessageService;
		this.businService = businService;
	}

	public void destroy() {
		if (subscribeService != null) {
			subscribeService.shutdown();
		}
		if (processService != null) {
			processService.shutdown();
		}
	}

	/**
	 * 发布指定业务的消息任务
	 * @param transactionId
	 * @param deliveryInfo
	 * @param message
	 */
	public void publishMessage(String transactionId, DeliveryInfoEntity deliveryInfo, MessageEntity message) {
		publishService.publishMessage(transactionId, deliveryInfo, message);
	}

	public void startSubscribeAll() {
		List<BusinModel> businModels = businService.selectValidBusin();
		if (businModels == null || businModels.isEmpty()) {
			logger.info("消息调度服务启动：自动订阅所有业务线消息时，没有发现至少一个可用的业务线。请创建业务线或启用已有的业务线。");
			return;
		}

		for (BusinModel businModel : businModels) {
			try {
				startSubscribeMessage(businModel.getName());
			} catch (Throwable throwable) {
				logger.error(throwable.getMessage(), throwable);
			}
		}
	}

	public void stopSubscribeAll() {
		subscribeService.stopSubscribeMessageAll();
	}

	/**
	 * 开始从一个业务订阅消息
	 *
	 * @param businessName
	 */
	public void startSubscribeMessage(String businessName) {
		subscribeService.startSubscribeMessage(businessName, this);
	}

	/**
	 * 停止从一个业务订阅消息
	 * @param businessName
	 */
	public void stopSubscribeMessage(final String businessName) {
		subscribeService.stopSubscribeMessage(businessName);
	}

	/**
	 * processService 使用
	 * @param message
	 */
	@Override
	public void processMessage(String transactionId, DeliveryInfoEntity deliveryInfo, MessageEntity message) {
		MDC.put("requestId", transactionId);
		Long beginDate = System.currentTimeMillis();
		Long endDate = null;
		logger.info("收到一个消息：事务为 {}，业务线 {} ，优先级 {} ，当前时间为 {}", transactionId, deliveryInfo.address().businessName(),
				deliveryInfo.address().priority().name(), beginDate);

		List<String> phoneNums = message.phoneNums();
		if (phoneNums == null || phoneNums.isEmpty()) {
			logger.warn("事务为 {}，没有可用的手机号！", transactionId);
			return;
		}
		logger.info("事务为 {}，手机号：{}", transactionId, phoneNums);

		logger.info("调用发送开始，事务为 {}，当前时间为 {}", transactionId, System.currentTimeMillis());
		doSendMessage(phoneNums, message.content(), deliveryInfo.createDate(), deliveryInfo.timeOut());
		endDate = System.currentTimeMillis();
		logger.info("调用发送结束，事务为 {}，当前时间为 {}， 总耗时为 {}", transactionId, endDate, endDate - beginDate);
	}

	private void doSendMessage(List<String> phoneNums, String content, Date createDate, Long timeOut) {
		try {
			// deliveryInfo.check();
			ChannelSendMessage sendMessage = new ChannelSendMessage();
			sendMessage.setPhoneNumber(phoneNums);
			sendMessage.setContent(content);
			sendMessage.setSendDate(createDate);
			sendMessage.setExpireDuration(timeOut);
			sendMessageService.sendMessage(sendMessage);
			logger.info("执行发送短信：{}：{}：{}", phoneNums, content);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * subscribeService 使用
	 * @param queueData
	 */
	@Override
	public void handQueueData(QueueData queueData) {
		this.processService.processQueueData(queueData.getTransactionId(), queueData.getDeliveryInfo(),
				queueData.getMessage());
	}

	/**
	 * publishService 使用
	 * @param cacheKey
	 * @param data
	 */
	@Override
	public void push(SmpCacheKey cacheKey, QueueData data) {
		String json = serializeTheMessage(data);
		cacheService.queuePushToRight(cacheKey.key(), json);
	}

	/**
	 * subscribeService 使用
	 * @param timeout
	 * @param keys
	 * @return
	 */
	@Override
	public QueueData poll(int timeout, String[] keys) {
		List<String> datas = cacheService.queueBlockPopFromLeft(timeout, keys);
		return convertToQueueData(datas);
	}

	private QueueData convertToQueueData(List<String> datas) {
		if (datas == null || datas.size() != 2) {
			return null;
		}
		return deserializeTheData(datas.get(1));
	}

	/**
	 * 反序列化数据
	 * @param data
	 * @return
	 */
	private QueueData deserializeTheData(String data) {
		QueueData result = new QueueData();
		JSONObject jsonObject = JSONObject.parseObject(data);

		MessageEntity message = ModelBuilder.createShortMessage(descrializePhoneNums(jsonObject),
				jsonObject.getString("content"));
		DeliveryAddressEntity address = ModelBuilder.createDeliveryAddress(jsonObject.getString("businessName"),
				jsonObject.getString("priority"));
		DeliveryInfoEntity deliveryInfo = new DeliveryInfoEntity(address, jsonObject.getDate("createDate"),
				jsonObject.getLong("timeOut"));

		String transactionId = jsonObject.getString("transactionId");

		result.setMessage(message);
		result.setDeliveryInfo(deliveryInfo);
		result.setTransactionId(transactionId);
		return result;
	}

	private List<String> descrializePhoneNums(JSONObject jsonObject) {
		JSONArray phoneNumsArray = jsonObject.getJSONArray("phoneNums");
		if (phoneNumsArray != null) {
			List<String> result = new ArrayList<>(phoneNumsArray.size());
			for (int i = 0; i < phoneNumsArray.size(); i++) {
				result.add(phoneNumsArray.getString(i));
			}
			return result;
		}
		return null;
	}

	/**
	 * 序列化数据
	 * @param data
	 * @return
	 */
	private String serializeTheMessage(QueueData data) {
		MessageEntity message = data.getMessage();
		DeliveryInfoEntity deliveryInfo = data.getDeliveryInfo();
		DeliveryAddressEntity address = deliveryInfo.address();

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("phoneNums", message.phoneNums());
		jsonObject.put("content", message.content());
		jsonObject.put("businessName", address.businessName());
		jsonObject.put("priority", address.priority().toString());
		jsonObject.put("createDate", deliveryInfo.createDate());
		jsonObject.put("timeOut", deliveryInfo.timeOut());
		jsonObject.put("transactionId", data.getTransactionId());

		return jsonObject.toJSONString();
	}
}
