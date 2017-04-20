package com.pzj.core.smp.delivery;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import com.pzj.framework.converter.JSONConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pzj.core.smp.business.BusinService;
import com.pzj.core.smp.common.exception.SmpException;
import com.pzj.core.smp.common.exception.SmpExceptionCode;
import com.pzj.core.smp.message.MessageEntity;
import com.pzj.core.smp.scheduling.SchedulingMessageServiceImpl;
import com.pzj.core.smp.util.CommonMatches;
import com.pzj.core.smp.util.ModelBuilder;
import com.pzj.core.smp.util.RpcCaller;
import com.pzj.framework.context.Result;

/**
 * Created by Administrator on 2016-12-29.
 */
public class ShortMessageServiceImpl implements IShortMessageService {
	private static Logger logger = LoggerFactory.getLogger(ShortMessageServiceImpl.class);

	private BusinService businService;

	private SchedulingMessageServiceImpl schedulingMessageService;

	@Override
	public Result<Boolean> sendMessage(final MessageBean messageBean) {
		return RpcCaller.call(new RpcCaller<Boolean>() {
			@Override
			public Boolean call() {
				doSendMessage(messageBean);
				return true;
			}
		});
	}

	private void doSendMessage(MessageBean messageBean) {
		if (logger.isInfoEnabled()){
			logger.info("开始处理，当前时间为 {} ，数据为 {}", System.currentTimeMillis(), JSONConverter.toJson(messageBean));
		}
		verifyMessageBean(messageBean);

		// 1. 校验业务名和优先级
		DeliveryInfoEntity deliveryInfo = ModelBuilder.createDeliveryInfo(messageBean.getHead());

		// 2. 模板渲染出真正发送的内容
		// TODO
		String renderContent = messageBean.getContent();

		// 3. 创建消息
		MessageEntity message = ModelBuilder.createShortMessage(messageBean.getPhoneNums(), renderContent);

		if (logger.isInfoEnabled()) {
			logger.info("开始投递到优先级队列，当前时间为 {}", System.currentTimeMillis());
		}

		// 4. 执行消息投递
		schedulingMessageService.publishMessage(deliveryInfo, message);
		if (logger.isInfoEnabled()) {
			logger.info("完成处理，当前时间为 {}", System.currentTimeMillis());
		}
	}

	private void verifyMessageBean(MessageBean messageBean) {
		assertArgumentNotEmpty(messageBean, "MessageBean 不能为空。");
		assertArgumentNotEmpty(messageBean.getContent(), "MessageBean 的 Content 不能为空。");
		assertArgumentNotEmpty(messageBean.getPhoneNums(), "MessageBean 的 PhoneNums 不能为空。");
		assertArgumentNotEmpty(messageBean.getHead(), "MessageBean 的 Head 不能为空。");
		assertArgumentNotEmpty(messageBean.getHead().getBusinessName(), "MessageHead 的 BusinessName 不能为空。");
		assertArgumentNotEmpty(messageBean.getHead().getPriority(), "MessageBean 的 Priority 不能为空。");
		assertArgumentNotEmpty(messageBean.getHead().getCreateDate(), "MessageBean 的 CreateDate 不能为空。");
		assertArgumentNotEmpty(messageBean.getHead().getTimeOut(), "MessageBean 的 TimeOut 不能为空。");

		validPhoneNums(messageBean.getPhoneNums());

		validBussiness(messageBean.getHead().getBusinessName());
	}

	private void validPhoneNums(List<String> phoneNums) {
		Iterator<String> iterator = phoneNums.iterator();
		while (iterator.hasNext()) {
			String phoneNum = iterator.next();
			if (!CommonMatches.checkPhone(phoneNum)) {
				iterator.remove();
			}
		}
		if (phoneNums.isEmpty())
			throw new SmpException(SmpExceptionCode.PARAMS_NULL, "没有至少一个有效的手机号。");
	}

	private void validBussiness(String businessName) {
		boolean valid = businService.selectByName(businessName);
		if (!valid) {
			SmpExceptionCode error = SmpExceptionCode.DELIVERY_BUSSINESSNAME_INVALID;
			throw new SmpException(error, error.getTemplateMessage(businessName));
		}
	}

	private void assertArgumentNotEmpty(Object param, String message) {
		if (param == null)
			throw new SmpException(SmpExceptionCode.PARAMS_NULL, message);
	}

	@Resource
	public void setSchedulingMessageService(SchedulingMessageServiceImpl schedulingMessageService) {
		this.schedulingMessageService = schedulingMessageService;
	}

	@Resource
	public void setBusinService(BusinService businService) {
		this.businService = businService;
	}
}