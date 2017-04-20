package com.pzj.core.smp.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pzj.core.smp.business.BusinEntity;
import com.pzj.core.smp.business.BusinModel;
import com.pzj.core.smp.business.BusinStateEnum;
import com.pzj.core.smp.common.exception.ParameterException;
import com.pzj.core.smp.errorRecord.ErrorRecordEntity;
import com.pzj.core.smp.errorRecord.ErrorRecordModel;
import com.pzj.core.smp.record.RecordEntity;
import com.pzj.core.smp.record.RecordModel;
import com.pzj.core.smp.record.RecordStateEnum;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.exception.ServiceException;
import com.pzj.framework.toolkit.Check;

/**
 * Created by Administrator on 2017-1-4.
 * 模型转换类
 */
public class ModelConvert {
	private static final Logger logger = LoggerFactory.getLogger(ModelConvert.class);

	/**
	 * 短信记录，转换为Entity模型
	 * @param recordModel
	 * @return
	 */
	public static RecordEntity convertTRecordEntity(RecordModel recordModel) {
		try {
			if (Check.NuNObject(recordModel)) {
				return new RecordEntity();
			}
			RecordEntity recordEntity = new RecordEntity(recordModel.getSendId(), recordModel.getSendPhone(),
					recordModel.getSendState() == null ? null : Integer.valueOf(recordModel.getSendState()),
					recordModel.getChannelId(), recordModel.getChannelName(), recordModel.getSendContent(),
					recordModel.getSendNum(), recordModel.getSendLinkId());
			if (recordModel.getUpdateTime() != null) {
				recordEntity.setUpdateTime(recordModel.getUpdateTime());
			}

			recordEntity.setCreateTime(recordModel.getSendTime());
			return recordEntity;
		} catch (Throwable t) {
			logger.error("params recordEntity is {}", JSONConverter.toJson(recordModel));
			throw t instanceof ServiceException ? (ServiceException) t : new ParameterException(t);
		}
	}

	/**
	 * 短信记录，转换为Entity模型集合
	 * @param recordModels
	 * @return
	 */
	public static ArrayList<RecordEntity> convertTRecordEntitys(List<RecordModel> recordModels) {
		try {
			ArrayList<RecordEntity> recordEns = new ArrayList<>();
			for (RecordModel rm : recordModels) {
				recordEns.add(convertTRecordEntity(rm));
			}
			return recordEns;
		} catch (Throwable t) {
			logger.error("params recordModels is {}", JSONConverter.toJson(recordModels));
			throw t instanceof ServiceException ? (ServiceException) t : new ParameterException(t);
		}
	}

	/**
	 * 用户分页查询较少参数使用
	 * @param recordModel
	 * @return
	 */
	public static RecordEntity convertTPageRecordEntity(RecordModel recordModel) {
		try {
			RecordEntity recordEntity = new RecordEntity();
			recordEntity.setPhoneNumber(recordModel.getSendPhone());
			recordEntity.setState(Integer.valueOf(recordModel.getSendState()));
			recordEntity.setCreateTime(recordModel.getSendTime());
			if (Check.NuNObject(recordEntity)) {
				return new RecordEntity();
			}
			return recordEntity;
		} catch (Throwable t) {
			logger.error("params recordModel is {}", JSONConverter.toJson(recordModel));
			throw t instanceof ServiceException ? (ServiceException) t : new ParameterException(t);
		}
	}

	/**
	 * 短信记录，转换为Model模型
	 * @param recordEntity
	 * @return
	 */
	public static RecordModel convertTRecordModel(RecordEntity recordEntity) {
		try {
			if (Check.NuNObject(recordEntity)) {
				return new RecordModel();
			}
			RecordModel recordModel = new RecordModel(recordEntity.getPhoneNumber(),
					RecordStateEnum.getRecordValue(RecordStateEnum.getRecordState(recordEntity.getState())),
					recordEntity.getChannelName(), recordEntity.getChannelId(), recordEntity.getCreateTime(),
					recordEntity.getContent(), recordEntity.getSendNum(), recordEntity.getUpdateTime(),
					recordEntity.getLinkId());
			recordModel.setSendId(recordEntity.getRecordId());
			return recordModel;
		} catch (Throwable t) {
			logger.error("params recordEntity is {}", JSONConverter.toJson(recordEntity));
			throw t instanceof ServiceException ? (ServiceException) t : new ParameterException(t);
		}

	}

