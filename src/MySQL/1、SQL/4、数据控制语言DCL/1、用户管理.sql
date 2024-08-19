进入mysql-表-user可查看所有用户
# 命令行登录命令:mysql -u 绝迹的星 -p

# 查询用户
use mysql;
select * from user;
# 创建用户
create user '用户名'@'主机名' identified by '密码'; # 默认无权限用户,登录该用户不能查看表
# 修改用户密码
alter user '用户名'@'主机名' identified mysql_native_password with '新密码'
# 删除用户
drop user '用户名'@'主机名';

例:
create user '绝迹的星'@'localhost' identified by '123456'; # 可在主机上以123456密码登录,访问数据库 # localhost为当前主机
create user '绝迹的星'@'%' identified by '123456'; # 可在所有设备上以123456密码登录