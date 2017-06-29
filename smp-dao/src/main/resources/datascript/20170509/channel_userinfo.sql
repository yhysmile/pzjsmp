alter table channel_userinfo modify column channel_identity  tinyint comment '1:鸿联企信通;2:高斯通;3:MAS政企云';

insert into smp.channel_userinfo(id, channel_identity, username, password, create_time) 
values(3, 3, 'mfly11', 'passwd@1111', now());
