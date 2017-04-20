package com.pzj.core.smp.base;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pzj.framework.entity.PageableRequestBean;

public interface BaseMapper<T extends BaseEntity> {

	/**
	 * 插入
	 * 
	 * @param record
	 * @return
	 */
	Integer insert(T record);

	/**
	 * 根据id查询对象
	 * 
	 * @param id
	 * @return
	 */
	T selectById(Long id);

	/**
	 * 按条件更新
	 * 
	 * @param record
	 * @return
	 */
	Integer update(T record);

	/**
	 * 通用总纪录数
	 * 
	 * @param param
	 * @return
	 */
	Integer countByParam(@Param(value = "param") BaseEntity param);

	/**
	 * 通用分页
	 * 
	 * @param param
	 * @param pageBean
	 * @return
	 */
	List<T> selectByParam(@Param(value = "param") BaseEntity param,
			@Param(value = "pageBean") PageableRequestBean pageBean);

	/**
	 * 批量插入
	 * 
	 * @param entitys
	 * @return
	 */
	Integer insertBatch(List<T> entitys);

	/**
	 * 批量更新
	 * 
	 * @param entitys
	 * @return
	 */
	Integer updateBatch(List<T> entitys);

}
