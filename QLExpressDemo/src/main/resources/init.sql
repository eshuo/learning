
create table param_info
(
    id     int auto_increment
        primary key,
    field  varchar(255) null comment '字段',
    title  varchar(255) null comment '标题',
    type   varchar(255) null comment '数据类型',
    c_info varchar(255) null comment '类信息',
    constraint paramInfo_id_uindex
        unique (id)
);

create table rule_info
(
    id     int auto_increment
        primary key,
    name   varchar(255)  null,
    status varchar(64)   null,
    seq    int default 8 null,
    remark varchar(255)  null,
    constraint rule_info_id_uindex
        unique (id)
);



create table condition_info
(
    id            int auto_increment
        primary key,
    expression    varchar(255) null comment '表达式 ',
    parent_id     int          null comment '自引用id ',
    result_info   varchar(512) null comment '响应信息',
    context_info  varchar(255) null,
    param_info_id int          null,
    rule_id       int          null,
    constraint condition_info___fk_rule
        foreign key (rule_id) references rule_info (id),
    constraint condition_info_param_info_id_fk
        foreign key (param_info_id) references param_info (id)
)
    comment '条件表';


