约束是作用于字段上的规则,用于限制存储在表中的数据
# 可使用多个关键字约束同一个字段

分类:
非空约束   not null     数据不能为null
唯一约束   unique       数据是唯一的,不能重复
主键约束   primary key  主键是一行数据的唯一标识,要求非空且唯一
默认约束   default      保存数据时,如果未指定值,则使用默认值
检查约束   check        保证字段满足某一个条件
外键约束   foreign key  将两张表之间建立连接,保证数据一致完整



例:
create table user(
    id int primary key auto_increment comment '主键', # auto_increment 自动增长
    name varchar(10) not null unique comment '姓名',
    age check (0<=age&&age<=120) comment '年龄'
) comment '用户表';