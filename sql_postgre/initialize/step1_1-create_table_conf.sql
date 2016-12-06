drop table if exists data_analysis_log;
    
create table data_analysis_log
(
  id serial primary key ,
  op_userid varchar(255)  ,
  op_ip varchar(255)  ,
  op_uri varchar(255)  ,
  op_excu_time date ,
  op_params varchar(255) ,
  op_res varchar(255) ,
  op_exception varchar(255) ,
  op_consume_time int
);

