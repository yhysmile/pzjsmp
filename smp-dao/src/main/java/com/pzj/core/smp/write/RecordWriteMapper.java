package com.pzj.core.smp.write;

import java.util.ArrayList;
import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.pzj.core.smp.record.RecordEntity;
import com.pzj.framework.sharding.annotation.Shard;
import com.pzj.framework.sharding.annotation.TimeShard;

public interface RecordWriteMapper {
	@Shard(time_shard = { @TimeShard(table = "record", field = "date", formatter = "MM", joiner = '_') })
	Integer updateRecordState(@Param(value = "date") Date operDate, @Param(value = "recordId") Long recordId,
			@Param(value = "recordState") Integer recordSatate);

	/**
	 * 插入
	 *
	 * @param record
	 * @return
	 */
	@Shard(time_shard = { @TimeShard(table = "record", field = "date", formatter = "MM", joiner = '_') })
	Integer insert(@Param(value = "date") Date operDate, @Param(value = "record") RecordEntity record);

	/**
	 * 批量插入
	 * 
	 * @param entitys
	 * @return
	 */
	@Shard(time_shard = { @TimeShard(table = "record", field = "date", formatter = "MM", joiner = '_') })
	Integer insertBatch(@Param(value = "date") Date operDate,
			@Param(value = "records") ArrayList<RecordEntity> entitys);
}