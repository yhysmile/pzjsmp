package com.pzj.core.smp.write;

import org.apache.ibatis.annotations.Param;

import com.pzj.core.smp.errorRecord.ErrorRecordEntity;

public interface ErrorRecordWriteMapper {
	/**
	 * 插入
	 *
	 * @param errorRecordEntity
	 * @return
	 */
	Integer insert(@Param(value = "errorRecord") ErrorRecordEntity errorRecordEntity);
}