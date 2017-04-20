package com.pzj.core.smp.record;

import java.util.List;

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
import com.pzj.framework.entity.QueryResult;
import com.pzj.framework.exception.ServiceException;

/**
 * Created by Administrator on 2017-1-3.
 * 短信记录Controller类
 * @author  ZhouYuan
 */
@Controller
@RequestMapping(value = "/record")
public class RecordController {
	private static final Logger logger = LoggerFactory.getLogger(RecordController.class);
	@Resource
	private IRecordManageService recordManageService;

	@RequestMapping(value = { "list", "" })
	public String records(@RequestAttribute JSONObject requestObject, Model model) {
		try {
			if (logger.isInfoEnabled()) {
				logger.info("request param: {}", JSONObject.toJSON(requestObject));
			}
			RecordVo recordVO = toRecordVO(requestObject);
			RecordModel recordModel = VoConvert.convertTRecordModel(recordVO);
			Result<QueryResult<RecordModel>> rbResult = recordManageService.selectByParam(recordModel);
			if (logger.isInfoEnabled()) {
				logger.info("result: {}", JSONConverter.toJson(rbResult));
			}
			//返回result
			Result<QueryResult<RecordVo>> voResult = new Result<>();
			voResult.setData(new QueryResult<RecordVo>(recordVO.getCurrentPage(), recordVO.getPageSize()));
			if (rbResult.isOk()) {
				List<RecordModel> recordModels = rbResult.getData().getRecords();
				List<RecordVo> rVOs = VoConvert.convertTRecordVOs(recordModels);
				voResult.getData().setRecords(rVOs);
				voResult.getData().setTotal(rbResult.getData().getTotal());
			}
			model.addAttribute("roResult", voResult);
			model.addAttribute("recordVO", recordVO);
		} catch (Throwable t) {
			logger.error("SmpApiInterceptor preHandle data is error{}", t);
		}
		return "modules/record/recordList";
	}

	public RecordVo toRecordVO(JSONObject jsonObject) {
		try {
			String requestJson = jsonObject.toJSONString();
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return objectMapper.readValue(requestJson, RecordVo.class);
		} catch (Throwable t) {
			logger.error("params is error {}", JSONConverter.toJson(jsonObject));
			throw t instanceof ServiceException ? (ServiceException) t : new ParameterException(t);
		}

	}
}
