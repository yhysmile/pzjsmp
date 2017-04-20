package com.pzj.core.smp.record;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pzj.framework.context.Result;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.entity.QueryResult;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2017-1-6.
 */
/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/spring.xml"
})*/
public class RecordServiceTest {

	static ApplicationContext context = null;
	private IRecordManageService recordManageService;

	@BeforeClass
	public static void setUpClass() {
		context = new ClassPathXmlApplicationContext("applicationContext-test.xml");
	}

	@Before
	public void setUp() {
		recordManageService = context.getBean(IRecordManageService.class);
	}

	private final Logger logger = LoggerFactory.getLogger(RecordServiceTest.class);

	/**
	 * 添加短信记录
	 */
/*	@Test
		public void insertRecord() {
			RecordModel recordBean = new RecordModel("98765432100", "SEND_ERROR", "测试短信状态", 123456l, new Timestamp(
					System.currentTimeMillis()), "测试短信状态", 1, new Timestamp(System.currentTimeMillis()), "20170106");
			logger.debug("测试参数{}", JSONConverter.toJson(recordBean));

			Result<Long> integerResult = recordManageService.insert(recordBean);
			logger.debug("insert result{}", JSONConverter.toJson(integerResult));
		}*/

	/**
	 * 根据主键id查询记录
	 */
	/*	@Test
		public void selectById() {
			Result<RecordModel> recordBeanResult = recordManageService.selectById(32l);
			logger.debug("查询记录{}", JSONConverter.toJson(recordBeanResult));
		}*/

	/**
	 * 根据主键id查询修改发送状态
	 */
/*		@Test
		public void updateRecordState() {
			Result<Integer> result = recordManageService.updateRecordState(821561534285656064L, "SEND_ERROR");
			logger.debug("修改结果{}", JSONConverter.toJson(result));

		}*/

	/**
	 * 分页查询短信记录
	 */
	@Test
	public void selectByParam() {
		RecordModel recordBean = new RecordModel();
		//recordBean.setSendPhone("987654321");
		recordBean.setSendState("SEND_ERROR");
		Result<QueryResult<RecordModel>> queryResult = recordManageService.selectByParam(recordBean);
		logger.debug("分页查询结果{}", JSONConverter.toJson(queryResult));
	}
}
