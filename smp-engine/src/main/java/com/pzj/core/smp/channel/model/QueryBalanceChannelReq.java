/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.smp.channel.model;

/**
 * 
 * @author Administrator
 * @version $Id: QueryBalanceChannelReqModel.java, v 0.1 2016年11月30日 下午2:21:17 Administrator Exp $
 */
public class QueryBalanceChannelReq extends SendSmsChannelReq implements java.io.Serializable {

	private static final long serialVersionUID = -7124259906567979199L;
	/** 查询余额请求数据格式*/
	private String format;

	/**
	 * Getter method for property <tt>format</tt>.
	 * 
	 * @return property value of format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * Setter method for property <tt>format</tt>.
	 * 
	 * @param format value to be assigned to property format
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/** 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QueryBalanceChannelReqModel [format=" + format + "]";
	}

}
