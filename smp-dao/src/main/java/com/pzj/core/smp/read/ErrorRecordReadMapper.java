package com.pzj.core.smp.read;

import org.apache.ibatis.annotations.Param;

import com.pzj.core.smp.errorRecord.ErrorRecordEntity;

public interface ErrorRecordReadMapper {

	ErrorRecordEntity selectByRecordId(@Param(value = "recordId") Long recordId);

}