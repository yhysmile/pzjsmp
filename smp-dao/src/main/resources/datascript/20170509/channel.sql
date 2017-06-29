alter table channel modify column channel_type  tinyint comment '11:鸿联企信通下行;12:鸿联企信通余额;13:鸿联企信通上行;21:高斯通下行;22:高斯通余额;23:高斯通上行;31:MAS政企云下行通道;';

insert into smp.channel(id, name, state, url, create_time, channel_type, priority_proportion) 
values(5, '政企云Mas短信下发', 1, 'http://112.35.4.197:15000', now(), 31, 10);