# Create RibbonSystem database structure;
# Data base structure version = 3.0;

#RIBBON USER CREATE
CREATE USER 'ribbon'@'localhost' IDENTIFIED BY '9ARb@Pas$';

#RIBBON DATABASE CREATE
CREATE DATABASE `ribbon` CHARACTER SET utf8 COLLATE utf8_general_ci;

#GRANT RIGHTS TO DB USER
GRANT ALL ON ribbon.* TO 'ribbon'@'localhost';