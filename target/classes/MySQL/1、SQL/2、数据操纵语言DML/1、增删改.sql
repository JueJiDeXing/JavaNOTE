insert 添加
update 修改
delete 删除

insert into 表名(字段1,字段2...) Values(值1,值2...); # 给指定字段添加数据
insert into 表名 Values(值1,值2...); # 给所有字段赋值
insert into 表名(字段1,字段2...) Values(值1,值2...),(值1,值2...),...; # 给指定字段批量添加数据
insert into 表名 Values(值1,值2...),(值1,值2...),...; # 给所有字段批量添加数据

update 表名 set 字段名1=值1,字段名2=值2,[where 条件]; # 修改字段值,不加条件则对所有数据进行修改
delete from 表名 [where 条件]; # 根据添加删除字段数据,不加添加则删除表的所有数据

例:
delete from tb_user where gender='男'; # 删除所有男性用户








