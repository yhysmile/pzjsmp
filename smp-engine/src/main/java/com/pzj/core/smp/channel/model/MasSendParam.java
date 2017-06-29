package com.pzj.core.smp.channel.model;

import com.pzj.core.smp.channel.common.EncryptUtils;

public class MasSendParam implements java.io.Serializable {

	private static final long serialVersionUID = 8214125700611764823L;
	//集团客户名称
	private String ecName;
	//用户名
	private String apId;
	//密码
	private String secretKey;
	//手机号码逗号分隔
	private String mobiles;
	//发送短信内容
	private String content;
	//网关签名编码
	private String sign;
	//扩展码，根据向移动公司申请的通道填写
	private String addSerial;
	//API输入参数签名结果，签名算法：将ecName，apId，secretKey， mobiles，content，sign，addSerial按照顺序拼接，然后通过md5(32位小写)计算后得出的值
	private String mac;
	//参数加密
	private String base64EncoderParam;

	public static final String defautlEcName = "测试用户MJ";
	public static final String defautlSign = "DWItALe3A";

	public MasSendParam(String apId, String secretKey, String mobiles, String content) {
		this.apId = apId;
		this.secretKey = secretKey;
		this.mobiles = mobiles;
		this.content = content;
		this.sign = defautlSign;
		this.ecName = defautlEcName;
	}

	//设置mac签名
	public void setMasMacInfo() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(this.getEcName());
		stringBuffer.append(this.getApId());
		stringBuffer.append(this.getSecretKey());
		stringBuffer.append(this.getMobiles());
		stringBuffer.append(this.getContent());
		stringBuffer.append(this.getSign());
		stringBuffer.append(this.getAddSerial());
		String mac = EncryptUtils.MD5Encode(stringBuffer.toString());
		this.setMac(mac);
	}

	public String getEcName() {
		return ecName;
	}

	public void setEcName(String ecName) {
		this.ecName = ecName;
	}

	public String getApId() {
		return apId;
	}

	public void setApId(String apId) {
		this.apId = apId;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getMobiles() {
		return mobiles;
	}

	public void setMobiles(String mobiles) {
		this.mobiles = mobiles;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getAddSerial() {
		return addSerial;
	}

	public void setAddSerial(String addSerial) {
		this.addSerial = addSerial;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

}
