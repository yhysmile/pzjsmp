package com.pzj.message.sms.service;

public interface SmsSendService {
	/**
	 * 发送短信
	 * 
	 * @param phone
	 *            手机号
	 * @param message
	 *            短信内容
	 * @return
	 */
	/**
	 * @api {dubbo} com.pzj.message.sms.service.SmsSendService.sendSMS 发送短信接口
	 * @apiGroup 旧的鸿联企信通发送短信服务
	 * @apiName 发送短信接口
	 * @apiVersion 1.0.0-SNAPSHOT
	 * @apiDescription 发送短信接口
	 *
	 * @apiParam (请求参数) {String} phone 发送手机号
	 * @apiParam (请求参数) {String} message 发送短信内容
	 *
	 *
	 * @apiParamExample 请求参数示例
	 *	{
	 *		"phone": "13789073456",
	 *		"message": "短信内容"
	 *  }
	 *
	 * @apiParam (响应数据) {Integer} num 返回发送短信条数
	 *
	 */
	public Integer sendSMS(String phone, String message) throws Exception;

}
