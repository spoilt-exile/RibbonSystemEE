# Create RibbonSystem database structure;
# Data base structure version = 2.1;

#RIBBON USER CREATE
CREATE USER 'ribbon'@'localhost' IDENTIFIED BY '9ARb@Pas$';

#RIBBON DATABASE CREATE
CREATE DATABASE `ribbon` CHARACTER SET utf8 COLLATE utf8_general_ci;

#GRANT RIGHTS TO DB USER
GRANT ALL ON ribbon.* TO 'ribbon'@'localhost';

#CHANGE DB;
USE ribbon;

#USER TABLE
CREATE TABLE User (
id		int AUTO_INCREMENT not null,
login		varchar(255) not null,
description	varchar(300),
passw		varchar(255) not null,
crt_date	timestamp not null,
log_date	timestamp not null,
is_admin	boolean not null,
is_enabled	boolean not null,
is_active	boolean not null,
PRIMARY KEY (id),
UNIQUE (login)
);

#GROUP TABLE
CREATE TABLE Groups (
id		int AUTO_INCREMENT not null,
name		varchar(255) not null,
description	varchar(300),
PRIMARY KEY (id),
UNIQUE (name)
);

#GROUP AND USER RELATION TABLE (MANY-TO-MANY)
CREATE TABLE UserGroupsRel (
id		int AUTO_INCREMENT not null,
user_id		int not null,
group_id	int not null,
is_enabled	boolean not null,
PRIMARY KEY (id),
FOREIGN KEY (user_id) REFERENCES User(id),
FOREIGN KEY (group_id) REFERENCES Groups(id)
);

#MESSAGE DIRECTIRY TABLE
CREATE TABLE Directory (
id		int AUTO_INCREMENT not null,
path		text(600) not null,
description	varchar(300),
is_hidden	boolean not null,
PRIMARY KEY (id)
);

#PERMISSION ENTRIES TABLE
CREATE TABLE Permission (
id		int AUTO_INCREMENT not null,
dir_id		int not null,
group_perm	boolean not null,
all_perm	boolean,
user_id		int,
group_id	int,
may_read	boolean not null,
may_post	boolean not null,
may_admin	boolean not null,
PRIMARY KEY (id),
FOREIGN KEY (user_id) REFERENCES User(id),
FOREIGN KEY (group_id) REFERENCES Groups(id)
);

#MESSAGE TABLE
CREATE TABLE Message (
id		int AUTO_INCREMENT not null,
header		text(500) not null,
dir_id		int not null,
post_date	timestamp not null,
auth_id		int not null,
is_urgent	boolean not null,
body		mediumtext not null,
PRIMARY KEY (id),
FOREIGN KEY (auth_id) REFERENCES User(id),
FOREIGN KEY (dir_id) REFERENCES Directory(id)
);