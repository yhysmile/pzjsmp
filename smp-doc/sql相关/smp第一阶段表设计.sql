##业务队列表
CREATE TABLE `busssys` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '业务系统主键id',
  `name` varchar(20) NOT NULL COMMENT '业务系统英文名称',
  `state` tinyint(1) unsigned DEFAULT '1' COMMENT '业务系统状态；1:正常;2:停用',
  `remarks` varchar(100) DEFAULT '0' COMMENT '详细备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务系统';

##短信发送记录表 按月份划分
CREATE TABLE `record_01` (
  `record_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '短信发送记录主键id',
  `phone_number` char(20) NOT NULL COMMENT '手机号',
  `state` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '短信发送状态；1:发送成功;2:发送失败;',
  `channel_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '通道id',
  `channel_name` varchar(20) NOT NULL DEFAULT '#' COMMENT '通道名称',
  `content` varchar(500) NOT NULL COMMENT '短信内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `send_num` int(8) NOT NULL DEFAULT '1' COMMENT '短信发送次数',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `link_id` varchar(20) NOT NULL DEFAULT '0' COMMENT '调用通道唯一id',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=821290396982181889 DEFAULT CHARSET=utf8 COMMENT='短信发送记录,1月份';

##短信发送错误详情表
CREATE TABLE `error_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '短信发送错误记录主键id',
  `record_num` int(4) NOT NULL DEFAULT '1' COMMENT '短信记录表编号',
  `record_id` bigint(20) unsigned NOT NULL COMMENT '短信发送记录id',
  `err_detail` varchar(100) NOT NULL COMMENT '短信错误详情',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='短信发送错误记录';

