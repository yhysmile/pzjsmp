package com.pzj.core.smp.business;

import java.sql.Timestamp;

import com.pzj.core.smp.base.BaseEntity;

/**
 * Created by Administrator on 2017-1-11.
 * 业务线数据库模型
 * @author ZhouYuan
 */
public class BusinEntity extends BaseEntity {
	/**  */
	private static final long serialVersionUID = 7882517372929790497L;
	//主键id
	private Long businId;
	//业务线名称
	private String businName;
	//业务线描述
	private String businDescribe;
	//业务线状态 1、正常 0、禁用
	private Integer state;
	//创建时间
	private Timestamp createTime;
	//修改时间
	private Timestamp updateTime;

	public BusinEntity() {
		super();
	}

	public BusinEntity(String businName, String businDescribe, Integer state, Timestamp createTime, Timestamp updateTime) {
		this.businName = businName;
		this.businDescribe = businDescribe;
		this.state = state;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public Long getBusinId() {
		return businId;
	}

	public void setBusinId(Long businId) {
		this.businId = businId;
	}

	public String getBusinName() {
		return businName;
	}

	public void setBusinName(String businName) {
		this.businName = businName;
	}

	public String getBusinDescribe() {
		return businDescribe;
	}

	public void setBusinDescribe(String businDescribe) {
		this.businDescribe = businDescribe;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
}
