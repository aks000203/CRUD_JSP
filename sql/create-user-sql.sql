create database demo;

use demo;

create table users(
    id int(3) NOT NULL AUTO_INCREMENT,
    name varchar(40) not null,
    email varchar(50) not null,
    country varchar(50),
    primary key(id)
)