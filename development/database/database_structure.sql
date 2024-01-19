drop database manuales;
create database manuales;
use manuales;

create table language (
    id int not null auto_increment primary key,
    name varchar(10) not null,
    icon varchar(255) not null,
    description varchar(255)
);

create table dictionary (
    id int not null auto_increment primary key,
    language_id int not null,
    keyword varchar(255) not null,
    value longtext,
    foreign key (language_id) references language(id)
);

create table project (
    id int not null auto_increment primary key,
    icon varchar(255),
    name_keyword varchar(255) not null,
    about_keyword varchar(255) not null,
    content_keyword varchar(255) not null,
    visible_code varchar(255) -- null = visible for all || <code> = visible for ?code=<code>
);

create table section (
    id int not null auto_increment primary key,
    name_keyword varchar(255) not null,
    description_keyword varchar(255) not null,
    content_keyword varchar(255) not null,
    metadata_keyword varchar(255) not null,
    project_id int not null,
    foreign key (project_id) references project(id)
);

create table users (
    id int not null auto_increment primary key,
    username varchar(255) not null,
    password varchar(255) not null
);

create table keyword_temp (
    id int not null auto_increment primary key,
    keyword varchar(255)
);


# =============================================================================
# =============================================================================
# =============================================================================

insert into users (id, username, password) values (1, 'cristina', 'safety');

insert into keyword_temp (keyword) values ('read_more');
insert into keyword_temp (keyword) values ('empty_result');
insert into keyword_temp (keyword) values ('close');
insert into keyword_temp (keyword) values ('login');
insert into keyword_temp (keyword) values ('home');
insert into keyword_temp (keyword) values ('search');
insert into keyword_temp (keyword) values ('username');
insert into keyword_temp (keyword) values ('password');