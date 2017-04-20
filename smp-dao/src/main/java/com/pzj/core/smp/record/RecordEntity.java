package com.pzj.core.smp.record;

import java.sql.Timestamp;

import com.pzj.core.smp.base.BaseEntity;

/**
 * Created by Administrator on 2017-1-4.
 * 短信发送记录，数据库模型
 * @author ZhouYuan
 */
public class RecordEntity extends BaseEntity {
	/**  */
	private static final long serialVersionUID = -1745315416569476437L;
	//短信记录id
	private Long recordId;
	//短信手机号
	private String phoneNumber;
	//短信发送状态；1:发送成功;2:发送失败;
	private Integer state;
	//渠道id
	private Long channelId;
	//通道名称
	private String channelName;
	//创建时间
	private Timestamp createTime;
	//短信内容
	private String content;
	//短信发送次数
	private Integer sendNum;
	//修改时间
	private Timestamp updateTime;
	//成功返回id
	private String linkId;

	public RecordEntity() {
		super();
	}

	public RecordEntity(Long recordId, String phoneNumber, Integer state, Long channelId, String channelName,
			String content, Integer sendNum, String linkId) {
		this.recordId = recordId;
		this.phoneNumber = phoneNumber;
		this.state = state;
		this.channelId = channelId;
		this.channelName = channelName;
		this.content = content;
		this.sendNum = sendNum;
		this.linkId = linkId;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
