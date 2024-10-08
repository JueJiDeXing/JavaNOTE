/*
select 字段列表 from 表名 where 条件列表;

# 条件
>大于     <小于     =等于
>=大于等于  <=小于等于
<> 或 != 不等于
between...and... 在某个范围内,全闭区间,写反了则查询不到数据
in(...) 在in之后的列表中的值,多选一
like 占位符,模糊匹配( %:表示任意字符的任意长度（包括0个字符）_:表示任意单个字符 [characters]:表示在方括号中列出的任意单个字符)
is null 属性为null

and 或 && 并且
or 或 || 或者
not 或 ! 非

例:
select * from user where age in(20,21,22); # 匹配年龄为20,21或22的用户
select * from user where name like '__'; # 匹配姓名两个字的用户
select * from user where idcard like '%X'; # 匹配身份证最后一位为X的用户
*/
