/**
 * piaozhijia.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.pzj.core.smp.channel.model;

/**
 * 
 * @author Administrator
 * @version $Id: QueryBalanceChannelRespModel.java, v 0.1 2016年11月30日 下午2:16:40 Administrator Exp $
 */
public class QueryBalanceChannelResp extends SendSmsChannelResp implements java.io.Serializable {

	private static final long serialVersionUID = -1322794937094719599L;
	/** 查询余额返回数据格式*/
	private String format;
	/** 返回通道剩余余额*/
	private String balance;

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

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "QueryBalanceChannelRespModel [format=" + format + ", balance=" + balance + "]";
	}

}
