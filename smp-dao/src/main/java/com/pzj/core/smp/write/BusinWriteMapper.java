package com.pzj.core.smp.write;

import org.apache.ibatis.annotations.Param;

import com.pzj.core.smp.business.BusinEntity;

/**
 * Created by Administrator on 2017-1-11.
 */
public interface BusinWriteMapper {
	Integer updateState(@Param(value = "businId") Long businId, @Param(value = "state") Integer state);

	/**
	 * 插入
	 *
	 * @param busin
	 * @return
	 */
	Integer insert(@Param(value = "busin") BusinEntity busin);
}
