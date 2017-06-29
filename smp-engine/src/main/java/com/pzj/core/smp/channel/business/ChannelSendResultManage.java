package com.pzj.core.smp.channel.business;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.smp.channel.common.ChannelAccessConstant;
import com.pzj.core.smp.channel.common.StrUtils;
import com.pzj.core.smp.channel.enums.ChannelIndentityEnum;
import com.pzj.core.smp.channel.enums.ChannelTypeEnum;
import com.pzj.core.smp.channel.enums.GSTRespCodeEnum;
import com.pzj.core.smp.channel.enums.HLQXTRespCodeEnum;
import com.pzj.core.smp.channel.enums.MASRespCodeEnum;
import com.pzj.core.smp.channel.model.ChannelInfo;
import com.pzj.core.smp.channel.model.ChannelSendMessage;
import com.pzj.core.smp.channel.model.SendSmsChannelResp;
import com.pzj.core.smp.common.exception.SmpException;
import com.pzj.core.smp.common.exception.SmpExceptionCode;
import com.pzj.core.smp.errorRecord.ErrorRecordModel;
import com.pzj.core.smp.errorRecord.ErrorRecordService;
import com.pzj.core.smp.record.RecordModel;
import com.pzj.core.smp.record.RecordService;
import com.pzj.core.smp.record.RecordStateEnum;
import com.pzj.framework.converter.JSONConverter;

@Component("channelSendResultManage")
public class ChannelSendResultManage {

	private final Logger logger = LoggerFactory.getLogger(ChannelSendResultManage.class);
	@Resource(name = "recordService")
	private RecordService recordService;
	@Resource(name = "errorRecordService")
	private ErrorRecordService errorRecordService;

	/**
	 * 保存短信发送结果
	 * @param sendMessage
	 * @param sendSmsChannelResp
	 * @param channelInfo
	 */
	public void saveSendMessageResult(ChannelSendMessage sendMessage, SendSmsChannelResp sendSmsChannelResp,
			ChannelInfo channelInfo) {
		if (logger.isDebugEnabled()) {
			logger.debug("saveSendMessageResult start sendMessage:{}, sendSmsChannelResp:{}, channelInfo:{}",
					JSONConverter.toJson(sendMessage), JSONConverter.toJson(sendSmsChannelResp),
					JSONConverter.toJson(channelInfo));
		}
		String sendState = RecordStateEnum.getRecordValue(RecordStateEnum.SEND_ERROR);
		String sendLinkId = sendMessage.getSendLinkId();
		if (StrUtils.checkStringIsNullStrict(sendLinkId) && null != sendSmsChannelResp) {
			String respContent = sendSmsChannelResp.getContent();
			if (null != respContent && respContent.contains(ChannelAccessConstant.GST_SEND_SUCCESS_FLAG)) {
				String[] contentArr = respContent.split(ChannelAccessConstant.GST_SEND_SUCCESS_FLAG);
				if (contentArr.length > 1) {
					sendLinkId = contentArr[1];
				}
			}
		}

		//检查此次发送是否失败
		Integer channelIdentity = getChannelIdentity(channelInfo);
		Boolean isErr = checkIsErr(sendSmsChannelResp, channelIdentity);
		if (!isErr) {
			sendState = RecordStateEnum.getRecordValue(RecordStateEnum.SEND_SUCCESS);
		}

		List<RecordModel> recordModels = new ArrayList<RecordModel>();
		Iterator<String> itera = sendMessage.getPhoneNumber().iterator();
		String phone = null;
		RecordModel recordModel = null;
		Timestamp time = null;
		while (itera.hasNext()) {
			time = new Timestamp(System.currentTimeMillis());
			phone = itera.next();
			//保存短信发送记录
			recordModel = new RecordModel();
			recordModel.setSendPhone(phone);
			recordModel.setSendState(sendState);
			if (null != channelInfo) {
				recordModel.setChannelId(channelInfo.getId());
				recordModel.setChannelName(channelInfo.getName());
			} else {
				recordModel.setChannelId(0L);
				recordModel.setChannelName("0");
			}
			recordModel.setSendContent(sendMessage.getContent());
			recordModel.setSendTime(time);
			recordModel.setSendNum(1);
			sendLinkId = StrUtils.checkStringIsNullStrict(sendLinkId) ? "0" : sendLinkId;
			recordModel.setSendLinkId(sendLinkId);
			recordModels.add(recordModel);

		}

		//入库操作
		if (recordModels.size() > 0) {
			List<Long> recordIds = recordService.insertBatch(recordModels);

			String errTip = sendMessage.getErrTip();
			Boolean errFlag = !StrUtils.checkStringIsNullStrict(errTip);
			//保存短信发送错误日志
			if (isErr || errFlag) {
				logger.error(
						"send message return error record error log, param sendMessage:{},sendSmsChannelResp:{},channelInfo:{}",
						JSONConverter.toJson(sendMessage), JSONConverter.toJson(sendSmsChannelResp),
						JSONConverter.toJson(channelInfo));
				String respContent = getErrDetail(channelIdentity, sendSmsChannelResp);
				errTip = errFlag ? errTip : respContent;
				for (Long recordId : recordIds) {
					time = new Timestamp(System.currentTimeMillis());

					ErrorRecordModel errorRecordModel = new ErrorRecordModel();
					errorRecordModel.setSendRecordId(recordId);
					errorRecordModel.setSendErrDetail(errTip);
					errorRecordModel.setSendTime(time);
					errorRecordService.insert(errorRecordModel);
				}
			}
		}

	}

