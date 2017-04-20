alter table sms_blacklist rename blacklist;
alter table sms_busssys rename busssys;
alter table sms_channel rename channel;
alter table sms_channel_userinfo rename channel_userinfo;
alter table sms_count rename count;
alter table sms_error_record rename error_record;
alter table sms_flow_control rename flow_control;
alter table sms_record_01 rename record_01;
alter table sms_record_02 rename record_02;
alter table sms_record_03 rename record_03;
alter table sms_record_04 rename record_04;
alter table sms_record_05 rename record_05;
alter table sms_record_06 rename record_06;
alter table sms_record_07 rename record_07;
alter table sms_record_08 rename record_08;
alter table sms_record_09 rename record_09;
alter table sms_record_10 rename record_10;
alter table sms_record_11 rename record_11;
alter table sms_record_12 rename record_12;


CREATE TABLE `status_report` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '短信发送响应状态报告主键id',
  `phone_number` char(20) NOT NULL COMMENT '手机号',
  `channel_id` bigint(20) unsigned NOT NULL COMMENT '通道id',
  `linkid` varchar(20) NOT NULL COMMENT '调用通道唯一id',
  `content` varchar(100) NOT NULL COMMENT '状态报告内容',
  `response_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '状态报告响应时间',
  `detail_result` varchar(1000) DEFAULT '0' COMMENT '调用通道返回状态报告详细结果',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

alter table record_01 add column linkid varchar(20) NOT NULL COMMENT '调用通道唯一id';
alter table record_02 add column linkid varchar(20) NOT NULL COMMENT '调用通道唯一id';
alter table record_03 add column linkid varchar(20) NOT NULL COMMENT '调用通道唯一id';
alter table record_04 add column linkid varchar(20) NOT NULL COMMENT '调用通道唯一id';
alter table record_05 add column linkid varchar(20) NOT NULL COMMENT '调用通道唯一id';
alter table record_06 add column linkid varchar(20) NOT NULL COMMENT '调用通道唯一id';
alter table record_07 add column linkid varchar(20) NOT NULL COMMENT '调用通道唯一id';
alter table record_08 add column linkid varchar(20) NOT NULL COMMENT '调用通道唯一id';
alter table record_09 add column linkid varchar(20) NOT NULL COMMENT '调用通道唯一id';
alter table record_10 add column linkid varchar(20) NOT NULL COMMENT '调用通道唯一id';
alter table record_11 add column linkid varchar(20) NOT NULL COMMENT '调用通道唯一id';
alter table record_12 add column linkid varchar(20) NOT NULL COMMENT '调用通道唯一id';