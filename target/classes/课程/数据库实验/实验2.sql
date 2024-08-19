use S_T;

/* 单表查询 */

-- 1. 按学号查询学生信息
SELECT *
FROM Student
WHERE Sno = '201212122';

-- 2. 按姓名查询学生信息
Insert Student (Sno, Sname, Ssex, Sage, Sdept)
values ('202400001', '张三', '男', 19, 'CS'),
       ('202400002', '张三', '男', 20, 'MA');

SELECT *
FROM Student
WHERE Sname = '张三';

/* 分组统计查询 */

-- 1. 查询每个系的学生人数
SELECT Sdept, COUNT(*) AS '学生人数'
FROM student
GROUP BY Sdept;

-- 2. 查询每个系的男生人数和女生人数
SELECT Sdept, Ssex, COUNT(*) AS '人数'
From student
GROUP BY Sdept, Ssex;

/* 连接查询 */

-- 1. 查询选修了某课程的学生信息
SELECT student.Sno as "Sno", Sname, Ssex, Sage, Sdept, Cno, Grade
FROM student
         JOIN sc
WHERE sc.Cno = 2
  AND sc.Sno = student.Sno;

-- 2. 查询选修了4学分的课程的学生
SELECT student.Sno as "学号",
       Sname       as "姓名",
       Ssex        as "性别",
       Sage        as "年龄",
       Sdept       as "院系",
       sc.Cno      as "课程id",
       Cname       as "课程名称",
       Grade       as "课程成绩",
       Ccredit     as "课程学分"
FROM student
         JOIN sc,
     course
Where Ccredit = 4
  AND sc.Cno = course.Cno
  AND sc.Sno = student.Sno;

/* 嵌套查询 */

-- 1. 查询选修了2号课程的学生
SELECT *
FROM student
WHERE Sno IN (SELECT Sno FROM sc WHERE Cno = 2);

-- 2. 查询选修了"数据库"的学生信息
SELECT *
FROM student
WHERE Sno IN (SELECT Sno
              FROM sc
              WHERE Cno = (SELECT Cno FROM course WHERE Cname = '数据库'));

/* 集合查询 */

-- 1. 查询选修了2号课程,但没有选修1号课程的学生
SELECT *
FROM student
where Sno IN (with A as (SELECT Sno FROM sc WHERE Cno = 2), -- 分别查询选修1和2的学生,然后取差集
                   B as (SELECT Sno FROM sc WHERE Cno = 1)
              SELECT DISTINCT A.Sno
              FROM A
                       RIGHT JOIN B ON A.Sno NOT IN (SELECT Sno FROM B));
-- 2. 查询选修了"数据库"的男生,或选修了"数学"的女生
SELECT *
FROM student
where Sno IN ((SELECT Sno FROM sc WHERE Cno = (SELECT Cno FROM course WHERE Cname = '数据库'))
              UNION
              (SELECT Sno FROM sc WHERE Cno = (SELECT Cno FROM course WHERE Cname = '数学')));
