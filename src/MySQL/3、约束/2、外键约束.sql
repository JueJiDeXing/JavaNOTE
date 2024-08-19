<!-- 行业标准:禁止使用外键 -->


# 当一个表的键关联另一个表的键时,使用外键方式

creat table xxx(
    ...,
    [constraint] [外键名称] foreign key(外键字段名) references 主表(主表列名)
);
# 添加外键
alter table 表名 add constraint 外键名称 foreign key(外键字段名)
  references 主表(主表列名) [on update 更新行为] [on delete 删除行为]


# 外键约束的删除/更新行为
    当在父表中删除/更新对应数据时,首先检查是否有对应外键:
no action    如果有外键,则不允许父表删除/更新
restrict     与no action一致
cascade      如果有外键,则一并删除/更新子表对应的数据
set null     如果有外键,则将子表的数据设为null(需要允许为null)
set default  将子表设为默认值(innodb不支持)
