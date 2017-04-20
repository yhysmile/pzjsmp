package com.pzj.core.smp.business;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pzj.core.smp.common.exception.ParameterException;
import com.pzj.core.smp.common.exception.SmpException;
import com.pzj.core.smp.common.exception.SmpExceptionCode;
import com.pzj.core.smp.read.BusinReadMapper;
import com.pzj.core.smp.util.ModelConvert;
import com.pzj.core.smp.write.BusinWriteMapper;
import com.pzj.framework.entity.PageableRequestBean;
import com.pzj.framework.entity.QueryResult;
import com.pzj.framework.toolkit.Check;

/**
 * Created by Administrator on 2017-1-11.
 * 业务线核心处理
 * @author ZhouYuan
 */
@Service("businService")
public class BusinService {
	@Resource
	private BusinReadMapper businReadMapper;
	@Resource
	private BusinWriteMapper businWriteMapper;

	/**
	 * 插入单条业务线
	 * @param businModel
	 * @return
	 */
	public Integer insert(BusinModel businModel) {
		BusinEntity businEntity = ModelConvert.convertTBusinEntity(businModel);
		Integer inResult = businWriteMapper.insert(businEntity);
		return inResult;
	}

	/**
	 * 根据业务线id修改业务线的状态
	 * @param businId
	 * @param state
	 * @return
	 */
	public Long updateState(Long businId, Integer state) {
		Integer upResult = businWriteMapper.updateState(businId, state);
		if (upResult != 0) {
			return businId;
		}
		return Long.valueOf(upResult);
	}

	/**
	 * 验证业务线名称名称是否存在，不验证状态
	 * @param name
	 * @return
	 */
	public boolean selectByName(String name) {
		Long businId = businReadMapper.selectByName(name, 1);
		if (businId != null) {
			return true;
		}
		return false;
	}

	/**
	 * 根据业务线名称，验证是否有有效的业务线存在 。启用状态的
	 * @param name
	 * @return
	 */
	public boolean selectValidByName(String name) {
		Long businId = businReadMapper.selectByName(name, BusinStateEnum.ENABLE.getState());
		if (Check.NuNObject(businId)) {
			return true;
		}
		return false;
	}

	/**
	 * 查询所有的有效业务线
	 * @return
	 */
	public List<BusinModel> selectValidBusin() {
		List<BusinEntity> businEntities = businReadMapper.selectValidBusin(BusinStateEnum.ENABLE.getState());
		if (Check.NuNObject(businEntities)) {
			return new ArrayList<BusinModel>();
		}
		List<BusinModel> businModels = ModelConvert.convertTBusinModels(businEntities);
		return businModels;
	}

	/**
	 * 分页查询业务线数据
	 * @param businModel
	 * @return
	 */
	public QueryResult<BusinModel> selectByParam(BusinModel businModel) {
		BusinEntity businEntity = ModelConvert.convertTBusinEntity(businModel);
		int count = businReadMapper.countByParam(businEntity);

		PageableRequestBean page = new PageableRequestBean();
		page.setCurrentPage(businModel.getCurrentPage());
		page.setPageSize(businModel.getPageSize());

		QueryResult<BusinModel> qr = new QueryResult<BusinModel>(businModel.getCurrentPage(), businModel.getPageSize());
		if (count == 0) {
			return qr;
		}
		List<BusinEntity> businEntities = businReadMapper.selectByParam(businEntity, page);

		if (Check.NuNObject(businEntities)) {
			return qr;
		}
		List<BusinModel> businModels = ModelConvert.convertTBusinModels(businEntities);
		qr.setTotal(count);
		qr.setRecords(businModels);
		return qr;
	}

	/**
	 * 根据id查询业务线信息
	 * @param id
	 * @return
	 */
	public BusinModel slectById(Long id) {
		BusinEntity businEntity = businReadMapper.selectById(id);
		BusinModel businModel = ModelConvert.convertTBusinModel(businEntity);
		return businModel;
	}
}
