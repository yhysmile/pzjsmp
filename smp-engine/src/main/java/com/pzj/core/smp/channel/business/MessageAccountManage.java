package com.pzj.core.smp.channel.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.smp.channel.common.ChannelAccessConstant;
import com.pzj.core.smp.channel.common.ObjUtils;
import com.pzj.core.smp.channel.enums.ChannelIndentityEnum;
import com.pzj.core.smp.channel.model.AccountWarnResp;
import com.pzj.core.smp.channel.model.ChannelUserinfo;
import com.pzj.core.smp.channel.model.SendSmsChannelResp;
import com.pzj.core.smp.common.exception.SmpException;
import com.pzj.core.smp.common.exception.SmpExceptionCode;
import com.pzj.framework.converter.JSONConverter;

@Component("messageAccountManage")
public class MessageAccountManage {
	private final Logger logger = LoggerFactory.getLogger(MessageAccountManage.class);
	@Resource(name = "hlqxtChannelManage")
	private HLQXTChannelManage hlqxtChannelManage;
	@Resource(name = "gstChannelManage")
	private GSTChannelManage gstChannelManage;

	/**
	 * 账户告警处理业务逻辑
	 * @return List<AccountWarnResp>
	 */
	public List<AccountWarnResp> accountWarn() {
		List<AccountWarnResp> accountWarns = new ArrayList<AccountWarnResp>();
		//添加鸿联的短信账户告警
		accountWarns.add(getHLQXTAccount());
		//添加高斯通的短信账户告警
		accountWarns.add(getGSTAccount());
		return accountWarns;
	}

	/**
	 * 鸿联账户告警处理
	 * @return
	 */
	private AccountWarnResp getHLQXTAccount() {
		AccountWarnResp accountWarnResp = null;
		try {
			SendSmsChannelResp channelResp = hlqxtChannelManage.getAccountInfo();
			if (null == channelResp || null == channelResp.getContent()) {
				return accountWarnResp;
			}
			ChannelUserinfo channelUserinfo = hlqxtChannelManage.getChannelUserinfo();
			if (ObjUtils.checkObjectIsNull(channelUserinfo)) {
				throw new SmpException(SmpExceptionCode.NOT_FOUND_HLQXT_CHANNELUSER_ERR);
			}

			Integer totalNum = Integer.parseInt(channelResp.getContent());
			Integer accountLimit = channelUserinfo.getBalance();

			if (null != accountLimit && null != totalNum && totalNum <= accountLimit) {
				accountWarnResp = new AccountWarnResp();
				accountWarnResp.setChannelIndentity(ChannelIndentityEnum.HLQXT);
				String subject = ChannelAccessConstant.HLQXT_ACCOUNT_WARN_SUBJECT;
				String content = ChannelAccessConstant.HLQXT_ACCOUNT_WARN_CONTENT;
				content = content.replace("#", "剩余可发送条数为：" + totalNum + "条");
				accountWarnResp.setSubject(subject);
				accountWarnResp.setContent(content);
			}
		} catch (Exception e) {
			if (!(e instanceof IOException || e instanceof SmpException)) {
				logger.error("get hlqxt account info error!", e);
			}
			//			throw new SmpException(SmpExceptionCode.ERROR);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getHLQXTAccount result:{}", JSONConverter.toJson(accountWarnResp));
		}
		return accountWarnResp;
	}

	/**
	 * 高斯通账户告警处理
	 * @return
	 */
	private AccountWarnResp getGSTAccount() {
		AccountWarnResp accountWarnResp = null;
		try {
			SendSmsChannelResp channelResp = gstChannelManage.getAccountInfo();
			if (null == channelResp || null == channelResp.getContent()) {
				return accountWarnResp;
			}
			ChannelUserinfo channelUserinfo = gstChannelManage.getChannelUserinfo();
			if (ObjUtils.checkObjectIsNull(channelUserinfo)) {
				throw new SmpException(SmpExceptionCode.NOT_FOUND_GST_CHANNELUSER_ERR);
			}
			String msgContent = channelResp.getContent();

			String[] smsAccounts = msgContent.split(",");
			double balance = 0, smsPrice = 1;
			for (String smsAccount : smsAccounts) {
				if (smsAccount.indexOf(ChannelAccessConstant.GST_ACCOUNT_BALANCE_FLAG) >= 0) {
					balance = Double.parseDouble(smsAccount.split("=")[1]);
				}
				if (smsAccount.indexOf(ChannelAccessConstant.GST_ACCOUNT_PRICE_FLAG) >= 0) {
					smsPrice = Double.parseDouble(smsAccount.split("=")[1]);
				}
			}
			Integer totalNum = (int) (balance / smsPrice);
			Integer accountLimit = channelUserinfo.getBalance();

			if (null != accountLimit && null != totalNum && totalNum <= accountLimit) {
				accountWarnResp = new AccountWarnResp();
				accountWarnResp.setChannelIndentity(ChannelIndentityEnum.GST);
				String subject = ChannelAccessConstant.GST_ACCOUNT_WARN_SUBJECT;
				String content = ChannelAccessConstant.GST_ACCOUNT_WARN_CONTENT;
				content = content.replace("#", "剩余可发送条数为：" + totalNum + "条");
				accountWarnResp.setSubject(subject);
				accountWarnResp.setContent(content);
			}
		} catch (Exception e) {
			if (!(e instanceof IOException || e instanceof SmpException)) {
				logger.error("get gst account info error!", e);
			}
			//			throw new SmpException(SmpExceptionCode.ERROR);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getGSTAccount result:{}", JSONConverter.toJson(accountWarnResp));
		}
		return accountWarnResp;
	}
}
