package com.pzj.core.smp.delivery;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016-12-29.
 */
public class MessageHead implements Serializable {
	// 业务名
	private String businessName;
	// 优先级
	private String priority;
	// 模版，需要考虑下是否真的需要
	private String template;
	// 创建时间
	private Date createDate;
	// 超时时间，毫秒
	private Long timeOut;

	public MessageHead() {
		setCreateDate(new Date());
	}

	public MessageHead(String businessName, String priority, Long timeOut) {
		this();
		setBusinessName(businessName);
		setPriority(priority);
		setTimeOut(timeOut);
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		/*if (businessName == null || businessName.isEmpty())
		    throw new SmpException("bussinessName 不能为空。");*/
		this.businessName = businessName;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		/*if (priority == null)
		    throw new SmpException("priority 不能为空。");*/

		/*if (!("A".equals(priority) || "B".equals(priority) || "C".equals(priority)))
		    throw new SmpException("priority 不合法，只能是 \"A\" 、 \"B\" 、 \"C\" 其中的一种。");*/

		this.priority = priority;
	}

	public Long getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(Long timeOut) {
		/*if (timeOut == null)
		    throw new SmpException("timeOut 不能为空。");*/

		/*if (timeOut <= 0)
		    throw new SmpException("timeOut 必须大于0。");*/

		this.timeOut = timeOut;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		/* if (createDate == null)
		    throw new SmpException("createDate 不能为空。");*/
		this.createDate = createDate;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	@Override
	public String toString() {
		return "MessageHead [businessName=" + businessName + ", priority=" + priority + ", template=" + template
				+ ", createDate=" + createDate + ", timeOut=" + timeOut + "]";
	}

}
