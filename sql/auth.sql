show schemas ;

create schema myazeroth_auth;

use myazeroth_auth;

create table user(
id BIGINT NOT NULL AUTO_INCREMENT primary key,
phone varchar(100)  null,
email varchar(240)  null,
username varchar(50) not null unique key,
password varchar(500) not null,
secret varchar(500) not null,
answer varchar(500)  null,
enabled boolean not null
);

create table authorities (
username varchar(50) not null,
authority varchar(50) not null,
constraint fk_authority_user foreign key(username) references user(username)
);

create unique index ix_auth_username on authorities (username,authority);