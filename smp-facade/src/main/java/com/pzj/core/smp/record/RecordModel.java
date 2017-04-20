package com.pzj.core.smp.record;

import java.io.Serializable;
import java.sql.Timestamp;

import com.pzj.framework.entity.PageableRequestBean;

/**
 * Created by Administrator on 2017-1-3.
 * 短信记录业务模型
 * @author  ZhouYuan
 */
public class RecordModel extends PageableRequestBean implements Serializable {
	/**  */
	private static final long serialVersionUID = -23944438720577586L;
	//短信记录id
	private Long sendId;
	//短信手机号
	private String sendPhone;
	//短信发送状态；发送成功、SEND_SUCCESS   发送失败、SEND_ERROR
	private String sendState;
	//通道名称
	private String channelName;
	//通道id
	private Long channelId;
	//创建时间
	private Timestamp sendTime;
	//短信内容
	private String sendContent;
	//短信发送次数
	private Integer sendNum;
	//修改时间
	private Timestamp updateTime;
	//成功返回id
	private String sendLinkId;

	public String getSendLinkId() {
		return sendLinkId;
	}

	public void setSendLinkId(String sendLinkId) {
		this.sendLinkId = sendLinkId;
	}

	public RecordModel() {
		super();
	}

	public RecordModel(String sendPhone, String sendState, String channelName, Long channerlId, Timestamp sendTime,
			String sendContent, Integer sendNum, Timestamp updateTime, String sendLinkId) {
		this.sendPhone = sendPhone;
		this.sendState = sendState;
		this.channelName = channelName;
		this.channelId = channerlId;
		this.sendTime = sendTime;
		this.sendContent = sendContent;
		this.sendNum = sendNum;
		this.updateTime = updateTime;
		this.sendLinkId = sendLinkId;

	}

	public Long getSendId() {
		return sendId;
	}

	public void setSendId(Long sendId) {
		this.sendId = sendId;
	}

	public String getSendPhone() {
		return sendPhone;
	}

	public void setSendPhone(String sendPhone) {
		this.sendPhone = sendPhone;
	}

	public String getSendState() {
		return sendState;
	}

	public void setSendState(String sendState) {
		this.sendState = sendState;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public Timestamp getSendTime() {
		return sendTime;
	}

	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}

	public String getSendContent() {
		return sendContent;
	}

	public void setSendContent(String sendContent) {
		this.sendContent = sendContent;
	}

	public Integer getSendNum() {
		return sendNum;
	}

	public void setSendNum(Integer sendNum) {
		this.sendNum = sendNum;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
}
