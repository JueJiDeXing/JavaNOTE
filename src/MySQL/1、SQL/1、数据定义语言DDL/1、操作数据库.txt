SQL语言以分号结尾为一句,不区分大小写(关键字行业标准大写)
可以使用缩进来增强可读性
-- 或 # 单行注释(MySQL特有)
#-----------------------------------------------

SHOW DATABASES; # 查询所有数据库
SELECT DATABASE(); #查询当前数据库
CREATE DATABASE [ IF NOT EXISTS ] 数据库名 [ DEFAULT CHARSET 字符集 ] [ COLLATE 排序规则];#创建
DROP DATABASE [ IF EXISTS ] 数据库名; # 删除
USE 数据库名; # 使用

# []表示可选参数

#----------------------------------------------
例:
#展示数据库
mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |# 这四条是一开始就存在的
| mysql              |
| performance_schema |
| sys                |
+--------------------+
4 rows in set (0.01 sec)

# 创建
mysql> create database itachi if not exists test default charset utf8mb4;# utf-8字符编码不支持4字节,所以mb4
Query OK, 1 row affected (0.01 sec)

mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| itachi             |
| mysql              |
| performance_schema |
| sys                |
+--------------------+
5 rows in set (0.00 sec)

# 删除
mysql> drop database if exists itachi; # 如果存在则删除
Query OK, 0 rows affected (0.01 sec)

# 使用
mysql> use itachi;
Database changed
mysql> select database();
+------------+
| database() |
+------------+
| itachi     |
+------------+
1 row in set (0.00 sec)
