#sms_channel 表修改
alter table sms_channel add column channel_type tinyint(1) unsigned NOT NULL DEFAULT '11' COMMENT '11:鸿联企信通下行;12:鸿联企信通余额;13:鸿联企信通上行;21:高斯通下行;22:高斯通余额;23:高斯通上行';


#sms_channel 数据初始化
insert into sms_channel(name, url, channel_type, create_time) values('鸿联企信通短信下发', 'http://114.255.71.158:8061/', 11, now());
insert into sms_channel(name, url, channel_type, create_time) values('鸿联企信通余额查询', 'http://114.255.71.158:8061/getfee/', 12, now());

insert into sms_channel(name, url, channel_type, create_time) values('高斯通短信下发', 'http://gateway.iems.net.cn/GsmsHttp', 21, now());
insert into sms_channel(name, url, channel_type, create_time) values('高斯通余额查询', 'http://gateway.iems.net.cn/GeneralSMS/GetAccountBalance', 22, now());


#sms_blacklist 数据初始化
insert into sms_blacklist(phone_number, create_time) values('18201359872', now());


#sms_flow_control 数据初始化
insert into sms_flow_control(channel_id, time_unit, category, limit_threshold, create_time) values(1, 6, 1, 30, now());


#sms_record 表修改
alter table sms_record drop column template_id;
alter table sms_record modify column remarks varchar(1000) DEFAULT '0' COMMENT '记录短信发送过程中问题详细备注';
alter table sms_record add column send_num int(8) NOT NULL DEFAULT 1 COMMENT '短信发送次数';
alter table sms_record add column update_time timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间';


#新增sms_channel_userinfo 短信通道用户表
create table sms_channel_userinfo(
	id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '短信通道用户信息ID',
    channel_id bigint(20) NOT NULL COMMENT '通道ID',
	username varchar(64) NOT NULL COMMENT '短信通道账户名称',
	password varchar(64) NOT NULL COMMENT '短信通道账户密码',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


