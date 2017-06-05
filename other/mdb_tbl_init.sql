use MDB;

-- �û���¼��Ϣ��
create table User
(
    LoginID varchar(20),
    PassWord varchar(20),
    Authoritiy int
);

insert into User values('admin','admin',0);
insert into User values('teacher','teacher',1);
insert into User values('student','student',2);

-- ��ʦ��
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

insert into Teachers values('T20140801','����', 50,'��','��ϢԺ','CS');

insert into Teachers values('T20140802','����', 47,'��','��ϢԺ','IS');

insert into Teachers values('T20140803','����', 45,'��','��ϢԺ','CS');

-- ѧ����
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

insert into Students values('2014080101','����ԣ',21,'��','��ϢԺ','CS');

insert into Students values('2014080102','�ش�',20,'��','��ϢԺ','IS');

insert into Students values('2014080103','�����',20,'Ů','��ϢԺ','CS');

-- �γ���Ϣ��
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

insert into Courses values('���ݿ�ԭ��','0801', '2017S', 'CS', 'T20140801');

insert into Courses values('��Ϣ��ȫԭ��','0802', '2017S', 'IS', 'T20140802');

insert into Courses values('���������','0803', '2017S', 'CS', 'T20140803');

-- ѧ��ѡ�α�SC
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

-- �γ�ʱ���CT
create table CT
(
    Cid varchar(20),
    Term varchar(20),
    Ctime varchar(20),
    Cplace varchar(20),
    constraint pk_name primary key (Cid, Term),
    foreign key(Cid) references Courses(Cid)
);

insert into CT values('0801', '2017S', 'ÿ��һ/����-8:00','��206');

insert into CT values('0802', '2017S', 'ÿ��һ/����-14:30','��401');

insert into CT values('0803', '2017S', 'ÿ�ܶ�/����-8:00','��206');

-- ѧ��ѧ�ڱ�StudentTerm
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