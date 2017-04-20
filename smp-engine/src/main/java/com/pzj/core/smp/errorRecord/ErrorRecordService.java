package com.pzj.core.smp.errorRecord;

import javax.annotation.Resource;

import com.pzj.framework.converter.JSONConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pzj.core.smp.read.ErrorRecordReadMapper;
import com.pzj.core.smp.util.ModelConvert;
import com.pzj.core.smp.write.ErrorRecordWriteMapper;

/**
 * Created by Administrator on 2017-1-9.
 * 短信错误记录核心处理
 * @author ZhouYuan
 */
@Service("errorRecordService")
public class ErrorRecordService {
	@Resource
	private ErrorRecordWriteMapper errorRecordWriteMapper;
	@Resource
	private ErrorRecordReadMapper errorRecordReadMapper;

	/**
	 * 插入单条短信记录
	 * @param errorRecordModel
	 * @return
	 */
	public Integer insert(ErrorRecordModel errorRecordModel) {
		ErrorRecordEntity errorRecordEntity = ModelConvert.convertTErrRecordEntity(errorRecordModel);
		Integer inserResult = errorRecordWriteMapper.insert(errorRecordEntity);
		return inserResult;
	}

	/**
	 * 根据主键id查询短信发送错误记录
	 * @param recordId
	 * @return
	 */
	public ErrorRecordModel selectByRecordId(Long recordId) {

		ErrorRecordEntity errorRecordEntity = errorRecordReadMapper.selectByRecordId(recordId);
		ErrorRecordModel errorRecordModel = ModelConvert.convertTErrRecordModel(errorRecordEntity);
		return errorRecordModel;

	}
}
