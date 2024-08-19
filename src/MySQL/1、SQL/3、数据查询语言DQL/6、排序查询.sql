/*
语法:
select 字段列表 from 表名 order by 字段1 排序方式1,字段2 排序方式2,...;
# 当第一个字段排序相同时,根据第二个字段排序

排序方式:
asc:升序(默认,可省略)
desc:降序

例:
select * from user order by age asc ,datatime asc; # 根据年龄升序,如果年龄相同,根据时间升序

# 排序中可嵌套子查询, 根据子查询表进行排序
select id,salary
form emp
order by (
    select dept.name from dept
    where emp.dept_id = dept.id
) ASC;
# 根据部门名称对员工信息排序
*/
