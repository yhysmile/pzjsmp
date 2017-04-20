package com.pzj.core.smp.errorRecord;

import java.sql.Timestamp;

import com.pzj.core.smp.base.BaseEntity;

/**
 * Created by Administrator on 2017-1-9.
 * 短信记录错误原因
 */
public class ErrorRecordEntity extends BaseEntity {
	/**  */
	private static final long serialVersionUID = -9175158078063683961L;
	//主键id
	private Long errorId;
	//记录id对应的月份
	private Integer recordNum;
	//发送记录id
	private Long recordId;
	//失败详情
	private String errDetail;
	//创建时间
	private Timestamp createTime;

	public ErrorRecordEntity() {
		super();
	}

	public ErrorRecordEntity(Long recordId, String errDetail, Timestamp createTime) {
		this.recordId = recordId;
		this.errDetail = errDetail;
		this.createTime = createTime;
	}

	public Long getErrorId() {
		return errorId;
	}

	public void setErrorId(Long errorId) {
		this.errorId = errorId;
	}

	public Integer getRecordNum() {
		return recordNum;
	}

	public void setRecordNum(Integer recordNum) {
		this.recordNum = recordNum;
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public String getErrDetail() {
		return errDetail;
	}

	public void setErrDetail(String errDetail) {
		this.errDetail = errDetail;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
}
