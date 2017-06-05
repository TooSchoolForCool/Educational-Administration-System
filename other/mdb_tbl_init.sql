use MDB;

-- 用户登录信息表
create table User
(
    LoginID varchar(20),
    PassWord varchar(20),
    Authoritiy int
);

insert into User values('admin','admin',0);
insert into User values('teacher','teacher',1);
insert into User values('student','student',2);

-- 教师表
create table Teachers
(   
    Tid varchar(20),
    Tname varchar(20),
    Tage numeric(3),
    Tsex char(10),
    Taca varchar(20),
    Tdepart varchar(20),
    constraint pk_name primary key (Tid)
);

insert into Teachers values('T20140801','张三', 50,'男','信息院','CS');

insert into Teachers values('T20140802','李四', 47,'男','信息院','IS');

insert into Teachers values('T20140803','王五', 45,'男','信息院','CS');

-- 学生表
create table Students
(   
    Sid varchar(20),
    Sname varchar(20),
    Sage numeric(3),
    Ssex char(10),
    Taca varchar(20),
    Tdepart varchar(20),
    constraint pk_name primary key (Sid)
);

insert into Students values('2014080101','黄泽裕',21,'男','信息院','CS');

insert into Students values('2014080102','秦川',20,'男','信息院','IS');

insert into Students values('2014080103','李姗妮',20,'女','信息院','CS');

-- 课程信息表
create table Courses
(
    Cname varchar(40),
    Cid varchar(20),
    Term varchar(20),
    Cdepart varchar(20),
    Tid varchar(20),
    constraint pk_name primary key (Cid, Term),
    foreign key(Tid) references Teachers(Tid)
);

insert into Courses values('数据库原理','0801', '2017S', 'CS', 'T20140801');

insert into Courses values('信息安全原理','0802', '2017S', 'IS', 'T20140802');

insert into Courses values('计算机网络','0803', '2017S', 'CS', 'T20140803');

-- 学生选课表SC
create table SC
(   
    Sid varchar(20),
    Cid varchar(20),
    Term varchar(20),
    Grade Integer,
    constraint pk_name primary key (Sid, Cid, Term),
    foreign key(Cid) references Courses(Cid),
    foreign key(Sid) references Students(Sid)
);

insert into SC values('2014080101','0801','2017S', NULL);

insert into SC values('2014080101','0802','2017S', NULL);

insert into SC values('2014080102','0802','2017S', NULL);

insert into SC values('2014080103','0803','2017S', NULL);

-- 课程时间表CT
create table CT
(
    Cid varchar(20),
    Term varchar(20),
    Ctime varchar(20),
    Cplace varchar(20),
    constraint pk_name primary key (Cid, Term),
    foreign key(Cid) references Courses(Cid)
);

insert into CT values('0801', '2017S', '每周一/周三-8:00','中206');

insert into CT values('0802', '2017S', '每周一/周三-14:30','复401');

insert into CT values('0803', '2017S', '每周二/周四-8:00','中206');

-- 学生学期表StudentTerm
create table ST
(
    Sid varchar(20),
    Term varchar(20),
    Ishere char(1),
    constraint pk_name primary key (Sid, Term),
    foreign key(Sid) references Students(Sid)
);

insert into ST values('2014080101', '2017S', 'T');

insert into ST values('2014080102', '2017S', 'T');

insert into ST values('2014080103', '2017S', 'T');