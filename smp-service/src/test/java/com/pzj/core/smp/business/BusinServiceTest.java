package com.pzj.core.smp.business;

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
 * Created by Administrator on 2017-1-6.
 */
/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/spring.xml"
})*/
public class BusinServiceTest {

	static ApplicationContext context = null;
	private IBusinManageService businManageService;

	@BeforeClass
	public static void setUpClass() {
		context = new ClassPathXmlApplicationContext("applicationContext-test.xml");
	}

	@Before
	public void setUp() {
		businManageService = context.getBean(IBusinManageService.class);
	}

	private final Logger logger = LoggerFactory.getLogger(BusinServiceTest.class);

	/**
	 * 添加记业务线
	 */
	/*	@Test
		public void insertBusin() {
			BusinModel businModel = new BusinModel(null, "TEST2_TEST", "测试产品线描述", "DISABLE", new Timestamp(
					System.currentTimeMillis()), null);
			logger.debug("businModel is {}", JSONConverter.toJson(businModel));
			Result<Long> integerResult = businManageService.insert(businModel);
			logger.debug("integerResult is {}", JSONConverter.toJson(integerResult));

		}*/

	/**
	 * 根据业务线id修改业务线状态 DISABLE 禁用，ENABLE 启用
	 */
	@Test
	public void upBusinState() {
		Result<Long> uprResult = businManageService.updateState(821996489070391296l, "ENABLE");
		logger.debug("uprResult is {}", JSONConverter.toJson(uprResult));
	}

	/**
	 * 根据业务线英文名称验证是业务线否存在 不验证状态
	 */
	/*@Test
	public void selectByAlias() {
		Result<Boolean> result = businManageService.selectByName("SPU2");
		logger.debug("result is {}", JSONConverter.toJson(result));
	}*/

	/**
	 * 分页查询业务线  ENABLE 启用  DISABLE 禁用
	 */
	/*		@Test
			public void selectByParam(){
				BusinModel businModel = new BusinModel();
				businModel.setState("ENABLE");
				Result<QueryResult<BusinModel>> queryResult = businManageService.selectByParam(businModel);
				logger.debug("分页查询结果{}",JSONConverter.toJson(queryResult));
			}*/
}
