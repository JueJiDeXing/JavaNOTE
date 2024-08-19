权限                   说明
all,all privileges   所有权限
select               查询数据
insert               插入数据
update               修改数据
delete               删除数据
alter                修改表
drop                 删除数据库/表/视图
create               创建数据库/表

语法:
# 查询权限
show grants for '用户名'@'主机名';
# 授予权限
grant 权限列表 on 数据库名.表名 to '用户名'@'主机名'; # 如果是所有数据库所有表,则写*.*
# 撤销权限
revoke 权限列表 on 数据库名.表名 from '用户名'@'主机名';