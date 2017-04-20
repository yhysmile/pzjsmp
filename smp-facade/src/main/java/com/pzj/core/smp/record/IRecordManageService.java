package com.pzj.core.smp.record;

import com.pzj.framework.context.Result;
import com.pzj.framework.entity.QueryResult;

/**
 * Created by Administrator on 2017-1-3.
 * 短信记录业务接口
 * @author ZhouYuan
 */

public interface IRecordManageService {
	/**
	 * @api {dubbo} com.pzj.core.smp.record.IRecordManageService.insert 插入单条短信记录
	 * @apiGroup 短信发送记录
	 * @apiName 插入单条短信记录
	 * @apiVersion 1.0.0-SNAPSHOT
	 * @apiDescription 插入单条短信发送记录
	 *
	 * @apiParam (请求参数) {RecordModel} recordModel 短信发送记录实体
	 *
	 * @apiParam (RecordModel) {String} sendPhone 发送手机号
	 * @apiParam (RecordModel) {String} sendState 发送成功、SEND_SUCCESS   发送失败、SEND_ERROR
	 * @apiParam (RecordModel) {String} [channelName] 通道名称
	 * @apiParam (RecordModel) {Long} [channelId] 通道id
	 * @apiParam (RecordModel) {Timestamp} sendTime 发送时间
	 * @apiParam (RecordModel) {Integer} sendContent 短信内容
	 * @apiParam (RecordModel) {Integer} sendNum 发送次数
	 * @apiParam (StockRuleModel) {Timestamp} updateTime 重发时间
	 * @apiParam (StockRuleModel) {String} [sendLinkId] 发送成功id
	 *
	 * @apiParamExample 请求参数示例
	 *	{
	 *   "sendPhone": "98765432100",
	 *	"sendState": "SEND_ERROR",
	 *	"channelName": "测试短信状态",
	 *	"channelId": 123456,
	 *	"sendTime": "Jan 19, 2017 3:11:29 PM",
	 *	"sendContent": "测试短信状态",
	 *	"sendNum": 1,
	 *	"updateTime": "Jan 19, 2017 3:11:29 PM",
	 *	"sendLinkId": "20170106"
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
	Result<Long> insert(RecordModel recordModel);

	/**
	 * @api {dubbo} com.pzj.core.smp.record.IRecordManageService.selectById 根据短信id查询短信发送记录
	 * @apiGroup 短信发送记录
	 * @apiName 查询单条短信记录
	 * @apiVersion 1.0.0-SNAPSHOT
	 * @apiDescription 根据短信id查询短信发送记录
	 *
	 * @apiParam (请求参数) {Long} recordId 短信记录id
	 *
	 *
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {RecordModel} data 短信发送记录实体
	 *
	 * @apiSuccessExample {json} 成功响应数据
	 * {
	 *	"errorCode": 10000,
	 *	"errorMsg": "ok",
	 *	"data": {
	 *	"sendId": 32,
	 *	"sendPhone": "987654321",
	 *	"sendState": "SEND_ERROR",
	 *	"channelName": "测试通道",
	 *	"channelId": 123456,
	 *	"sendTime": "Jan 9, 2017 2:07:28 PM",
	 *	"sendContent": "状态失败",
	 *	"sendNum": 1,
	 *	"updateTime": "Jan 9, 2017 2:16:22 PM",
	 *	"sendLinkId": "20170106",
	 *	"current_page": 1,
	 *	"page_size": 20
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
	Result<RecordModel> selectById(Long id);

	/**
	 * @api {dubbo} com.pzj.core.smp.record.IRecordManageService.updateRecordState 修改短信发送记录状态
	 * @apiGroup 短信发送记录
	 * @apiName 修改短信发送记录状态
	 * @apiVersion 1.0.0-SNAPSHOT
	 * @apiDescription 修改短信发送记录状态
	 *
	 * @apiParam (请求参数) {Long} recordId 修改的短信记录id
	 * @apiParam (请求参数) {String} recordSatate 发送成功、SEND_SUCCESS   发送失败、SEND_ERROR
	 *
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {Long} data 返回创建短信发送记录id主键
	 *
	 *@apiSuccessExample {json} 成功响应数据
	 *{
	 *	"errorCode": 10000,
	 *	"errorMsg": "ok",
	 *	"data": 1
	 *}
	 * @apiParam (错误码) {ParameterException} 16021 短信记录发送状态为空
	 * @apiParam (错误码) {ParameterException} 16020 短信记录id为空
	 *
	 * @apiErrorExample {json} 异常示例
	 * {
	 *    "errorCode" : 16021,
	 *    "errorMsg":"短信记录发送状态为空"
	 * }
	 */
	Result<Integer> updateRecordState(Long recordId, String recordSatate);

	 /**
	 * @api {dubbo} com.pzj.core.smp.record.IRecordManageService.selectByParam 分页查询短信记录
	 * @apiGroup 短信发送记录
	 * @apiName 分页查询短信记录
	 * @apiVersion 1.0.0-SNAPSHOT
	 * @apiDescription 分页查询短信记录
	 *
	  * @apiParam (请求参数) {RecordModel} recordModel 短信发送记录实体
	  *
	  * @apiParam (RecordModel) {String} [sendPhone] 发送手机号
	  * @apiParam (RecordModel) {String} [sendState] 发送成功、SEND_SUCCESS   发送失败、SEND_ERROR
	  * @apiParam (RecordModel) {Timestamp} [sendTime] 发送时间
	  * @apiParam (RecordModel) {Integer} pageSize 查询数量
	  * @apiParam (RecordModel) {Integer} currentPage 查询页数
	  *
	  *
	  * @apiParam (响应数据) {int} errorCode 返回结果码
	  * @apiParam (响应数据) {String} errorMsg 返回结果提示
	  * @apiParam (响应数据) {data} data 返回数据及分页信息
	  *
	  *@apiSuccessExample {json} 成功响应数据
	  *{
	  *	 "errorCode": 10000,
	  *	 "errorMsg": "ok",
	  *	 "data": {
	  *	 "total": 1,
	  *	 "current_page": 1,
	  *	 "total_page": 1,
	  * "page_size": 20,
	  * "records": [
	  *			 {
	  *				 "sendId": 32,
	  *				 "sendPhone": "987654321",
	  *				 "sendState": "SEND_ERROR",
	  *				 "channelName": "测试通道",
	  *				 "channelId": 123456,
	  *				 "sendTime": "Jan 9, 2017 2:07:28 PM",
	  *				 "sendContent": "状态失败",
	  *				 "sendNum": 1,
	  *				 "updateTime": "Jan 9, 2017 2:16:22 PM",
	  *				 "sendLinkId": "20170106",
	  *				 "current_page": 1,
	  *				 "page_size": 20
	  *	 		}
	  * 			]
	  * 		}
	  *}
	 * @apiParam (错误码) {ParameterException} 16998 参数为空
	 *
	 * @apiErrorExample {json} 异常示例
	 * {
	 *    "errorCode" : 16998,
	 *    "errorMsg":"参数为空"
	 * }
	 */
	Result<QueryResult<RecordModel>> selectByParam(RecordModel recordModel);

}
