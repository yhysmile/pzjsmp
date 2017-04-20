package com.pzj.core.smp.record;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.pzj.framework.converter.JSONConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pzj.core.smp.common.exception.SmpException;
import com.pzj.core.smp.common.exception.SmpExceptionCode;
import com.pzj.core.smp.read.RecordReadMapper;
import com.pzj.core.smp.util.ModelConvert;
import com.pzj.core.smp.write.RecordWriteMapper;
import com.pzj.framework.entity.PageableRequestBean;
import com.pzj.framework.entity.QueryResult;
import com.pzj.framework.idgen.IDGenerater;
import com.pzj.framework.toolkit.Check;

/**
 * Created by Administrator on 2017-1-3.
 * 短息记录核心处理层
 * @author ZhouYuan
 */
@Service("recordService")
public class RecordService {
	private static final Logger logger = LoggerFactory.getLogger(RecordService.class);
	@Resource
	private RecordWriteMapper recordWriteMapper;
	@Resource
	private RecordReadMapper recordReadMapper;
	@Resource(name = "idGenerater")
	private IDGenerater idGenerater;

	/**
	 * 插入单条短信记录
	 * @param recordModel
	 * @return
	 */
	public Long insert(RecordModel recordModel) {
		checkRecordState(recordModel);
		Long recordId = idGenerater.nextId();
		recordModel.setSendId(recordId);
		RecordEntity recordEntity = ModelConvert.convertTRecordEntity(recordModel);
		Integer inserResult = recordWriteMapper.insert(new Date(), recordEntity);
		if (inserResult != 0) {
			return recordId;
		}
		return Long.valueOf(inserResult);

	}

	/**
	 * 插入短信记录集合
	 * @param recordModels
	 * @return
	 */
	public List<Long> insertBatch(List<RecordModel> recordModels) {
		List<Long> ids = new ArrayList<Long>();
		for (RecordModel rd : recordModels) {
			checkRecordState(rd);
			Long recordId = idGenerater.nextId();
			rd.setSendId(recordId);
			ids.add(recordId);
		}
		ArrayList<RecordEntity> recordEntitys = ModelConvert.convertTRecordEntitys(recordModels);
		Integer inserResult = recordWriteMapper.insertBatch(new Date(), recordEntitys);
		if (inserResult != 0) {
			return ids;
		}
		return new ArrayList<Long>();
	}

	/**
	 * 根据主键id查询短信发送记录
	 * @param id
	 * @return
	 */
	public RecordModel selectById(Long id) {
		RecordEntity recordEntity = recordReadMapper.selectById(new Date(), id);
		if (logger.isDebugEnabled()) {
			logger.debug("record result: {}", JSONConverter.toJson(recordEntity));
		}
		RecordModel recordModel = ModelConvert.convertTRecordModel(recordEntity);
		return recordModel;

	}

	/**
	 * 根据id修改短信记录的发送状态
	 * @param recordId
	 * @param recordSatate
	 * @return
	 */
	public Integer updateRecordState(Long recordId, String recordSatate) {
		RecordStateEnum recordStateEnum = RecordStateEnum.valueOf(recordSatate);
		if (Check.NuNObject(recordStateEnum)) {
			logger.error("update error.reocrdId: {},recordState: {}", recordId, recordSatate);
			throw new SmpException(SmpExceptionCode.RECORD_STATE_ERROR);
		}
		Integer upResult = recordWriteMapper.updateRecordState(new Date(), recordId, recordStateEnum.getState());
		return upResult;
	}

	/**
	 * 分页查询短信发送记录
	 * @param recordModel
	 * @return
	 */
	public QueryResult<RecordModel> selectByParam(RecordModel recordModel) {
		if (!Check.NuNStrStrict(recordModel.getSendState())) {
			checkRecordState(recordModel);
		}
		RecordEntity recordEntity = ModelConvert.convertTRecordEntity(recordModel);
		int count = recordReadMapper.countByParam(new Date(), recordEntity);

		PageableRequestBean page = new PageableRequestBean();
		page.setCurrentPage(recordModel.getCurrentPage());
		page.setPageSize(recordModel.getPageSize());

		QueryResult<RecordModel> qr = new QueryResult<RecordModel>(recordModel.getCurrentPage(),
				recordModel.getPageSize());
		if (count == 0) {
			return qr;
		}

		List<RecordEntity> recordEntities = recordReadMapper.selectByParam(new Date(), recordEntity, page);

		if (Check.NuNObject(recordEntities)) {
			return qr;
		}
		List<RecordModel> recordModels = ModelConvert.convertsTRecordModels(recordEntities);
		qr.setTotal(count);
		qr.setRecords(recordModels);
		return qr;
	}

	/**
	 * 转换短信发送状态
	 * @param recordModel
	 * @return
	 */
	private RecordModel checkRecordState(RecordModel recordModel) {
		String recordState = recordModel.getSendState();

		if (!RecordStateEnum.getRecordValue(RecordStateEnum.SEND_ERROR).equals(recordState)
				&& !RecordStateEnum.getRecordValue(RecordStateEnum.SEND_SUCCESS).equals(recordState)) {
			throw new SmpException(SmpExceptionCode.RECORD_STATE_ERROR);
		}
		RecordStateEnum recordStateEnum = RecordStateEnum.valueOf(recordState);
		if (Check.NuNObject(recordStateEnum)) {
			throw new SmpException(SmpExceptionCode.RECORD_STATE_ERROR);
		}
		Integer inState = recordStateEnum.getState();
		if (inState == null) {
			throw new SmpException(SmpExceptionCode.RECORD_STATE_ERROR);
		}
		recordModel.setSendState(inState.toString());
		return recordModel;
	}
}
