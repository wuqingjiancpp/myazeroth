create table myazeroth_business.my_comment
(
    id           bigint auto_increment,
    pid          bigint        not null,
    content      varchar(4096) not null,
    create_time  datetime      not null,
    creater      varchar(64)   not null,
    update_time  datetime      null,
    updater      varchar(64)   null,
    check_time   datetime      null,
    checker      varchar(64)   null,
    `like`       int           null,
    dislike      int           null,
    is_blacklist boolean       null,
    is_report    boolean       null,
    reply        varchar(4096) null,
    top          int           null,
    is_checked   boolean       null,
    constraint id_PK
        primary key (id),
    constraint pid_FK
        foreign key (pid) references myazeroth_business.my_comment (id)
)
    comment '评论';