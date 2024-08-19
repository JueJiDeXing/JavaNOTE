show tables; #查询当前数据库所有表
desc 表名; # 查询表结构(展示一张表存储的信息,如姓名,年龄等)
show create table 表名; # 查询指定表的建表语句

create table 表名(
字段1 字段1类型[ comment 字段1注释],
字段2 字段2类型[ comment 字段2注释],
...
字段n 字段n类型[ comment 字段n注释]
)[comment 表注释]; # 创建表

alter table 表名 add 字段名 类型 [comment 注释] [约束]; # 向表中添加字段
alter table 表名 modify  字段名 新类型; # 修改字段的数据类型
alter table 表名 change  旧字段名 新字段名 新类型 [comment 注释] [约束]; # 修改字段名和字段类型
alter table 表名 drop 字段名; # 删除字段

alter table 旧表名 rename to 新表名; # 修改表名
drop table[if exists] 表名; # 删除表
truncate table 表名; # 删除并重新创建(清空表中所有字段)

select * from 表名; # 查询表中字段存储的信息
#-----------------------------------------------------------------
例:
# 创建表
mysql> create table tb_user(
-> id int comment '编号',
-> name varchar(50) comment '姓名',
-> age int comment '年龄'
-> ) comment '用户表';
Query OK, 0 rows affected (0.03 sec)

# 查询表
mysql> show tables;
+------------------+
| Tables_in_itachi |
+------------------+
| tb_user          |
+------------------+
1 row in set (0.00 sec)

#查询表结构
mysql> desc tb_user;
+-------+-------------+------+-----+---------+-------+
| Field | Type        | Null | Key | Default | Extra |
+-------+-------------+------+-----+---------+-------+
| id    | int         | YES  |     | NULL    |       |
| name  | varchar(50) | YES  |     | NULL    |       |
| age   | int         | YES  |     | NULL    |       |
+-------+-------------+------+-----+---------+-------+
3 rows in set (0.00 sec)

# 查询建表语句
mysql> show create table tb_user;
+---------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Table   | Create Table

       |
+---------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| tb_user | CREATE TABLE `tb_user` (
`id` int DEFAULT NULL COMMENT '编号',
`name` varchar(50) DEFAULT NULL COMMENT '姓名',
`age` int DEFAULT NULL COMMENT '年龄'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表' |
+---------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
1 row in set (0.00 sec)  # ENGINE=InnoDB创建表使用的引擎,CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci字符集与排序规则(默认)
