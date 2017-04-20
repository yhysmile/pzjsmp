package com.pzj.core.smp.business;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pzj.core.smp.common.exception.ParameterException;
import com.pzj.core.smp.common.utils.VoConvert;
import com.pzj.framework.context.Result;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.entity.QueryResult;
import com.pzj.framework.exception.ServiceException;

/**
 * Created by Administrator on 2017-1-12.
 * 业务线管理Controller
 * @author ZhouYuan
 */
@Controller
@RequestMapping(value = "/busin")
public class BusinController {
	private static final Logger logger = LoggerFactory.getLogger(BusinController.class);
	@Resource
	private IBusinManageService businManageService;

	@RequestMapping(value = { "list", "" })
	public String busins(@RequestAttribute JSONObject requestObject, Model model) {
		try {
			if(logger.isInfoEnabled()){
				logger.info("request param: {}.", JSONObject.toJSON(requestObject));
			}

			BusinVo businVo = toBusinVO(requestObject);
			BusinModel businModel = VoConvert.convertTBusinModel(businVo);
			Result<QueryResult<BusinModel>> busResult = businManageService.selectByParam(businModel);
			if(logger.isInfoEnabled()){
				logger.info("result: {}.", JSONConverter.toJson(busResult));
			}

			//返回result
			Result<QueryResult<BusinVo>> voResult = new Result<>();
			voResult.setData(new QueryResult<BusinVo>(businVo.getCurrentPage(), businVo.getPageSize()));
			if (busResult.isOk()) {
				List<BusinModel> businModels = busResult.getData().getRecords();
				List<BusinVo> bVOs = VoConvert.convertTBusinVOs(businModels);
				voResult.getData().setRecords(bVOs);
				voResult.getData().setTotal(busResult.getData().getTotal());
			}
			model.addAttribute("buResult", voResult);
			model.addAttribute("businVo", businVo);
		} catch (Throwable t) {
			logger.error("SmpApiInterceptor preHandle data is error{}", t);
		}
		return "modules/busin/businList";
	}

	@ResponseBody
	@RequestMapping(value = { "updateBusinState", "" })
	public JSONObject updateBusinState(@RequestAttribute JSONObject requestObject, Model model) {
		Result<Long> busResult = new Result<>();
		try {
			if(logger.isInfoEnabled()){
				logger.info("request param: {}", JSONObject.toJSON(requestObject));
			}

			BusinVo businVo = toBusinVO(requestObject);
			busResult = businManageService.updateState(businVo.getId(), businVo.getState());
			if(logger.isInfoEnabled()) {
				logger.info("result: {}", JSONConverter.toJson(busResult));
			}
		} catch (Throwable t) {
			logger.error("SmpApiInterceptor preHandle data is error{}", t);
		}
		return JSONObject.parseObject(JSONConverter.toJson(busResult));
	}

	@RequestMapping(value = { "addBusin", "" })
	public String addBusin() {
		return "modules/busin/insertBusin";
	}

	@ResponseBody
	@RequestMapping(value = { "insertBusin", "" })
	public JSONObject insertBusin(@RequestAttribute JSONObject requestObject, Model model) {
		Result<Long> busResult = new Result<>();
		try {
			if(logger.isInfoEnabled()) {
				logger.info("request param:{}", JSONObject.toJSON(requestObject));
			}
			BusinVo businVo = toBusinVO(requestObject);
			BusinModel businModel = VoConvert.convertTBusinModel(businVo);
			busResult = businManageService.insert(businModel);
			if(logger.isInfoEnabled()) {
				logger.info("result: {}", JSONConverter.toJson(busResult));
			}
		} catch (Throwable t) {
			logger.error("SmpApiInterceptor preHandle data is error{}", t);
		}
		return JSONObject.parseObject(JSONConverter.toJson(busResult));
	}

	public BusinVo toBusinVO(JSONObject jsonObject) {
		try {
			String requestJson = jsonObject.toJSONString();
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return objectMapper.readValue(requestJson, BusinVo.class);
		} catch (Throwable t) {
			logger.error("param is error {}", JSONConverter.toJson(jsonObject));
			throw t instanceof ServiceException ? (ServiceException) t : new ParameterException(t);
		}
	}
}
