package 数据库;

import java.sql.*;

public class jdbc事务回滚 {
    //PreparedStatement接口继承于Statement接口,用于执行String sql语句
    public static void main(String[] args) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "Mx18397887506");
        //参1:数据库的url,其中localhost表主机地址,3306为MySQL默认端口号,user为创建的数据库
        //参2:登录的账户 ; 参3:登录用的密码

        noTransaction(conn);//事务

        Statement statement = conn.createStatement(); //sql语句的执行者
        /* 操作字段
        String sql1 = "insert into emp(name, managerid, dept_id) values('王五',2,2)";//添加数据
        String sql2 = "update emp  SET emp.managerid = 3 where emp.id = 4";//修改数据
        String sql3 = "delete from emp where id = 4;delete from emp where id=3;";//删除数据

        int i = statement.executeUpdate(sql3);//返回改变的行数
        */

        conn.close();
    }

    public static void noTransaction(Connection connection) throws SQLException {
        String sql = "update account set balance=balance-100 where id=1";
        String sql2 = "update account set balance=balance+100 where id=2";
        //创建PreparedStatement对象
        PreparedStatement preparedStatement;
        try {
            connection.setAutoCommit(false);//事务开始

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();//执行第一个sql
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.executeUpdate(); // 执行sql2
            connection.commit(); //提交事务
        } catch (SQLException e) {
            //捕获到异常
            try {
                connection.rollback();// 进行事务回滚，默认回滚到事务开始的地方
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally { //关闭流
            connection.close();
        }
    }
}
