package com.pzj.core.smp.messaging;

/**
 * Created by Administrator on 2017-1-7.
 */
public class MessagingConfig {
	private String consumerGroup = null;

	private String topic = null;

	private String namesrvAddr = null;

	public String getConsumerGroup() {
		return consumerGroup;
	}

	public void setConsumerGroup(String consumerGroup) {
		this.consumerGroup = consumerGroup;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getNamesrvAddr() {
		return namesrvAddr;
	}

	public void setNamesrvAddr(String namesrvAddr) {
		this.namesrvAddr = namesrvAddr;
	}
}
