#修改通道用户表，删除通道比重字段
alter table channel_userinfo drop column priority_proportion;

#修改通道表，新增通道比重字段
alter table channel add column priority_proportion int(6) unsigned not null comment '通道优先比重';