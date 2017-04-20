# 修改短信通道用户信息
alter table smp.sms_channel_userinfo change channel_id channel_identity tinyint(1) unsigned DEFAULT '1' COMMENT '1:鸿联企信通;2:高斯通';

#初始化通道账户信息
insert into smp.sms_channel_userinfo(channel_identity, username, password, create_time) values(1, 'mfgj', 'mfgj321', NOW());
insert into smp.sms_channel_userinfo(channel_identity, username, password, create_time) values(3, '70277:admin', '53636112', NOW());

