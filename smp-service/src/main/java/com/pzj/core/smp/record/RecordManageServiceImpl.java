package com.pzj.core.smp.record;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pzj.core.smp.common.exception.SmpException;
import com.pzj.core.smp.common.exception.SmpExceptionCode;
import com.pzj.framework.context.Result;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.entity.QueryResult;
import com.pzj.framework.toolkit.Check;

/**
 * Created by Administrator on 2017-1-3.
 * 业务端调用接口实现，仅供业务端使用
 * @author ZhouYuan
 */
@Service("recordManageService")
public class RecordManageServiceImpl implements IRecordManageService {
	private static final Logger logger = LoggerFactory.getLogger(RecordManageServiceImpl.class);
	@Resource
	private RecordService recordService;

	/**
	 * 插入短信发送记录
	 * @param recordModel
	 * @return
	 */
	@Override
	public Result<Long> insert(RecordModel recordModel) {
		try {
			if (Check.NuNObject(recordModel)) {
				logger.warn("illegal args, param: {}.", JSONConverter.toJson(recordModel));
				return new Result<>(SmpExceptionCode.PARAMS_NULL.getCode(), SmpExceptionCode.PARAMS_NULL.getMsg());
			}

			if (logger.isInfoEnabled()) {
				logger.info("insert record param: {}", JSONConverter.toJson(recordModel));
			}

			Long inResult = recordService.insert(recordModel);

			if (logger.isInfoEnabled()) {
				logger.info("insert result: {}", inResult);
			}
			if (inResult < 1) {
				return new Result<>(SmpExceptionCode.RECORD_INSERT_ERROR.getCode(),
						SmpExceptionCode.RECORD_INSERT_ERROR.getMsg());
			}
			return new Result<>(inResult);
		} catch (Exception e) {
			logger.error("insert record fail, param: " + JSONConverter.toJson(recordModel), e);
			if (e instanceof SmpException) {
				return new Result<>(((SmpException) e).getCode(), e.getMessage());
			}
			return new Result<>(SmpExceptionCode.RECORD_INSERT_ERROR.getCode(),
					SmpExceptionCode.RECORD_INSERT_ERROR.getMsg());
		}
	}

	/**
	 * 查询单条短信发送记录
	 * @param id
	 * @return
	 */
	@Override
	public Result<RecordModel> selectById(Long id) {
		try {
			if (id == null) {
				logger.warn("illegal args, id: {}.", id);
				return new Result<>(SmpExceptionCode.RECORD_ID_NULL.getCode(), SmpExceptionCode.RECORD_ID_NULL.getMsg());
			}
			if (logger.isInfoEnabled()) {
				logger.info("id: {}", id);
			}

			RecordModel recordModel = recordService.selectById(id);

			if (logger.isInfoEnabled()) {
				logger.debug("query result: {}", JSONConverter.toJson(recordModel));
			}

			if (Check.NuNObject(recordModel)) {
				return new Result<>(new RecordModel());
			}
			return new Result<>(recordModel);
		} catch (Exception e) {
			logger.error("insert record fail, id: " + id, e);
			if (e instanceof SmpException) {
				return new Result<>(((SmpException) e).getCode(), e.getMessage());
			}
			return new Result<>(SmpExceptionCode.RECORD_QUERY_ERROR.getCode(),
					SmpExceptionCode.RECORD_QUERY_ERROR.getMsg());
		}
	}

	/**
	 * 修改短信记录发送状态
	 * @param recordId
	 * @param recordSatate 常量字符串 SEND_SUCCESS or SEND_ERROR
	 * @return
	 */
	@Override
	public Result<Integer> updateRecordState(Long recordId, String recordSatate) {
		try {
			if (recordId == null) {
				logger.warn("illegal args, recordId: {}.", recordId);
				return new Result<>(SmpExceptionCode.RECORD_ID_NULL.getCode(), SmpExceptionCode.RECORD_ID_NULL.getMsg());
			}
			if (Check.NuNStrStrict(recordSatate)) {
				logger.warn("illegal args, recordSatate: {}.", recordSatate);
				return new Result<>(SmpExceptionCode.RECORD_STATE_NULL.getCode(),
						SmpExceptionCode.RECORD_STATE_NULL.getMsg());
			}

			if (logger.isInfoEnabled()) {
				logger.info("recordId: {},recordState: {}", recordId, recordSatate);
			}

			Integer upResult = recordService.updateRecordState(recordId, recordSatate);
			if (upResult < 1) {
				return new Result<>(SmpExceptionCode.RECORD_UPDATE_ERROR.getCode(),
						SmpExceptionCode.RECORD_UPDATE_ERROR.getMsg());
			}
			return new Result<>(upResult);
		} catch (Exception e) {
			logger.error("update record state fail, reocrdId: " + recordId + ",recordState: " + recordSatate, e);
			if (e instanceof SmpException) {
				return new Result<>(((SmpException) e).getCode(), e.getMessage());
			}
			return new Result<>(SmpExceptionCode.RECORD_QUERY_ERROR.getCode(),
					SmpExceptionCode.RECORD_QUERY_ERROR.getMsg());
		}
	}

	/**
	 * 分页查询短信发送记录
	 * @param recordModel
	 * @return
	 */
	@Override
	public Result<QueryResult<RecordModel>> selectByParam(RecordModel recordModel) {
		try {
			if (Check.NuNObject(recordModel)) {
				return new Result<>(SmpExceptionCode.PARAMS_NULL.getCode(), SmpExceptionCode.PARAMS_NULL.getMsg());
			}

			logger.debug("params recordModel is {}", JSONConverter.toJson(recordModel));
			QueryResult<RecordModel> qrd = recordService.selectByParam(recordModel);
			return new Result<>(qrd);
		} catch (Exception e) {
			logger.error("query page record fail, params recordModel: " + JSONConverter.toJson(recordModel), e);

			if (e instanceof SmpException) {
				return new Result<>(((SmpException) e).getCode(), e.getMessage());
			}
			return new Result<>(SmpExceptionCode.RECORD_QUERY_ERROR.getCode(),
					SmpExceptionCode.RECORD_QUERY_ERROR.getMsg());
		}
	}

}
