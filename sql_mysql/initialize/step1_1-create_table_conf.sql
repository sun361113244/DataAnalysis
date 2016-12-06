drop table if exists data_analysis_log;
    
create table data_analysis_log
(
  id bigint(20) not null auto_increment,
  op_userid varchar(200) ,
  op_ip varchar(200) ,
  op_uri varchar(200) ,
  op_excu_time datetime ,
  op_params varchar(200) ,
  op_res varchar(200) ,
  op_exception varchar(200) ,
  op_consume_time bigint(20) ,
  primary key (id)
) engine=innodb default charset=utf8 collate=utf8_unicode_ci;

