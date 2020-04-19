create table t_coffee (
    id bigint not null auto_increment,
    name varchar(255),
    account DECIMAL not null,
    currency  varchar(255) not null,
    create_time timestamp,
    update_time timestamp,
    primary key (id)
);
