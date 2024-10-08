/*
if(value,t,f)   如果value为true,返回t,否则返回f
ifnull(v1,v2)   如果v1非空,返回v1,否则返回v2

case when [v1] then [res1]...else [default] end
如果v1为true,则返回res1...如果都为false,则返回default默认值 [相当于 if...else if...else...]

case [expr] when [v1] then [res1]...else [default] end
如果expr的值等于v1,则返回res1...,否则返回default默认值 [相当于 switch...case...]

例:
select
    name,
    (case address when '北京' then '一线城市' else '二线城市' end) as '地址'
from user;
# 把地址为北京的使用一线城市展示,其余用二线城市展示

select
    name,
    (case when math>=90 then '优秀' when math>=60 then '及格' else '不及格' end) as '数学',
    (case when english>=90 then '优秀' when english>=60 then '及格' else '不及格' end) as '英语',
    (case when chinese>=90 then '优秀' when chinese>=60 then '及格' else '不及格' end) as '语文'
from score;
# 把成绩以及格制代替分数制展示

*/
