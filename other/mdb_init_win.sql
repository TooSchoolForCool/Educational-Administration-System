-- 原用户登陆，创建新用户
create user mdbuser@localhost identified by 'mdbuser';
grant all privileges on MDB.* to 'mdbuser'@'localhost';

-- 创建数据库
create database MDB;