package com.pzj.core.smp.read;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pzj.core.smp.record.RecordEntity;
import com.pzj.framework.entity.PageableRequestBean;
import com.pzj.framework.sharding.annotation.Shard;
import com.pzj.framework.sharding.annotation.TimeShard;

public interface RecordReadMapper {
	/**
	 * 根据id查询对象
	 *
	 * @param recordId
	 * @param operDate
	 * @return
	 */
	@Shard(time_shard = { @TimeShard(table = "record", field = "date", formatter = "MM", joiner = '_') })
	RecordEntity selectById(@Param(value = "date") Date operDate, @Param(value = "recordId") Long recordId);

	/**
	 * 通用总纪录数
	 *
	 * @param param
	 * @return
	 */
	@Shard(time_shard = { @TimeShard(table = "record", field = "date", formatter = "MM", joiner = '_') })
	Integer countByParam(@Param(value = "date") Date operDate, @Param(value = "record") RecordEntity param);

	/**
	 * 通用分页
	 *
	 * @param param
	 * @param pageBean
	 * @return
	 */
	@Shard(time_shard = { @TimeShard(table = "record", field = "date", formatter = "MM", joiner = '_') })
	List<RecordEntity> selectByParam(@Param(value = "date") Date operDate, @Param(value = "record") RecordEntity param,
			@Param(value = "pageBean") PageableRequestBean pageBean);
}