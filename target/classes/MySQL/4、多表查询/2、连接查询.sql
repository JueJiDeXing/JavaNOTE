/*
select * from user,user_info where user.id=user_info.id;
# 多表查询,查询结果为这多张表的笛卡尔积,需要加入外键判断以消除无效笛卡尔积

内连接:查询A,B集合的交集部分
左外连接:查询左表数据
右外连接:查询右表数据
自连接:与自身连接查询(必须使用表别名)

语法:
# 隐式内连接
select 字段列表 from 表1,表2 where 条件...;
# 显示内连接
select 字段列表 from 表1 [inner] join 表2 on 条件...;
# 左外连接
select 字段列表 from 表1 left [outer] join 表2 on 条件...; # 只保留表1
# 右外连接
select 字段列表 from 表1 right [outer] join 表2 on 条件...; # 只保留表2
# 满外连接 ( mysql不支持 )
select 字段列表 from 表1 full [outer] join 表2 on 条件...; # 只保留表2
# 自然连接
select 字段列表 from 表1 natural [outer] join 表2; # 自动查询所有相同字段进行等值连接
# Using 连接
select 字段列表 from 表1 join 表2 using(字段名); # 指定同名字段进行等值连接

select a.name '员工', b.name '领导' from emp a join emp b on a.manage_id=b.id;
# 根据员工的领导id 与领导id进行匹配,查询员工-领导关系表
select a.name '员工', b.name '领导' from emp a left join emp b on a.manage_id=b.id;
# 优化,使用外连接,将为null的数据保留(该员工就是最大的领导)


*/
