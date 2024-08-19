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
-- 将学生表中Sno为1001的name改为'李四',age改为22
UPDATE student
SET Sname = '李四',
    Sage=22
WHERE Sno = 1001;


# (4) 基于子查询的修改
Insert Into sc (Sno, Cno, Grade)
values (1001, 2, 50);
-- 将有不及格学生的课程学分下调1分
UPDATE course
SET Ccredit = Ccredit - 1
WHERE Cno IN (SELECT Cno FROM sc WHERE Grade < 60);


# (5) 元组删除
-- 删除学号为1001的学生的选课信息
DELETE
FROM sc
WHERE Sno = '1001';


# (6) 基于子查询的删除
-- 将专业为CS的学生选的课程删除
DELETE
FROM sc
where Sno IN
      (SELECT Sno FROM student where Sdept = 'CS');


