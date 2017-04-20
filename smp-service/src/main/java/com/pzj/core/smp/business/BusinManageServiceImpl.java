package com.pzj.core.smp.business;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pzj.core.smp.common.exception.SmpException;
import com.pzj.core.smp.common.exception.SmpExceptionCode;
import com.pzj.core.smp.scheduling.SchedulingMessageServiceImpl;
import com.pzj.framework.context.Result;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.entity.QueryResult;
import com.pzj.framework.idgen.IDGenerater;
import com.pzj.framework.toolkit.Check;

/**
 * Created by Administrator on 2017-1-11.
 * 业务线管理接口实现
 * @author ZhouYuan
 */
@Service("businManageService")
public class BusinManageServiceImpl implements IBusinManageService {
	private static final Logger logger = LoggerFactory.getLogger(BusinManageServiceImpl.class);
	@Resource
	private BusinService businService;
	@Resource
	private SchedulingMessageServiceImpl schedulingMessageService;
	@Resource(name = "idGenerater")
	private IDGenerater idGenerater;

	@Override
	public Result<Long> insert(BusinModel businModel) {
		try {
			if (Check.NuNObject(businModel) || Check.NuNStrStrict(businModel.getName())
					|| Check.NuNStrStrict(businModel.getDescribe()) || Check.NuNStrStrict(businModel.getState())) {
				logger.warn("illegal args, param: {}.", JSONConverter.toJson(businModel));
				return new Result<>(SmpExceptionCode.PARAMS_NULL.getCode(), SmpExceptionCode.PARAMS_NULL.getMsg());
			}
			Integer buState = checkBusinState(businModel.getState());
			if (buState == null) {
				logger.warn("illegal args,business state: {}.", businModel.getState());
				return new Result<>(SmpExceptionCode.BUSIN_TYPE_ERROR.getCode(),
						SmpExceptionCode.BUSIN_TYPE_ERROR.getMsg());
			}
			businModel.setState(buState.toString());
			businModel.setCreateDate(new Timestamp(System.currentTimeMillis()));
			Long businId = idGenerater.nextId();
			businModel.setId(businId);
			if (logger.isInfoEnabled()) {
				logger.info("param businModel: {}.", JSONConverter.toJson(businModel));
			}

			Integer inResult = businService.insert(businModel);
			if (logger.isInfoEnabled()) {
				logger.info("add business result,inResult: {}.", inResult);
			}
			if (inResult != 0) {
				return new Result<>(SmpExceptionCode.BUSIN_INSERT_ERROR.getCode(),
						SmpExceptionCode.BUSIN_INSERT_ERROR.getMsg());
			}
			//新增成功，修改队列状态
			schedulingMessageService.startSubscribeMessage(businModel.getName());
			return new Result<>(businId);
		} catch (Exception e) {
			logger.error("add business fail,param businModel: " + JSONConverter.toJson(businModel), e);
			return new Result<>(SmpExceptionCode.BUSIN_INSERT_ERROR.getCode(),
					SmpExceptionCode.BUSIN_INSERT_ERROR.getMsg());
		}
	}

	@Override
	public Result<Boolean> selectByName(String name) {
		try {
			if (Check.NuNObject(name)) {
				logger.warn("illegal args,name: {}.", name);
				return new Result<>(SmpExceptionCode.BUSIN_NAME_NULL.getCode(),
						SmpExceptionCode.BUSIN_NAME_NULL.getMsg());
			}

			if (logger.isInfoEnabled()) {
				logger.info("query business args,name: {}.", name);
			}

			boolean isExisted = businService.selectByName(name);

			if (logger.isInfoEnabled()) {
				logger.info("query business result,isExisted: {}.", isExisted);
			}
			return new Result<>(isExisted);
		} catch (Exception e) {
			logger.error("query business fail,args name: " + name, e);
			return new Result<>(SmpExceptionCode.BUSIN_NAME_ERROR.getCode(), SmpExceptionCode.BUSIN_NAME_ERROR.getMsg());
		}
	}

