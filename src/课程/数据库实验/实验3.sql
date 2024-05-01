use s_t;

# (1) 元组插入
-- 插入一个学生元组
INSERT INTO student (Sno, Sname, Ssex, Sage, Sdept)
VALUES (1001, '张三', '男', 21, 'CS');

# (2) 基于子查询的插入
-- 从学生表中筛选数据插入到新表
CREATE TABLE select_student
(
    Sno   int         not null,
    constraint Sno primary key (Sno),
    Sname varchar(10) not null,
    Ssex  varchar(2)  not null,
    Sage  int         not null,
    Sdept varchar(20) not null
);
Insert Into select_student
SELECT *
FROM student
WHERE Sno IN (SELECT Sno
              FROM sc
              WHERE Cno = 2);
# (3) 元组修改
-- 将学生表中Sno为1001的Sname改为'李四'
UPDATE student
SET Sname = '李四'
WHERE Sno = 1001;

















# (4) 基于子查询的修改
# (5) 元组删除
# (6) 基于子查询的删除
