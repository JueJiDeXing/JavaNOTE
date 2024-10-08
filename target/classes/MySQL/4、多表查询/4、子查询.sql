# 嵌套查询

# 语法:
# select *
# from t1
# where column1 = (select column1 from t2);
# 子查询外部的语句可以是select,insert,update,delete
# 除 group by 和 limit 之外的部分都可以写子查询
/*
分类:
标量子查询(子查询结果为单个值)
列子查询(子查询结果为一列)
行子查询(子查询结果为一行)
表子查询(子查询结果为多行多列)
*/


# 例:
# 标量子查询-------常用< , > , <= , >= 等比较符
select *
from emp
where dept_id = (select id from dept where name = '销售部');
# 查询销售部所有员工信息(查询部门id->查询员工信息)

select *
from emp
where entrydate > (select entrydate from emp where name = '员工甲');
# 查询指定日期后入职的员工(查询员工甲的入职时间->查询大于这个日期的员工信息)


# 列子查询--------常用in , not in , any , some , all 等操作符 (其中any与some一样,都表示满足一个规则)
select *
from emp
where dept_id in (select id from dept where name = '销售部' or name = '市场部');
# 查询销售部和市场部的所有员工(部门id->员工信息)


select *
from emp
where emp.salary >
          all (select salary
               from emp
               where emp.dept_id = (select id from dept where name = '财务部'));
# 查询比财务部所有人工资都高的员工(部门id->财务部员工工资->比财务部所有员工工资高的员工)
# 使用any或some则表示比其中一人工资高(即大于财务部最低工资)

# 行子查询-------常用= , <> , in ,not in 等
select *
from emp
where (salary, managerid) = (select salary, managerid from emp where name = '员工甲');
# 查询与员工甲的工资相同,且领导也相同的员工(员工甲工资及领导->员工)

# 表子查询-------常用in 等
select *
from emp
where (job, salary) in
      (select job, salary from emp where name = '员工甲' or name = '员工乙');
# 查询与员工甲或员工乙的职位相同,且工资也相同的员工(员工甲,员工乙职位和工资->员工)

select e.*, d.*
from (select * from emp where entrydate > '2006-01-01') e
         left join dept d
                   on e.dept_id = d.id;
#查询2006-01-01后入职的员工及其部门信息(指定时间后的临时表->员工与部门)


# 例:
select d.id, d.name, (select count(*) from emp e where e.dept_id = d.id) '人数'
from dept d;
# 查询所有部门信息,并统计部门员工人数

