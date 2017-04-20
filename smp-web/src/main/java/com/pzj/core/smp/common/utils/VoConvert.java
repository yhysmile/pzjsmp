package com.pzj.core.smp.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.pzj.core.smp.business.BusinModel;
import com.pzj.core.smp.business.BusinVo;
import com.pzj.core.smp.common.exception.ParameterException;
import com.pzj.core.smp.errorRecord.ErrorRecordModel;
import com.pzj.core.smp.errorRecord.ErrorRecordVo;
import com.pzj.core.smp.record.RecordModel;
import com.pzj.core.smp.record.RecordVo;
import com.pzj.framework.converter.JSONConverter;
import com.pzj.framework.exception.ServiceException;
import com.pzj.framework.toolkit.Check;

/**
 * Created by Administrator on 2017-1-11.
 * 业务端 VO与Model之间的转换
 */
public class VoConvert {
	private static final Logger logger = LoggerFactory.getLogger(VoConvert.class);

	/**
	 * 短信记录VO转Model
	 * @param recordVO
	 * @return
	 */
	public static RecordModel convertTRecordModel(RecordVo recordVO) {
		try {
			if (Check.NuNObject(recordVO)) {
				return new RecordModel();
			}
			RecordModel rdm = new RecordModel();
			BeanUtils.copyProperties(recordVO, rdm);
			return rdm;
		} catch (Throwable t) {
			logger.error("params RecordVo is {}", JSONConverter.toJson(recordVO));
			throw t instanceof ServiceException ? (ServiceException) t : new ParameterException(t);
		}
	}

	/**
	 * 短信记录Model转VO 集合转换
	 * @param recordModels
	 * @return
	 */
	public static List<RecordVo> convertTRecordVOs(List<RecordModel> recordModels) {
		try {
			if (Check.NuNObject(recordModels)) {
				return new ArrayList<RecordVo>();
			}
			List<RecordVo> rVOs = new ArrayList<>();
			for (RecordModel rd : recordModels) {
				rVOs.add(convertTRecordVO(rd));
			}
			return rVOs;
		} catch (Throwable t) {
			logger.error("params recordModels is {}", JSONConverter.toJson(recordModels));
			throw t instanceof ServiceException ? (ServiceException) t : new ParameterException(t);
		}
	}

	/**
	 * 短信记录Model转VO
	 * @param recordModel
	 * @return
	 */
	public static RecordVo convertTRecordVO(RecordModel recordModel) {
		try {
			if (Check.NuNObject(recordModel)) {
				return new RecordVo();
			}
			RecordVo recordVO = new RecordVo();
			BeanUtils.copyProperties(recordModel, recordVO);
			return recordVO;
		} catch (Throwable t) {
			logger.error("params recordModel is {}", JSONConverter.toJson(recordModel));
			throw t instanceof ServiceException ? (ServiceException) t : new ParameterException(t);
		}
	}

	/**
	 * 短信错误详情 Model转VO
	 * @param errRecordModel
	 * @return
	 */
	public static ErrorRecordVo convertTErrRecordVO(ErrorRecordModel errRecordModel) {
		try {
			if (Check.NuNObject(errRecordModel)) {
				return new ErrorRecordVo();
			}
			ErrorRecordVo erVO = new ErrorRecordVo();
			BeanUtils.copyProperties(errRecordModel, erVO);
			return erVO;
		} catch (Throwable t) {
			logger.error("params errRecordModel is {}", JSONConverter.toJson(errRecordModel));
			throw t instanceof ServiceException ? (ServiceException) t : new ParameterException(t);
		}
	}

	/**
	 * 业务线 VO转Model
	 * @param businVo
	 * @return
	 */
	public static BusinModel convertTBusinModel(BusinVo businVo) {
		try {
			if (Check.NuNObject(businVo)) {
				return new BusinModel();
			}
			BusinModel businModel = new BusinModel();
			BeanUtils.copyProperties(businVo, businModel);
			return businModel;
		} catch (Throwable t) {
			logger.error("params businVo is {}", JSONConverter.toJson(businVo));
			throw t instanceof ServiceException ? (ServiceException) t : new ParameterException(t);
		}
	}

	/**
	 * 业务线 MOdel转Vo
	 * @param businModel
	 * @return
	 */
	public static BusinVo convertTBusinVo(BusinModel businModel) {
		try {
			if (Check.NuNObject(businModel)) {
				return new BusinVo();
			}
			BusinVo businVo = new BusinVo();
			BeanUtils.copyProperties(businModel, businVo);
			return businVo;
		} catch (Throwable t) {
			logger.error("params businModel is {}", JSONConverter.toJson(businModel));
			throw t instanceof ServiceException ? (ServiceException) t : new ParameterException(t);
		}
	}

	/**
	 * 业务线Model转VO 集合转换
	 * @param businModels
	 * @return
	 */
	public static List<BusinVo> convertTBusinVOs(List<BusinModel> businModels) {
		try {
			if (Check.NuNObject(businModels)) {
				return new ArrayList<BusinVo>();
			}
			List<BusinVo> bVOs = new ArrayList<>();
			for (BusinModel bm : businModels) {
				bVOs.add(convertTBusinVo(bm));
			}
			return bVOs;
		} catch (Throwable t) {
			logger.error("params businModels is {}", JSONConverter.toJson(businModels));
			throw t instanceof ServiceException ? (ServiceException) t : new ParameterException(t);
		}
	}
}
