package com.pzj.core.smp.business;

import java.io.Serializable;
import java.sql.Timestamp;

import com.pzj.framework.entity.PageableRequestBean;

/**
 * Created by Administrator on 2017-1-11.
 * 业务队列Model
 */
public class BusinVo extends PageableRequestBean implements Serializable {
	/**  */
	private static final long serialVersionUID = -5904606909255056479L;
	//主键id
	private Long id;
	//业务线描述
	private String describe;
	//业务线名称
	private String name;
	//业务线状态 启用、ENABLE 禁用、DISABLE
	private String state;
	//创建时间
	private Timestamp createDate;
	//修改时间
	private Timestamp editDate;

	public BusinVo() {
		super();
	}

	public BusinVo(Long id, String name, String describe, String state, Timestamp createDate, Timestamp editDate) {
		this.id = id;
		this.name = name;
		this.describe = describe;
		this.state = state;
		this.createDate = createDate;
		this.editDate = editDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getEditDate() {
		return editDate;
	}

	public void setEditDate(Timestamp editDate) {
		this.editDate = editDate;
	}
}
