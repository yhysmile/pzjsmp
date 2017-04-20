package com.pzj.core.smp.business;

/**
 * Created by Administrator on 2017-1-11.
 * 业务线状态
 * @author ZhouYuan
 */
public enum BusinStateEnum {
	ENABLE(1, "启用"), DISABLE(0, "禁用");
	private int state;
	private String value;

	BusinStateEnum(int state, String value) {
		this.state = state;
		this.value = value;
	}

	public int getState() {
		return state;
	}

	public String getValue() {
		return value;
	}

	public static String getBusinValue(BusinStateEnum businStateEnum) {
		switch (businStateEnum.getState()) {
		case 1:
			return "ENABLE";
		case 0:
			return "DISABLE";
		default:
			return "ENABLE";
		}
	}

	public static BusinStateEnum getBusinState(int state) {
		switch (state) {
		case 1:
			return ENABLE;
		case 0:
			return DISABLE;
		default:
			return ENABLE;
		}
	}

}
