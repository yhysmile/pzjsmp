package com.pzj.core.smp.errorRecord;

import com.pzj.framework.context.Result;

/**
 * Created by Administrator on 2017-1-9.
 * 短信错误记录业务接口
 * @author ZhouYuan
 */
public interface IErrorRecordManageService {
	/**
	 * @api {dubbo} com.pzj.core.smp.record.IErrorRecordManageService.insert 插入单条短信错误信息
	 * @apiGroup 短信错误信息
	 * @apiName 插入单条短信错误信息
	 * @apiVersion 1.0.0-SNAPSHOT
	 * @apiDescription 在发送短信错误时，将错误信息记录下来
	 *
	 * @apiParam (请求参数) {ErrorRecordModel} errRecordModel 短信错误信息实体
	 *
	 * @apiParam (ErrorRecordModel) {String} sendErrDetail 失败详情
	 * @apiParam (ErrorRecordModel) {Long} sendRecordId 短信记录id
	 * @apiParam (ErrorRecordModel) {Timestamp} sendTime 发送时间
	 *
	 * @apiParamExample 请求参数示例
	 *	{
	 *   "sendRecordId": "98765432100",
	 *	"sendErrDetail": "因为...发送失败了",
	 *	"sendTime": "Jan 19, 2017 3:11:29 PM"
	 * }
	 *
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {Long} data 返回创建短信发送记录id主键
	 *
	 * @apiParam (错误码) {ParameterException} 16998 参数为空
	 *
	 * @apiErrorExample {json} 异常示例
	 * {
	 *    "errorCode" : 16998,
	 *    "errorMsg":"参数为空"
	 * }
	 */
	Result<Integer> insert(ErrorRecordModel errorRecordModel);

	/**
	 * @api {dubbo} com.pzj.core.smp.record.IErrorRecordManageService.selectByRecordId 根据短信id查询错误信息
	 * @apiGroup 短信错误信息
	 * @apiName 根据短信id查询错误信息
	 * @apiVersion 1.0.0-SNAPSHOT
	 * @apiDescription 根据短信id查询错误信息
	 *
	 * @apiParam (请求参数) {Long} recordId 短信记录id
	 *
	 *
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {ErrorRecordModel} data 短信发送记录实体
	 *
	 * @apiParam (ErrorRecordModel) {String} sendErrDetail 失败详情
	 * @apiParam (ErrorRecordModel) {Long} sendRecordId 短信记录id
	 * @apiParam (ErrorRecordModel) {Timestamp} sendTime 发送时间
	 * @apiParam (ErrorRecordModel) {Integer} sendNum 短信记录的月份
	 *
	 * @apiSuccessExample {json} 成功响应数据
	 * {
	 *	"sendErrDetail": "发送失败111",
	 *	"sendTime": "Jan 11, 2017 3:15:56 PM",
	 *	"sendRecordId": 111
	 *	"sendNum":1
	 *	}
	 * }
	 *
	 * @apiParam (错误码) {ParameterException} 16020 短信记录id为空
	 *
	 *
	 * @apiErrorExample {json} 异常示例
	 * {
	 *    "errorCode" : 16020,
	 *    "errorMsg":"短信记录id为空"
	 * }
	 */
	Result<ErrorRecordModel> selectByRecordId(Long recordId);
}
