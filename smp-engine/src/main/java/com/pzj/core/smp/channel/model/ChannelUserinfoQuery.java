package com.pzj.core.smp.channel.model;

public class ChannelUserinfoQuery implements java.io.Serializable {

	private static final long serialVersionUID = -524578948943001594L;
	/** 渠道标识*/
	private Integer indentity;

	public Integer getIndentity() {
		return indentity;
	}

	public void setIndentity(Integer indentity) {
		this.indentity = indentity;
	}

	@Override
	public String toString() {
		return "ChannelUserinfoQueryModel [indentity=" + indentity + "]";
	}

}
