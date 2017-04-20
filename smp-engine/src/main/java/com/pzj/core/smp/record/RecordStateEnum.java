package com.pzj.core.smp.record;

/**
 * Created by Administrator on 2017-1-4.
 * 短信记录发送状态
 * @author 	ZhouYuan
 */
public enum RecordStateEnum {
	SEND_SUCCESS(1, "发送成功"), SEND_ERROR(0, "发送失败");

	private int state;
	private String value;

	RecordStateEnum(int state, String value) {
		this.state = state;
		this.value = value;
	}

	public int getState() {
		return state;
	}

	public String getValue() {
		return value;
	}

	public static RecordStateEnum getRecordState(int state) {
		switch (state) {
		case 1:
			return SEND_SUCCESS;
		case 0:
			return SEND_ERROR;
		default:
			return SEND_SUCCESS;
		}
	}

	public static String getRecordValue(RecordStateEnum recordStateEnum) {
		switch (recordStateEnum.getState()) {
		case 1:
			return "SEND_SUCCESS";
		case 0:
			return "SEND_ERROR";
		default:
			return "SEND_SUCCESS";
		}
	}
}