	/**
	 * 获取通道标识
	 * @param channelInfo
	 * @return Integer
	 */
	public Integer getChannelIdentity(ChannelInfo channelInfo) {
		Integer channelIdentity = null;
		if (null == channelInfo) {
			return channelIdentity;
		}
		ChannelTypeEnum channelType = ChannelTypeEnum.getChannelType(channelInfo.getChannelType());
		if (ChannelTypeEnum.HLQXT_DOWNLINK == channelType) {
			channelIdentity = ChannelIndentityEnum.HLQXT.getKey();
		} else if (ChannelTypeEnum.GST_DOWNLINK == channelType) {
			channelIdentity = ChannelIndentityEnum.GST.getKey();
		} else if (ChannelTypeEnum.MAS_DOWNLINK == channelType) {
			channelIdentity = ChannelIndentityEnum.MAS.getKey();
		}
		if (null == channelIdentity) {
			throw new SmpException(SmpExceptionCode.NO_AVAIABLE_CHANNEL_ERR);
		}

		return channelIdentity;
	}

	/**
	 * 检查是否发送失败
	 * @param sendSmsChannelResp
	 * @param channelIdentity
	 * @return Boolean true:发送错误，false:发送成功
	 */
	public Boolean checkIsErr(SendSmsChannelResp sendSmsChannelResp, Integer channelIdentity) {
		Boolean flag = Boolean.TRUE;
		if (null == sendSmsChannelResp || null == channelIdentity || null == sendSmsChannelResp.getCode()) {
			return flag;
		}
		if (ChannelIndentityEnum.HLQXT.getKey() == channelIdentity
				&& sendSmsChannelResp.getContent().equals(HLQXTRespCodeEnum.SUCCESS.getCode())) {
			flag = Boolean.FALSE;
		} else if (ChannelIndentityEnum.GST.getKey() == channelIdentity
				&& sendSmsChannelResp.getContent().contains(ChannelAccessConstant.GST_SEND_SUCCESS_FLAG)) {
			flag = Boolean.FALSE;
		} else if (ChannelIndentityEnum.MAS.getKey() == channelIdentity
				&& sendSmsChannelResp.getContent().equals(MASRespCodeEnum.SUCCESS.getCode())) {
			flag = Boolean.FALSE;
		}
		return flag;
	}

	/**
	 * 获取通道错误详情
	 * @param channelIdentity
	 * @param sendSmsChannelResp
	 * @return
	 */
	private String getErrDetail(Integer channelIdentity, SendSmsChannelResp sendSmsChannelResp) {
		String errDetail = "";
		if (null == channelIdentity || null == sendSmsChannelResp) {
			return errDetail;
		}
		String respContent = sendSmsChannelResp.getContent() == null ? "" : sendSmsChannelResp.getContent();
		if (ChannelIndentityEnum.HLQXT.getKey() == channelIdentity) {
			HLQXTRespCodeEnum hlqxtResp = HLQXTRespCodeEnum.getRespCode(respContent);
			errDetail = hlqxtResp == null ? respContent : hlqxtResp.getCode() + ":" + hlqxtResp.getDetail();
		} else if (ChannelIndentityEnum.GST.getKey() == channelIdentity) {
			GSTRespCodeEnum gstResp = GSTRespCodeEnum.getRespCode(respContent);
			errDetail = gstResp == null ? respContent : gstResp.getCode() + ":" + gstResp.getDetail();
		} else if (ChannelIndentityEnum.MAS.getKey() == channelIdentity) {
			MASRespCodeEnum masResp = MASRespCodeEnum.getRespCode(respContent);
			errDetail = masResp == null ? respContent : masResp.getCode() + ":" + masResp.getDetail();
		}
		return errDetail;
	}
}
