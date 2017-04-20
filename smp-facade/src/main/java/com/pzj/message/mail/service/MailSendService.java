package com.pzj.message.mail.service;

public interface MailSendService {
	/**
	 * 发送邮件
	 * 
	 * @param subject 标题
	 *            
	 * @param content 邮件内容，只支持text类型的内容
	 * 
	 * @param toAddresses 要发送的地址
	 * 
	 * @return
	 */
	/**
	 * @api {dubbo} com.pzj.message.mail.service.MailSendService.sendMail 发送邮件
	 * @apiGroup 发送邮件服务
	 * @apiName 发送邮件
	 * @apiVersion 1.0.0-SNAPSHOT
	 * @apiDescription 发送邮件
	 *
	 * @apiParam (请求参数) {String} subject 邮件主题
	 * @apiParam (请求参数) {String} content 邮件内容
	 * @apiParam (请求参数) {String[]} mailtoArrays 接收邮件的邮箱数组集合
	 *
	 * @apiParamExample 请求参数示例
	 *	{
	 *		"subject": "测试邮件主题",
	 *		"content": "测试邮件具体内容详情",
	 *		"mailtoArrays":[
	 *			"zhangshan@mftour.cn",
	 *			"lisi@mftour.cn"
	 *		]
	 *  }
	 *
	 *
	 */
	public void sendMail(String subject, String content, String[] mailtoArrays) throws Exception;

}
