package com.pzj.core.smp.errorRecord;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pzj.framework.context.Result;
import com.pzj.framework.converter.JSONConverter;

/**
 * Created by Administrator on 2017-1-10.
 */
public class ErrorRecordServiceTest {

	static ApplicationContext context = null;
	private IErrorRecordManageService errorRecordManageService;

	@BeforeClass
	public static void setUpClass() {
		context = new ClassPathXmlApplicationContext("applicationContext-test.xml");
	}

	@Before
	public void setUp() {
		errorRecordManageService = context.getBean(IErrorRecordManageService.class);
	}

	private final Logger logger = LoggerFactory.getLogger(ErrorRecordServiceTest.class);

	/**
	 * 添加短信错误记录
	 */
	/*@Test
	public void insertErrorRecord(){
		ErrorRecordModel errorRecordBean = new ErrorRecordModel();
		errorRecordBean.setSendRecordId(111l);
		errorRecordBean.setSendErrDetail("发送失败111");
		logger.debug("测试参数{}", JSONConverter.toJson(errorRecordBean));
	
		Result<Integer> integerResult = errorRecordManageService.insert(errorRecordBean);
		logger.debug("insert result{}",JSONConverter.toJson(integerResult));
	}
	*/
	/**
	 * 根据主键id查询错误记录
	 */
	@Test
	public void selectById() {
		Result<ErrorRecordModel> errorRecordBeanResult = errorRecordManageService.selectByRecordId(111l);
		logger.debug("查询记录{}", JSONConverter.toJson(errorRecordBeanResult));
	}
}
