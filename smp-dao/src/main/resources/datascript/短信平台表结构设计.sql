create table sms_blacklist(
	id bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '黑名单主键id',
	phone_number char(20) NOT NULL COMMENT '手机号',
	state tinyint(1) unsigned DEFAULT 1 COMMENT '1:正常;2:停用',	
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

create table sms_channel(
	id bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '短信通道主键id',
	name varchar(64) NOT NULL COMMENT '通道名称',
	state tinyint(1) unsigned DEFAULT 1 COMMENT '1:正常;2:停用',	
	url varchar(64) NOT NULL COMMENT '通道访问地址',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

create table sms_count(
	id bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '短信统计主键id',
	channel_id bigint(20) unsigned NOT NULL COMMENT '通道id',
	ok_num int(10) unsigned NOT NULL COMMENT '成功数量',
	err_num int(10) unsigned NOT NULL COMMENT '失败数量',
	period int(6) unsigned NOT NULL COMMENT '统计期间；格式：201610',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

create table sms_flow_control(
	id bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '短信流控配置主键id',
	channel_id bigint(20) unsigned NOT NULL COMMENT '通道id',
	time_unit tinyint(1) unsigned DEFAULT 1 COMMENT '限流时间单位；1:日;2:周;3:月;4:年;5:时;6:分;7:秒;',
	category tinyint(1) unsigned DEFAULT 1 COMMENT '限流种类；1:手机号;2:IP地址;3:短信通道',
	limit_threshold int(6) unsigned NOT NULL COMMENT '限流阈值',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

create table sms_record(
	id bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '短信发送记录主键id',
	phone_number char(20) NOT NULL COMMENT '手机号',
	state tinyint(1) unsigned DEFAULT 1 COMMENT '短信发送状态；1:发送成功;2:发送失败;3:黑名单过滤;4:流控过滤;5:模板不匹配;',
	template_id bigint(20) unsigned NOT NULL COMMENT '模板id',
	channel_id bigint(20) unsigned NOT NULL COMMENT '通道id',
	content varchar(500) NOT NULL COMMENT '短信内容',
	remarks varchar(100) DEFAULT '0' COMMENT '记录短信发送过程中问题详细备注',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;