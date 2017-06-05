<<<<<<< HEAD
use MDB;

-- ÓÃ»§µÇÂ¼ÐÅÏ¢±í
create table User
(
    LoginID varchar(20),
    PassWord varchar(20),
    Authoritiy int
);

insert into User values('admin','admin',0);
insert into User values('teacher','teacher',1);
insert into User values('student','student',2);

-- ½ÌÊ¦±í
create table Teachers
(   
    Tid varchar(20),
    Tname varchar(20),
    Tage numeric(3),
    Tsex char(2),
    Taca varchar(20),
    Tdepart varchar(20),
    constraint pk_name primary key (Tid)
);

insert into Teachers values('T20140801','ÕÅÈý', 50,'ÄÐ','ÐÅÏ¢Ôº','CS');

insert into Teachers values('T20140802','ÀîËÄ', 47,'ÄÐ','ÐÅÏ¢Ôº','IS');

insert into Teachers values('T20140803','ÍõÎå', 45,'ÄÐ','ÐÅÏ¢Ôº','CS');

-- Ñ§Éú±í
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

insert into Students values('2014080101','»ÆÔóÔ£',21,'ÄÐ','ÐÅÏ¢Ôº','CS');

insert into Students values('2014080102','ÇØ´¨',20,'ÄÐ','ÐÅÏ¢Ôº','IS');

insert into Students values('2014080103','Àîæ©ÄÝ',20,'Å®','ÐÅÏ¢Ôº','CS');

-- ¿Î³ÌÐÅÏ¢±í
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

insert into Courses values('Êý¾Ý¿âÔ­Àí','0801', '2017S', 'CS', 'T20140801');

insert into Courses values('ÐÅÏ¢°²È«Ô­Àí','0802', '2017S', 'IS', 'T20140802');

insert into Courses values('¼ÆËã»úÍøÂç','0803', '2017S', 'CS', 'T20140803');

-- Ñ§ÉúÑ¡¿Î±íSC
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

-- ¿Î³ÌÊ±¼ä±íCT
create table CT
(
    Cid varchar(20),
    Term varchar(20),
    Ctime varchar(20),
    Cplace varchar(20),
    constraint pk_name primary key (Cid, Term),
    foreign key(Cid) references Courses(Cid)
);

insert into CT values('0801', '2017S', 'Ã¿ÖÜÒ»/ÖÜÈý-8:00','ÖÐ206');

insert into CT values('0802', '2017S', 'Ã¿ÖÜÒ»/ÖÜÈý-14:30','¸´401');

insert into CT values('0803', '2017S', 'Ã¿ÖÜ¶þ/ÖÜËÄ-8:00','ÖÐ206');

-- Ñ§ÉúÑ§ÆÚ±íStudentTerm
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

=======
use MDB;

-- ç”¨æˆ·ç™»å½•ä¿¡æ¯è¡¨
create table User
(
    LoginID varchar(20),
    PassWord varchar(20),
    Authoritiy int
);

insert into User values('admin','admin',0);
insert into User values('teacher','teacher',1);
insert into User values('student','student',2);

-- æ•™å¸ˆè¡¨
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

insert into Teachers values('T20140801','å¼ ä¸‰', 50,'ç”·','ä¿¡æ¯é™¢','CS');

insert into Teachers values('T20140802','æŽå››', 47,'ç”·','ä¿¡æ¯é™¢','IS');

insert into Teachers values('T20140803','çŽ‹äº”', 45,'ç”·','ä¿¡æ¯é™¢','CS');

-- å­¦ç”Ÿè¡¨
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

insert into Students values('2014080101','é»„æ³½è£•',21,'ç”·','ä¿¡æ¯é™¢','CS');

insert into Students values('2014080102','ç§¦å·',20,'ç”·','ä¿¡æ¯é™¢','IS');

insert into Students values('2014080103','æŽå§—å¦®',20,'å¥³','ä¿¡æ¯é™¢','CS');

-- è¯¾ç¨‹ä¿¡æ¯è¡¨
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

insert into Courses values('æ•°æ®åº“åŽŸç†','0801', '2017S', 'CS', 'T20140801');

insert into Courses values('ä¿¡æ¯å®‰å…¨åŽŸç†','0802', '2017S', 'IS', 'T20140802');

insert into Courses values('è®¡ç®—æœºç½‘ç»œ','0803', '2017S', 'CS', 'T20140803');

-- å­¦ç”Ÿé€‰è¯¾è¡¨SC
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

-- è¯¾ç¨‹æ—¶é—´è¡¨CT
create table CT
(
    Cid varchar(20),
    Term varchar(20),
    Ctime varchar(20),
    Cplace varchar(20),
    constraint pk_name primary key (Cid, Term),
    foreign key(Cid) references Courses(Cid)
);

insert into CT values('0801', '2017S', 'æ¯å‘¨ä¸€/å‘¨ä¸‰-8:00','ä¸­206');

insert into CT values('0802', '2017S', 'æ¯å‘¨ä¸€/å‘¨ä¸‰-14:30','å¤401');

insert into CT values('0803', '2017S', 'æ¯å‘¨äºŒ/å‘¨å››-8:00','ä¸­206');

-- å­¦ç”Ÿå­¦æœŸè¡¨StudentTerm
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

>>>>>>> branch 'master' of https://github.com/TooSchoolForCool/Educational-Administration-System.git
insert into ST values('2014080103', '2017S', 'T');