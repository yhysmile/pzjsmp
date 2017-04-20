package com.pzj.core.smp.channel.model;

public class ChannelExceptionInfo implements java.io.Serializable {

	private static final long serialVersionUID = -8123328666593045880L;
	/** 通道异常重试标识 true:需要重试 false:不需要重试*/
	private Boolean excepFlag;
	/** 通道重试次数，默认值*/
	private Integer retryNum;

	public static final Integer defaultRetryNum = 1;
	public static final Integer notAvaiRetryNum = -1;

	public ChannelExceptionInfo() {
	}

	public ChannelExceptionInfo(Boolean excepFlag, Integer retryNum) {
		this.excepFlag = excepFlag;
		this.retryNum = retryNum;
	}

	public Boolean getExcepFlag() {
		return excepFlag;
	}

	public void setExcepFlag(Boolean excepFlag) {
		this.excepFlag = excepFlag;
	}

	public Integer getRetryNum() {
		return retryNum;
	}

	public void setRetryNum(Integer retryNum) {
		this.retryNum = retryNum;
	}

}
