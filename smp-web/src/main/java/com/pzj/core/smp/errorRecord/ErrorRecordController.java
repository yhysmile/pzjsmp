package com.pzj.core.smp.errorRecord;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pzj.core.smp.common.exception.ParameterException;
import com.pzj.core.smp.common.utils.VoConvert;
import com.pzj.framework.context.Result;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.exception.ServiceException;

/**
 * Created by Administrator on 2017-1-10.
 * 短信错误记录controller类
 * @author ZhouYuan
 */
@Controller
@RequestMapping(value = "/error/record")
public class ErrorRecordController {
	private static final Logger logger = LoggerFactory.getLogger(ErrorRecordController.class);
	@Resource
	private IErrorRecordManageService errorRecordManageService;

	@RequestMapping(value = { "selectByRecordId", "" })
	public String selectByRecordId(@RequestAttribute JSONObject requestObject, Model model) {
		try {
			if(logger.isInfoEnabled()) {
				logger.info("request param: {}", JSONObject.toJSON(requestObject));
			}
			ErrorRecordVo errorRecordVO = toErrorRecordVO(requestObject);
			Result<ErrorRecordModel> erbResult = errorRecordManageService
					.selectByRecordId(errorRecordVO.getSendRecordId());
			if(logger.isInfoEnabled()) {
				logger.info("result: {}", JSONConverter.toJson(erbResult));
			}
			if (!erbResult.isOk()) {
				model.addAttribute("errorRecordVO", new ErrorRecordVo());
			} else {
				ErrorRecordVo erVO = VoConvert.convertTErrRecordVO(erbResult.getData());
				model.addAttribute("errorRecordVO", erVO);
			}
		} catch (Throwable t) {
			logger.error("SmpApiInterceptor preHandle data is error{}", t);
		}
		return "modules/errorRecord/errorDetail";
	}

	public ErrorRecordVo toErrorRecordVO(JSONObject jsonObject) {
		try {
			String requestJson = jsonObject.toJSONString();
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return objectMapper.readValue(requestJson, ErrorRecordVo.class);
		} catch (Throwable t) {
			logger.error("params is {}", JSONConverter.toJson(jsonObject));
			throw t instanceof ServiceException ? (ServiceException) t : new ParameterException(t);
		}

	}
}
