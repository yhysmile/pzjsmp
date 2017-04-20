package com.pzj.core.smp.common.exception;

/**
 * Created by Administrator on 2017-1-4.
 * 短信平台错误码
 * 100~149为短信通道异常码
 */
public enum SmpExceptionCode {
	ERROR(16999, "失败"), //
	PARAMS_NULL(16998, "参数为空"), //
	PAGE_NULL(16997, "分页数据为空"), //
	PARAM_ERROR(16996, "参数错误！"), //

	RECORD_ID_NULL(16020, "短信记录id为空"), //
	RECORD_STATE_NULL(16021, "短信记录发送状态为空"), //
	RECORD_STATE_ERROR(16022, "没有相应的短信发送状态类型"), //
	RECORD_CONVERT_ERROR(16023, "参数转换异常"), //
	RECORD_QUERY_ERROR(16024, "查询短信记录异常"), //
	RECORD_INSERT_ERROR(16025, "短信记录插入异常"), //
	RECORD_UPDATE_ERROR(16026, "修改短信记录发送状态异常"), //

	ERROR_RECORD_INSERT_ERROR(16027, "插入短信错误信息异常"), //
	ERROR_RECORD_QUERY_ERROR(16028, "查询短信错误信息异常"), //

	/***  Service层 Message模块  ***/
	DELIVERY_CREATEDATE_Null(16040, "投递消息错误：创建时间不能为空。"), //
	DELIVERY_TIMEOUT_Null(16041, "投递消息错误：超时时间不能为空。"), //
	DELIVERY_BUSINESSNAME_NULL(16042, "投递消息错误：业务名不能为空。"), //
	DELIVERY_PRIORITY_NULL(16043, "投递消息错误：优先级不能为空。"), //
	DELIVERY_ADDRESS_NULL(16044, "投递消息错误：发送目标不能为空。"), //
	DELIVERY_TIMEOUT(16045, "投递消息错误：消息已超时。", "投递消息错误：消息已超时，创建时间为 %1$tF %1$tT.%1$tL（ %1$tQ ），过期时间为 %2$d，超时 %3$d。") {
		@Override
		public String getTemplateMessage(Object... args) {
			if (args == null || args.length != 3)
				return getMsg();
			String message = String.format(getTemplate(), args[0], args[1], args[2]);
			return message;
		}
	},
	DELIVERY_PRIORITY_INVALID(16046, "投递消息错误：所指定的优先级的值无效。", "投递消息错误：所指定的优先级 %1$s 无效。"), //
	DELIVERY_BUSSINESSNAME_INVALID(16047, "投递消息错误：业务名无效。", "投递消息错误：业务名 %1$s 无效。") {
		@Override
		public String getTemplateMessage(Object... args) {
			if (args == null || args.length != 1)
				return getMsg();
			String message = String.format(getTemplate(), args[0]);
			return message;
		}
	}, //

	/***  Message模块  ***/
	MESSAGE_PHONENUM_NULL(16060, "接收消息的手机号不能为空。"), //
	MESSAGE_ADDRESS_NULL(16061, "接收消息的地址不能为空。"), //
	MESSAGE_CONTENT_NULL(16062, "消息的内容不能为空。"), //

	/***  Scheduling模块  ***/
	SCHEDULING_NAME_NULL(16080, "消息优先级调度队列的名称不能为空。"), //

	/************************** 短信通道异常码 开始  ******************************/
	HTTP_RESP_ERR(16100, "远程访问http返回状态码异常！"), //
	NOT_FOUND_GST_CHANNELUSER_ERR(16101, "没找到高斯通账户信息！"), //
	NOT_FOUND_HLQXT_CHANNELUSER_ERR(16102, "没找到鸿联企信通账户信息！"), //
	NOT_FOUND_GST_CHANNEL_ERR(16103, "没找到高斯通通道信息！"), //
	NOT_FOUND_HLQXT_CHANNEL_ERR(16104, "没找到鸿联企信通通道信息！"), //
	NO_AVAIABLE_CHANNEL_ERR(16105, "没有可用的通道！"),
	/************************** 短信通道异常码 结束  *******************************/

	BUSIN_INSERT_ERROR(16050, "业务线插入异常"), //
	BUSIN_ID_NULL(16051, "业务线id为空"), //
	BUSIN_STATE_NULL(16052, "业务线状态为空"), //
	BUSIN_STATE_ERROR(16053, "修改业务线状态异常"), //
	BUSIN_TYPE_ERROR(16054, "没有相应的业务线状态"), //
	BUSIN_NAME_NULL(16055, "业务线名称为空"), //
	BUSIN_QUERY_ERROR(16056, "分页查询业务线数据异常"), //
	BUSIN_NAME_ERROR(16057, "名称查询业务线数据异常"); //

	private int code;
	private String msg;
	private String template;

	SmpExceptionCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	SmpExceptionCode(int code, String msg, String template) {
		this(code, msg);
		this.template = template;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public String getTemplate() {
		return template;
	}

	public String getTemplateMessage(Object... args) {
		if (args == null || args.length == 0)
			return getMsg();

		return String.format(getTemplate(), args);
	}
}
