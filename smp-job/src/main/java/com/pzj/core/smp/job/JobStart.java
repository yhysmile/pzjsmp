/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.smp.job;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author Administrator
 * @version $Id: JobStart.java, v 0.1 2016年10月18日 上午11:25:04 Administrator Exp $
 */
public class JobStart {
    private static ApplicationContext context;

    public static void main(String[] args) {
        context = new ClassPathXmlApplicationContext("classpath:META-INF/spring/smp-job-applicationContext-quartz.xml");
        System.out.println("短信平台job已经启动：" + context);
    }
}
