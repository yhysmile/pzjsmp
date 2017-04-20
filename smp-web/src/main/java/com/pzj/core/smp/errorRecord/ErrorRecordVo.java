package com.pzj.core.smp.errorRecord;

import java.io.Serializable;
import java.sql.Timestamp;

import com.pzj.framework.entity.PageableRequestBean;

/**
 * Created by Administrator on 2017-1-9.
 * 短信记录错误记录
 */
public class ErrorRecordVo extends PageableRequestBean implements Serializable {
	/**  */
	private static final long serialVersionUID = 5706175676357684411L;
	//失败详情
	private String sendErrDetail;
	//创建时间
	private Timestamp sendTime;
	//短信记录id
	private Long sendRecordId;
	//短信错误记录月份
	private Integer sendNum;

	public Integer getSendNum() {
		return sendNum;
	}

	public void setSendNum(Integer sendNum) {
		this.sendNum = sendNum;
	}

	public Long getSendRecordId() {
		return sendRecordId;
	}

	public void setSendRecordId(Long sendRecordId) {
		this.sendRecordId = sendRecordId;
	}

	public String getSendErrDetail() {
		return sendErrDetail;
	}

	public void setSendErrDetail(String sendErrDetail) {
		this.sendErrDetail = sendErrDetail;
	}

	public Timestamp getSendTime() {
		return sendTime;
	}

	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}
}
