#修改通道用户表，新增账户余额字段
alter table channel_userinfo add column balance int(10) unsigned not null default 0 comment '通道余额，单位为分';

#修改状态报告表
alter table status_report drop column update_time;
alter table status_report modify column detail_result varchar(1000) not null COMMENT '调用通道返回状态报告详细结果';

# 修改通道用户表，新增通道筛选比重值
alter table channel_userinfo add column priority_proportion int(6) unsigned not null comment '通道优先比重';


#修改短信平台表结构，添加注释
alter table blacklist comment '黑名单';
alter table busssys comment '业务系统';
alter table channel comment '短信通道';
alter table channel_userinfo comment '短信通道用户信息';
alter table count comment '短信统计';
alter table error_record comment '短信发送错误记录';
alter table flow_control comment '短信流控配置';
alter table record_01 comment '短信发送记录,1月份';
alter table record_02 comment '短信发送记录,2月份';
alter table record_03 comment '短信发送记录,3月份';
alter table record_04 comment '短信发送记录,4月份';
alter table record_05 comment '短信发送记录,5月份';
alter table record_06 comment '短信发送记录,6月份';
alter table record_07 comment '短信发送记录,7月份';
alter table record_08 comment '短信发送记录,8月份';
alter table record_09 comment '短信发送记录,9月份';
alter table record_10 comment '短信发送记录,10月份';
alter table record_11 comment '短信发送记录,11月份';
alter table record_12 comment '短信发送记录,12月份';
alter table status_report comment '短信发送响应状态报告';