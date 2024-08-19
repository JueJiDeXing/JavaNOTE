/*
作用:将一列数据作为一个整体,进行纵向计算
# 所有的null值不参与聚合函数运算
# where中不使用聚合函数, 在having中使用

常见聚合函数:
count 统计数量
max   最大值
min   最小值
avg   平均值
sum   求和

语法:
select 函数名(字段列表) from 表名;

例:
select count(idcard) from user;  # 对于 count(*),统计的是总数据行数(包括全null行)
select sum(age) from user where gender='女'; # 统计所有女性年龄之和

count(*)、count(1)、count(字段) 统计行数的效率:
如果是 MySQl MyISAM 存储引擎, 每张表有meta信息存储了行数值, 效率均为O(1)
如果是 InnoDB 存储引擎, count(*)和count(1)会自动优化找key_len小的二级索引扫描, 效率count(*)=count(1)>count(字段)
*/