	@Override
	public Result<Long> updateState(Long id, String state) {
		if (Check.NuNObject(id, state) || id < 1) {
			logger.warn("illegal args, id: {}, state: {}.", id, state);
			return new Result<>(SmpExceptionCode.BUSIN_ID_NULL.getCode(), SmpExceptionCode.BUSIN_ID_NULL.getMsg());
		}

		if (logger.isInfoEnabled()) {
			logger.info("update business state args, id: {}, state: {}.", id, state);
		}

		try {
			BusinModel businModel = businService.slectById(id);
			if (state.equals(businModel.getState())) {
				logger.error("update state error.update state: [" + state + "],busin state: [" + businModel.getState()
						+ "]");
				throw new SmpException(SmpExceptionCode.BUSIN_STATE_ERROR);
			}
			BusinStateEnum businStateEnum = BusinStateEnum.valueOf(state);
			if (Check.NuNObject(businStateEnum)) {
				logger.error("convert state error,state: {}", state);
				throw new SmpException(SmpExceptionCode.BUSIN_TYPE_ERROR);
			}
			Integer businState = businStateEnum.getState();
			Long upResult = businService.updateState(id, businState);
			if (logger.isInfoEnabled()) {
				logger.info("update business state result. upResult: {}", upResult);
			}
			if (upResult == 0) {
				return new Result<>(SmpExceptionCode.BUSIN_STATE_ERROR.getCode(),
						SmpExceptionCode.BUSIN_STATE_ERROR.getMsg());
			}

			//修改状态成功需要修改队列状态
			if (state.equals(BusinStateEnum.getBusinValue(BusinStateEnum.ENABLE))) {
				schedulingMessageService.startSubscribeMessage(businModel.getName());
			} else if (state.equals(BusinStateEnum.getBusinValue(BusinStateEnum.DISABLE))) {
				schedulingMessageService.stopSubscribeMessage(businModel.getName());
			}

			if (logger.isInfoEnabled()) {
				logger.info("manage queue: [" + businModel.getName() + "], action: [" + state + "]");
			}
			return new Result<Long>();
		} catch (Exception e) {
			logger.error("update business state fail, id is " + (id) + ", state is " + state, e);
			return new Result<>(SmpExceptionCode.BUSIN_STATE_ERROR.getCode(),
					SmpExceptionCode.BUSIN_STATE_ERROR.getMsg());
		}
	}

	/**
	 * 分页查询业务线数据
	 * @param businModel
	 * @return
	 */
	@Override
	public Result<QueryResult<BusinModel>> selectByParam(BusinModel businModel) {
		try {
			if (Check.NuNObject(businModel)) {
				return new Result<>(SmpExceptionCode.PARAMS_NULL.getCode(), SmpExceptionCode.PARAMS_NULL.getMsg());
			}
			if(!Check.NuNStrStrict(businModel.getState())){
				Integer buState = checkBusinState(businModel.getState());
				if (buState == null) {
					logger.warn("illegal args,business state: {}.", businModel.getState());
					return new Result<>(SmpExceptionCode.BUSIN_TYPE_ERROR.getCode(),
							SmpExceptionCode.BUSIN_TYPE_ERROR.getMsg());
				}
				businModel.setState(buState.toString());
			}

			if (logger.isInfoEnabled()) {
				logger.info("query page business params,businModel: {}.", JSONConverter.toJson(businModel));
			}
			QueryResult<BusinModel> qrb = businService.selectByParam(businModel);
			if (logger.isInfoEnabled()) {
				logger.info("query page business result,qrb: {}.", JSONConverter.toJson(qrb));
			}

			if (Check.NuNObject(qrb)) {
				return new Result<>(new QueryResult<BusinModel>(businModel.getCurrentPage(), businModel.getPageSize()));
			}
			return new Result<>(qrb);
		} catch (Exception e) {
			logger.error("query page business fail, params businModel: " + JSONConverter.toJson(businModel), e);
			return new Result<>(SmpExceptionCode.BUSIN_QUERY_ERROR.getCode(),
					SmpExceptionCode.BUSIN_QUERY_ERROR.getMsg());
		}
	}

	/**
	 * 转换业务线值
	 * @param businState
	 * @return
	 */
	private Integer checkBusinState(String businState) {
		if (Check.NuNObject(businState)) {
			return null;
		}
		BusinStateEnum businStateEnum = BusinStateEnum.valueOf(businState);
		if (Check.NuNObject(businStateEnum)) {
			return null;
		}
		Integer buState = businStateEnum.getState();
		return buState;
	}
}
