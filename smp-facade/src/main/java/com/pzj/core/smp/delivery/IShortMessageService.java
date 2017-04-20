package com.pzj.core.smp.delivery;

import com.pzj.framework.context.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息投递（Message Delivery）服务
 * Created by Administrator on 2016-12-29.
 */
public interface IShortMessageService {
    /**
     * 发送多个消息
     *
     * @api {dubbo} com.pzj.core.smp.delivery.IShortMessageService#sendMessage 发送短信接口
     * @apiGroup 短信发送服务
     * @apiVersion 1.0.0
     * @apiDescription
     * <h3>
     * 发送多个消息
     * </h3>
     * <p>
     *     可批量给多个手机号发送短信。
     * </p>
     * @apiParam {MessageBean} messageBean 消息数据
     *
     * @apiParam (messageBean) {MessageHead} head 消息头
     * @apiParam (messageBean) {List} phoneNums 手机号。实际是List&lt;String&gt;类型，每个手机号是String类型。
     * @apiParam (messageBean) {String} content 消息内容
     *
     * @apiParam (messageBean_head) {String} businessName 业务名
     * @apiParam (messageBean_head) {String=A,B,C} priority 优先级
     * @apiParam (messageBean_head) {Date} createDate=当前时间 创建时间
     * @apiParam (messageBean_head) {Long} timeOut 超时时间，单位为毫秒
     *
     * @apiParamExample {json} 参数示例
     *
     * {
     *     "head" : {
     *          "businessName" : "trade",
     *          "priority" : "A",
     *          "createDate" : "2017-01-16 09:51:23",
     *          "timeOut" : 300
     *     },
     *     "phoneNums" :[
     *          "18310605737",
     *          "18310605635",
     *          "18310605307"
     *     ],
     *     "content" : "短信内容"
     * }
     *
     * @apiSuccess (响应数据) {int} errorCode 错误码
     * @apiSuccess (响应数据) {String} errorMsg 错误说明
     * @apiSuccess (响应数据) {Boolean} data 发送短信结果。true为发送成功，false为发送失败。
     *
     * @apiSuccessExample {json} 响应数据示例
     *
     * {
     *     "errorCode" : 10000,
     *     "errorMsg" : null,
     *     "data" : true
     * }
     *
     * @apiError (错误码说明) 16040 投递消息错误：创建时间不能为空。
     * @apiError (错误码说明) 16041 投递消息错误：超时时间不能为空。
     * @apiError (错误码说明) 16042 投递消息错误：业务名不能为空。
     * @apiError (错误码说明) 16043 投递消息错误：优先级不能为空。
     * @apiError (错误码说明) 16044 投递消息错误：发送目标不能为空。
     * @apiError (错误码说明) 16046 投递消息错误：所指定的优先级的值无效。
     * @apiError (错误码说明) 16047 投递消息错误：业务名无效。
     *
     * @apiError (错误码说明) 16060 接收消息的手机号不能为空。
     * @apiError (错误码说明) 16061 接收消息的地址不能为空。
     * @apiError (错误码说明) 16062 消息的内容不能为空。
     *
     * @apiError (错误码说明) 16080 消息优先级调度队列的名称不能为空。
     *
     *
     * @param messageBean
     * @return
     */
    Result<Boolean> sendMessage(MessageBean messageBean);
}
