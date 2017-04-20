package com.pzj.core.smp.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pzj.core.smp.business.BusinEntity;
import com.pzj.framework.entity.PageableRequestBean;

/**
 * Created by Administrator on 2017-1-11.
 */
public interface BusinReadMapper {

	/**
	 * 根据id查询对象
	 *
	 * @param id
	 * @return
	 */
	BusinEntity selectById(@Param(value = "businId") Long id);

	/**
	 * 根据名称和状态查询业务线
	 * @param name
	 * @param state
	 * @return
	 */
	Long selectByName(@Param(value = "name") String name, @Param(value = "state") Integer state);

	/**
	 * 查询有效的业务线
	 * @param state
	 * @return
	 */
	List<BusinEntity> selectValidBusin(@Param(value = "state") Integer state);

	/**
	 * 通用总纪录数
	 *
	 * @param businEntity
	 * @return
	 */
	Integer countByParam(@Param(value = "busin") BusinEntity businEntity);

	/**
	 * 通用分页
	 *
	 * @param businEntity
	 * @param pageBean
	 * @return
	 */
	List<BusinEntity> selectByParam(@Param(value = "busin") BusinEntity businEntity,
			@Param(value = "pageBean") PageableRequestBean pageBean);
}
