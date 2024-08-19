# 创建[唯一]索引
creat [unique] index 索引名 on 表名(字段名,...)# 主键与唯一约束的字段会自动创建索引

alter table 表名 add index 索引名 索引类型
# 查看索引
show index from 表名
# 删除索引
drop index 索引名 on 表名 # 主键索引不能删除


