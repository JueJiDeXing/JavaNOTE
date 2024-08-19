# 练习六
-- 定义职工和部门关系模式
CREATE SCHEMA 公司;
use 公司;
CREATE TABLE 职工
(
    职工号 INT PRIMARY KEY, # 定义主码
    姓名   VARCHAR(255),
    年龄   INT,
    职务   VARCHAR(255),
    工资   DECIMAL(10, 2),
    部门号 INT
);

CREATE TABLE 部门
(
    部门号 INT PRIMARY KEY,# 定义主码
    名称   VARCHAR(255),
    经理名 VARCHAR(255),
    电话   VARCHAR(255)
);
-- 定义参照完整性
ALTER TABLE 职工
    ADD CONSTRAINT 部门号 FOREIGN KEY (部门号) REFERENCES 部门 (部门号);
-- 定义年龄限制约束
ALTER TABLE 职工
    ADD CONSTRAINT 年龄限制 CHECK (年龄 <= 60);



# 练习七
-- 插入一条主码缺失的职工记录
INSERT INTO 职工 (姓名, 年龄, 职务, 工资, 部门号)
VALUES ('张三', 30, '员工', 5000, 1);

-- 插入一条年龄超过60岁的职工记录
INSERT INTO 职工 (职工号, 姓名, 年龄, 职务, 工资, 部门号)
VALUES (1, '张三', 65, '经理', 10000, 1);


