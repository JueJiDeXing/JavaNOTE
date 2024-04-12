-- 创建学生-课程模式
CREATE SCHEMA S_T;

use S_T;

-- 创建学生表
# 学号(主键),姓名,性别,年龄,所在系
Create Table Student
(
    Sno   int         not null,
    constraint Sno primary key (Sno),
    Sname varchar(10) not null,
    Ssex  varchar(2)  not null,
    Sage  int         not null,
    Sdept varchar(20) not null
);
-- 创建课程表
# 课程号(主键),课程名,先行课,学分
Create Table Course
(
    Cno     int         not null,
    constraint Cno primary key (Cno),
    Cname   varchar(50) not null,
    Cpno    int         null,
    Ccredit int         not null
);
-- 创建学生选课表
# 学号(主键),课程号,成绩
Create Table SC
(
    Sno   int not null,
    constraint Sno primary key (Sno),
    Cno   int not null,
    Grade int not null
);
-- 插入数据
insert into Student
values (201215121, '李勇', '男', 20, 'CS'),
       (201212122, '刘晨', '女', 19, 'CS'),
       (201212123, '王敏', '女', 18, 'MA'),
       (201212125, '张立', '男', 19, 'IS')
;

insert into Course
values (1, '数据库', 5, 4),
       (2, '数学', null, 2),
       (3, '信息系统', 1, 4),
       (4, '操作系统', 6, 3),
       (5, '数据结构', 7, 4),
       (6, '数据处理', null, 2),
       (7, 'PASCAL语言', 6, 4)
;

alter table sc
    drop primary key;

alter table sc
    add primary key (Sno, Cno);


insert into SC
values (201215121, 1, 92),
       (201215121, 2, 85),
       (201215121, 3, 88),
       (201212122, 2, 90),
       (201212122, 3, 80)
;
Create Table AAA
(
    A int  not null,
    constraint A primary key (A),
    B int  not null,
    E date not null
);
