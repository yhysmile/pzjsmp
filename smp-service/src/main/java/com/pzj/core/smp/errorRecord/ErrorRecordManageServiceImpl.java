package com.pzj.core.smp.errorRecord;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pzj.core.smp.common.exception.SmpExceptionCode;
import com.pzj.framework.context.Result;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.toolkit.Check;

/**
 * Created by Administrator on 2017-1-9.
 * 短信错误记录业务实现类
 * @author ZhouYuan
 */
@Service("errorRecordManageService")
public class ErrorRecordManageServiceImpl implements IErrorRecordManageService {
	private static final Logger logger = LoggerFactory.getLogger(ErrorRecordManageServiceImpl.class);
	@Resource
	private ErrorRecordService errorRecordService;

	@Override
	public Result<Integer> insert(ErrorRecordModel errorRecordModel) {
		try {
			if (Check.NuNObject(errorRecordModel) || Check.NuNStrStrict(errorRecordModel.getSendErrDetail())) {
				logger.warn("illegal args, param: {}.", JSONConverter.toJson(errorRecordModel));
				return new Result<>(SmpExceptionCode.PARAMS_NULL.getCode(), SmpExceptionCode.PARAMS_NULL.getMsg());
			}

			if (logger.isInfoEnabled()) {
				logger.info("inster error record param: {}.", JSONConverter.toJson(errorRecordModel));
			}

			Integer inResult = errorRecordService.insert(errorRecordModel);

			if (logger.isInfoEnabled()) {
				logger.info("insert result: {}", inResult);
			}

			return new Result<>(inResult);
		} catch (Exception e) {
			logger.error("insert error record fail,param errorRecordModel: " + JSONConverter.toJson(errorRecordModel),
					e);
			return new Result<>(SmpExceptionCode.ERROR_RECORD_INSERT_ERROR.getCode(),
					SmpExceptionCode.ERROR_RECORD_INSERT_ERROR.getMsg());
		}
	}

	@Override
	public Result<ErrorRecordModel> selectByRecordId(Long recordId) {
		try {
			if (recordId == null) {
				logger.warn("illegal args, recordId: {}.", recordId);
				return new Result<>(SmpExceptionCode.RECORD_ID_NULL.getCode(), SmpExceptionCode.RECORD_ID_NULL.getMsg());
			}
			if (logger.isInfoEnabled()) {
				logger.debug("param id: {}", recordId);
			}

			ErrorRecordModel errorRecordModel = errorRecordService.selectByRecordId(recordId);
			if (logger.isInfoEnabled()) {
				logger.debug("result: {}", JSONConverter.toJson(errorRecordModel));
			}

			return new Result<>(errorRecordModel);
		} catch (Exception e) {
			logger.error("query error record fail, id is " + (recordId), e);
			return new Result<>(SmpExceptionCode.ERROR_RECORD_QUERY_ERROR.getCode(),
					SmpExceptionCode.ERROR_RECORD_QUERY_ERROR.getMsg());
		}
	}
}