	/**
	 * 短信记录，转换为Model模型集合
	 * @param recordEntities
	 * @return
	 */
	public static List<RecordModel> convertsTRecordModels(List<RecordEntity> recordEntities) {
		try {
			List<RecordModel> recordModels = new ArrayList<>();
			for (RecordEntity re : recordEntities) {
				recordModels.add(convertTRecordModel(re));
			}
			return recordModels;
		} catch (Throwable t) {
			logger.error("params recordEntities is {}", JSONConverter.toJson(recordEntities));
			throw t instanceof ServiceException ? (ServiceException) t : new ParameterException(t);
		}
	}

	/**
	 * 短信错误记录，转换为Entity模型
	 * @param errorRecordModel
	 * @return
	 */
	public static ErrorRecordEntity convertTErrRecordEntity(ErrorRecordModel errorRecordModel) {
		try {

			if (Check.NuNObject(errorRecordModel)) {
				return new ErrorRecordEntity();
			}
			ErrorRecordEntity ere = new ErrorRecordEntity(errorRecordModel.getSendRecordId(),
					errorRecordModel.getSendErrDetail(), new Timestamp(System.currentTimeMillis()));
			return ere;
		} catch (Throwable t) {
			logger.error("params errorRecordModel is {}", JSONConverter.toJson(errorRecordModel));
			throw t instanceof ServiceException ? (ServiceException) t : new ParameterException(t);
		}
	}

	/**
	 * 短信错误记录，转换为Model模型
	 * @param errorRecordEntity
	 * @return
	 */
	public static ErrorRecordModel convertTErrRecordModel(ErrorRecordEntity errorRecordEntity) {
		try {

			if (Check.NuNObject(errorRecordEntity)) {
				return new ErrorRecordModel();
			}
			ErrorRecordModel errorRecordModel = new ErrorRecordModel(errorRecordEntity.getRecordId(),
					errorRecordEntity.getRecordNum(), errorRecordEntity.getErrDetail(),
					errorRecordEntity.getCreateTime());
			return errorRecordModel;
		} catch (Throwable t) {
			logger.error("params errorRecordEntity is {}", JSONConverter.toJson(errorRecordEntity));
			throw t instanceof ServiceException ? (ServiceException) t : new ParameterException(t);
		}
	}

	/**
	 * 业务线，转换为Entity模型
	 * @param businModel
	 * @return
	 */
	public static BusinEntity convertTBusinEntity(BusinModel businModel) {
		try {
			if (Check.NuNObject(businModel)) {
				return new BusinEntity();
			}
			Integer businState = null;
			if (!Check.NuNStrStrict(businModel.getState())) {
				businState = Integer.valueOf(businModel.getState());
			}

			BusinEntity businEntity = new BusinEntity(businModel.getName(), businModel.getDescribe(), businState,
					businModel.getCreateDate(), businModel.getEditDate());
			return businEntity;
		} catch (Throwable t) {
			logger.error("params businModel is {}", JSONConverter.toJson(businModel));
			throw t instanceof ServiceException ? (ServiceException) t : new ParameterException(t);
		}
	}

	/**
	 * 业务线，转换为Model模型
	 * @param businEntity
	 * @return
	 */
	public static BusinModel convertTBusinModel(BusinEntity businEntity) {
		try {
			if (Check.NuNObject(businEntity)) {
				return new BusinModel();
			}
			BusinStateEnum businStateEnum = BusinStateEnum.getBusinState(businEntity.getState());
			BusinModel businModel = new BusinModel(businEntity.getBusinId(), businEntity.getBusinName(),
					businEntity.getBusinDescribe(), BusinStateEnum.getBusinValue(businStateEnum), businEntity.getCreateTime(),
					businEntity.getUpdateTime());
			return businModel;
		} catch (Throwable t) {
			logger.error("params businEntity is {}", JSONConverter.toJson(businEntity));
			throw t instanceof ServiceException ? (ServiceException) t : new ParameterException(t);
		}
	}

	/**
	 * 业务线，转换为Model模型集合
	 * @param businEntities
	 * @return
	 */
	public static List<BusinModel> convertTBusinModels(List<BusinEntity> businEntities) {
		try {
			List<BusinModel> businModels = new ArrayList<>();
			for (BusinEntity be : businEntities) {
				businModels.add(convertTBusinModel(be));
			}
			return businModels;
		} catch (Throwable t) {
			logger.error("params businEntities is {}", JSONConverter.toJson(businEntities));
			throw t instanceof ServiceException ? (ServiceException) t : new ParameterException(t);
		}
	}
}
