package com.pzj.core.smp.business;

import com.pzj.framework.context.Result;
import com.pzj.framework.entity.QueryResult;

/**
 * Created by Administrator on 2017-1-11.
 * 业务调用接口定义
 * @author ZhouYuan
 */
public interface IBusinManageService {

	/**
	 * @api {dubbo} com.pzj.core.smp.record.IBusinManageService.insert 插入一条业务线
	 * @apiGroup 业务线管理
	 * @apiName 插入一条业务线
	 * @apiVersion 1.0.0-SNAPSHOT
	 * @apiDescription 插入一条业务线
	 *
	 * @apiParam (请求参数) {BusinModel} businModel 短信发送记录实体
	 *
	 * @apiParam (BusinModel) {String} name 业务线名称
	 * @apiParam (BusinModel) {String} describe 业务线描述
	 * @apiParam (BusinModel) {String} state 业务线状态 启用、ENABLE 禁用、DISABLE,创建是默认为ENABLE,目前前端控制不会出现DISABLE
	 *
	 * @apiParamExample 请求参数示例
	 *	{
	 *"name": "TEST",
	 *"describe": "测试产品线描述",
	 *"state": "DISABLE"
	 *}
	 *
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {Long} data 业务线id
	 *
	 * @apiParam (错误码) {ParameterException} 16998 参数为空
	 *
	 * @apiErrorExample {json} 异常示例
	 * {
	 *    "errorCode" : 16998,
	 *    "errorMsg":"参数为空"
	 * }
	 */
	Result<Long> insert(BusinModel businModel);

	/**
	* @api {dubbo} com.pzj.core.smp.record.IBusinManageService.selectByName 根据名称验证业务线是否存在
	* @apiGroup 业务线管理
	* @apiName 根据名称验证业务线是否存在
	* @apiVersion 1.0.0-SNAPSHOT
	* @apiDescription 根据名称验证业务线是否存在
	*
	* @apiParam (请求参数) {String} name 业务线名称
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {Boolean} data 验证结果 true是存在 false 不存在
	*
	* @apiParam (错误码) {ParameterException} 16055 业务线名称为空
	*
	* @apiErrorExample {json} 异常示例
	* {
	*    "errorCode" : 16055,
	*    "errorMsg":"业务线名称为空"
	* }
	*/
	Result<Boolean> selectByName(String name);

	/**
	* @api {dubbo} com.pzj.core.smp.record.IBusinManageService.updateState 根据id修改业务线状态
	* @apiGroup 业务线管理
	* @apiName 根据id修改业务线状态
	* @apiVersion 1.0.0-SNAPSHOT
	* @apiDescription 根据id修改业务线状态
	*
	* @apiParam (请求参数) {Long} id 业务线id
	* @apiParam (请求参数) {String} state 业务线状态
	*
	* @apiParam (响应数据) {int} errorCode 返回结果码
	* @apiParam (响应数据) {String} errorMsg 返回结果提示
	* @apiParam (响应数据) {Long} data 被影响的业务线id
	*
	* @apiParam (错误码) {ParameterException} 16052 业务线状态为空
	* @apiParam (错误码) {ParameterException} 16051 业务线id为空
	*
	* @apiErrorExample {json} 异常示例
	* {
	*    "errorCode" : 16052,
	*    "errorMsg":"业务线状态为空"
	* }
	*/
	Result<Long> updateState(Long id, String state);

	/**
	 * @api {dubbo} com.pzj.core.smp.record.IBusinManageService.selectByParam 分页查询业务线信息
	 * @apiGroup 业务线管理
	 * @apiName 分页查询业务线信息
	 * @apiVersion 1.0.0-SNAPSHOT
	 * @apiDescription 分页查询业务线信息
	 *
	 * @apiParam (请求参数) {BusinModel} businModel 业务线实体
	 *
	 * @apiParam (BusinModel) {String} [name] 业务线名称
	 * @apiParam (BusinModel) {String} [state] 业务线状态 启用、ENABLE 禁用、DISABLE
	 * @apiParam (BusinModel) {Integer} pageSize 查询数量
	 * @apiParam (BusinModel) {Integer} currentPage 查询页数
	 *
	 *
	 * @apiParam (响应数据) {int} errorCode 返回结果码
	 * @apiParam (响应数据) {String} errorMsg 返回结果提示
	 * @apiParam (响应数据) {data} data 返回数据及分页信息
	 *
	 *@apiSuccessExample {json} 成功响应数据
	 *{
	 *"errorCode": 10000,
	 *"errorMsg": "ok",
	 *"data": {
	 *	"total": 2,
	 *	"current_page": 1,
	 *	"total_page": 1,
	 *	"page_size": 20,
	 *	"records": [
	 *		{
	 *			"id": 7,
	 *			"name": "TEST",
	 *			"describe": "测试产品线描述",
	 *			"state": "启用",
	 *			"createDate": "Jan 12, 2017 1:45:57 PM",
	 *			"editDate": "Jan 12, 2017 4:29:09 PM",
	 *			"current_page": 1,
	 *			"page_size": 20
	 *			},
	 *			{
	 *			"id": 8,
	 *			"name": "YWXCSA",
	 *			"describe": "业务线测试11",
	 *			"state": "启用",
	 *			"createDate": "Jan 13, 2017 1:58:31 PM",
	 *			"editDate": "Jan 13, 2017 1:58:36 PM",
	 *			"current_page": 1,
	 *			"page_size": 20
	 *			}
	 *		]
	 *	}
	 *}
	 * @apiParam (错误码) {ParameterException} 16998 参数为空
	 *
	 * @apiErrorExample {json} 异常示例
	 * {
	 *    "errorCode" : 16998,
	 *    "errorMsg":"参数为空"
	 * }
	 */
	Result<QueryResult<BusinModel>> selectByParam(BusinModel businModel);
}
