package com.pzj.core.smp.job;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pzj.core.smp.channel.AccountWarnService;

@Component("messageAccountJob")
public class MessageAccountJob {
	private final Logger logger = LoggerFactory.getLogger(MessageAccountJob.class);
	@Resource(name = "accountWarnService")
	private AccountWarnService accountWarnService;

	public void messageAccountWarn() {
		if (logger.isDebugEnabled()) {
			logger.debug("========短信账户告警定时器开始执行============");
		}

		accountWarnService.accountWarn();

		if (logger.isDebugEnabled()) {
			logger.debug("========短信账户告警定时器执行结束============");
		}
	}
}
