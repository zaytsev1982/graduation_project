DROP table if exists user_roles;
DROP table if exists usr;

create table usr(
id BIGINT not null auto_increment,
user_name varchar not null,
password varchar not null,
primary key(id)
)engine = InnoDB;

create table user_roles(
user_id bigint not null,
roles varchar not null,
unique (user_id, roles)
)engine=InnoDB;


create table rate(
id bigint not null auto_increment,
ccy varchar not null,
base_ccy varchar not null,
buy double not null,
sale double not null,
version int not null default 0,
primary key (id)
)engine=InnoDB;

alter table user_roles
add constraint roles foreign key (user_id) references usr (id);