package com.pzj.core.smp.channel.model;

public class MasSdkSendParam implements java.io.Serializable {

	private static final long serialVersionUID = 8214125700611764823L;
	/**
	 * 手机号码
	 */
	private String[] mobiles;
	/**
	 * 短信内容
	 */
	private String smsContent;
	/**
	 * 扩展码
	 */
	private String addSerial;
	/**
	 * 优先级
	 */
	private int smsPriority;
	/**
	 * 网关签名编码
	 */
	private String sign;
	/**
	 * 发送数据批次号
	 */
	private String msgGroup;
	/**
	 * 是否需要上行
	 */
	private boolean isMo;

	public static final int defautlPriority = 1;
	public static final String defautlSign = "DWItALe3A";

	public MasSdkSendParam() {
		this.smsPriority = defautlPriority;
		this.sign = defautlSign;
	}

	public MasSdkSendParam(String[] mobiles, String smsContent, String msgGroup) {
		this.mobiles = mobiles;
		this.smsContent = smsContent;
		this.msgGroup = msgGroup;
		this.sign = defautlSign;
		this.smsPriority = defautlPriority;
	}

	public String[] getMobiles() {
		return mobiles;
	}

	public void setMobiles(String[] mobiles) {
		this.mobiles = mobiles;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public String getAddSerial() {
		return addSerial;
	}

	public void setAddSerial(String addSerial) {
		this.addSerial = addSerial;
	}

	public int getSmsPriority() {
		return smsPriority;
	}

	public void setSmsPriority(int smsPriority) {
		this.smsPriority = smsPriority;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getMsgGroup() {
		return msgGroup;
	}

	public void setMsgGroup(String msgGroup) {
		this.msgGroup = msgGroup;
	}

	public boolean isMo() {
		return isMo;
	}

	public void setMo(boolean isMo) {
		this.isMo = isMo;
	}

}
