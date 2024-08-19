-- 查看user和权限
SELECT *
FROM mysql.user;


#  创建用户
-- %表示可以在任意主机上访问, localhost表示只能在本地访问
-- by 后面为密码
CREATE USER 'testuser'@'%' IDENTIFIED BY '123456';


#  给用户分配权限
CREATE DATABASE test_db;
use test_db;
CREATE TABLE test_table
(
    id   INT,
    name VARCHAR(20),
    age  INT,
    sex  VARCHAR(10)
);
GRANT SELECT, INSERT, UPDATE, DELETE ON test_table.* TO 'testuser'@'%';




#  创建角色
CREATE ROLE testrole;

-- 为角色分配权限
GRANT SELECT, INSERT, UPDATE, DELETE ON test_table TO testrole;


-- 将角色分配给用户
CREATE USER `testuser2`@`%` IDENTIFIED BY '123456';
GRANT testrole TO testuser2;
