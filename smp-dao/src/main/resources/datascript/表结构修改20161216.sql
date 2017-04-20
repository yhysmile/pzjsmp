#sms_flow_control 修改添加state字段
alter table smp.sms_flow_control add column state tinyint(1) unsigned not null DEFAULT '1' COMMENT '1:正常;2:停用';

#sms_record 修改state字段
alter table smp.sms_record modify column state tinyint(1) unsigned not null DEFAULT '1' COMMENT '短信发送状态；1:发送成功;2:发送失败;';

#sms_busssys 新增业务系统表，维护短信队列支持业务系统
CREATE TABLE sms_busssys (
  id bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '业务系统主键id',
  name varchar(20) NOT NULL COMMENT '业务系统英文名称',
  state tinyint(1) unsigned DEFAULT '1' COMMENT '业务系统状态；1:正常;2:停用',
	remarks varchar(100) DEFAULT '0' COMMENT '详细备注',
  create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


#sms_busssys 新增业务系统表，维护短信队列支持业务系统
CREATE TABLE sms_error_record (
  id bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '短信发送错误记录主键id',
	record_num int(4) NOT NULL DEFAULT '1' COMMENT '短信记录表编号',
  record_id bigint(20) unsigned NOT NULL COMMENT '短信发送记录id',
  err_detail varchar(100) NOT NULL COMMENT '短信错误详情',
  create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

#拆分sms_record表
CREATE TABLE `sms_record_01` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '短信发送记录主键id',
  `phone_number` char(20) NOT NULL COMMENT '手机号',
  `state` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '短信发送状态；1:发送成功;2:发送失败;',
  `channel_id` bigint(20) unsigned NOT NULL COMMENT '通道id',
  `content` varchar(500) NOT NULL COMMENT '短信内容',
  `remarks` varchar(1000) DEFAULT '0' COMMENT '记录短信发送过程中问题详细备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `send_num` int(8) NOT NULL DEFAULT '1' COMMENT '短信发送次数',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `sms_record_02` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '短信发送记录主键id',
  `phone_number` char(20) NOT NULL COMMENT '手机号',
  `state` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '短信发送状态；1:发送成功;2:发送失败;',
  `channel_id` bigint(20) unsigned NOT NULL COMMENT '通道id',
  `content` varchar(500) NOT NULL COMMENT '短信内容',
  `remarks` varchar(1000) DEFAULT '0' COMMENT '记录短信发送过程中问题详细备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `send_num` int(8) NOT NULL DEFAULT '1' COMMENT '短信发送次数',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `sms_record_03` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '短信发送记录主键id',
  `phone_number` char(20) NOT NULL COMMENT '手机号',
  `state` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '短信发送状态；1:发送成功;2:发送失败;',
  `channel_id` bigint(20) unsigned NOT NULL COMMENT '通道id',
  `content` varchar(500) NOT NULL COMMENT '短信内容',
  `remarks` varchar(1000) DEFAULT '0' COMMENT '记录短信发送过程中问题详细备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `send_num` int(8) NOT NULL DEFAULT '1' COMMENT '短信发送次数',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `sms_record_04` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '短信发送记录主键id',
  `phone_number` char(20) NOT NULL COMMENT '手机号',
  `state` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '短信发送状态；1:发送成功;2:发送失败;',
  `channel_id` bigint(20) unsigned NOT NULL COMMENT '通道id',
  `content` varchar(500) NOT NULL COMMENT '短信内容',
  `remarks` varchar(1000) DEFAULT '0' COMMENT '记录短信发送过程中问题详细备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `send_num` int(8) NOT NULL DEFAULT '1' COMMENT '短信发送次数',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `sms_record_05` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '短信发送记录主键id',
  `phone_number` char(20) NOT NULL COMMENT '手机号',
  `state` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '短信发送状态；1:发送成功;2:发送失败;',
  `channel_id` bigint(20) unsigned NOT NULL COMMENT '通道id',
  `content` varchar(500) NOT NULL COMMENT '短信内容',
  `remarks` varchar(1000) DEFAULT '0' COMMENT '记录短信发送过程中问题详细备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `send_num` int(8) NOT NULL DEFAULT '1' COMMENT '短信发送次数',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `sms_record_06` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '短信发送记录主键id',
  `phone_number` char(20) NOT NULL COMMENT '手机号',
  `state` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '短信发送状态；1:发送成功;2:发送失败;',
  `channel_id` bigint(20) unsigned NOT NULL COMMENT '通道id',
  `content` varchar(500) NOT NULL COMMENT '短信内容',
  `remarks` varchar(1000) DEFAULT '0' COMMENT '记录短信发送过程中问题详细备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `send_num` int(8) NOT NULL DEFAULT '1' COMMENT '短信发送次数',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `sms_record_07` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '短信发送记录主键id',
  `phone_number` char(20) NOT NULL COMMENT '手机号',
  `state` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '短信发送状态；1:发送成功;2:发送失败;',
  `channel_id` bigint(20) unsigned NOT NULL COMMENT '通道id',
  `content` varchar(500) NOT NULL COMMENT '短信内容',
  `remarks` varchar(1000) DEFAULT '0' COMMENT '记录短信发送过程中问题详细备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `send_num` int(8) NOT NULL DEFAULT '1' COMMENT '短信发送次数',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `sms_record_08` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '短信发送记录主键id',
  `phone_number` char(20) NOT NULL COMMENT '手机号',
  `state` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '短信发送状态；1:发送成功;2:发送失败;',
  `channel_id` bigint(20) unsigned NOT NULL COMMENT '通道id',
  `content` varchar(500) NOT NULL COMMENT '短信内容',
  `remarks` varchar(1000) DEFAULT '0' COMMENT '记录短信发送过程中问题详细备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `send_num` int(8) NOT NULL DEFAULT '1' COMMENT '短信发送次数',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `sms_record_09` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '短信发送记录主键id',
  `phone_number` char(20) NOT NULL COMMENT '手机号',
  `state` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '短信发送状态；1:发送成功;2:发送失败;',
  `channel_id` bigint(20) unsigned NOT NULL COMMENT '通道id',
  `content` varchar(500) NOT NULL COMMENT '短信内容',
  `remarks` varchar(1000) DEFAULT '0' COMMENT '记录短信发送过程中问题详细备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `send_num` int(8) NOT NULL DEFAULT '1' COMMENT '短信发送次数',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `sms_record_10` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '短信发送记录主键id',
  `phone_number` char(20) NOT NULL COMMENT '手机号',
  `state` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '短信发送状态；1:发送成功;2:发送失败;',
  `channel_id` bigint(20) unsigned NOT NULL COMMENT '通道id',
  `content` varchar(500) NOT NULL COMMENT '短信内容',
  `remarks` varchar(1000) DEFAULT '0' COMMENT '记录短信发送过程中问题详细备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `send_num` int(8) NOT NULL DEFAULT '1' COMMENT '短信发送次数',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `sms_record_11` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '短信发送记录主键id',
  `phone_number` char(20) NOT NULL COMMENT '手机号',
  `state` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '短信发送状态；1:发送成功;2:发送失败;',
  `channel_id` bigint(20) unsigned NOT NULL COMMENT '通道id',
  `content` varchar(500) NOT NULL COMMENT '短信内容',
  `remarks` varchar(1000) DEFAULT '0' COMMENT '记录短信发送过程中问题详细备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `send_num` int(8) NOT NULL DEFAULT '1' COMMENT '短信发送次数',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `sms_record_12` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '短信发送记录主键id',
  `phone_number` char(20) NOT NULL COMMENT '手机号',
  `state` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '短信发送状态；1:发送成功;2:发送失败;',
  `channel_id` bigint(20) unsigned NOT NULL COMMENT '通道id',
  `content` varchar(500) NOT NULL COMMENT '短信内容',
  `remarks` varchar(1000) DEFAULT '0' COMMENT '记录短信发送过程中问题详细备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `send_num` int(8) NOT NULL DEFAULT '1' COMMENT '短信发送次数',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

drop table sms_record;

