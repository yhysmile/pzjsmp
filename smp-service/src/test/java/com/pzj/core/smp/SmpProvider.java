/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.smp;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author Administrator
 * @version $Id: SmpProvider.java, v 0.1 2016年10月26日 上午10:51:59 Administrator Exp $
 */
public class SmpProvider {
	private static Logger logger = LoggerFactory.getLogger(SmpProvider.class);
	private static ApplicationContext context;

	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("classpath*:META-INF/spring/applicationContext.xml");
		System.out.println("短信平台服务已启动：" + context);
		try {
			System.in.read();
		} catch (IOException e) {
			logger.error("短信平台服务启动异常！", e);
		}
	}
}
