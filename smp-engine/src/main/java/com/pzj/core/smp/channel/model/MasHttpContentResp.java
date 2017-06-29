package com.pzj.core.smp.channel.model;

public class MasHttpContentResp implements java.io.Serializable {

	private static final long serialVersionUID = 3141285412130917706L;
	/** msg组信息*/
	private String msgGroup;
	/** 返回响应内容*/
	private String rspcod;
	/** 是否成功标识*/
	private String success;

	public String getMsgGroup() {
		return msgGroup;
	}

	public void setMsgGroup(String msgGroup) {
		this.msgGroup = msgGroup;
	}

	public String getRspcod() {
		return rspcod;
	}

	public void setRspcod(String rspcod) {
		this.rspcod = rspcod;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

}
