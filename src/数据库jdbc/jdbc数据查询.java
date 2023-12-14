package 数据库jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class jdbc数据查询 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //----------------------------------------------------------------------
        //在Mybatis中写在配置文件中
        // 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //获取连接对象
        String url = "jdbc:mysql://localhost:3306/user";
        String username = "root";
        String password = "Mx18397887506";
        Connection conn = DriverManager.getConnection(url, username, password);
        //----------------------------------------------------------------------
        //在Mybatis中自动封装
        //执行sql语句
        String sql = "select * from usertable1";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);//查询结果
        //封装结果数据
        List<User> userList = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            short age = resultSet.getShort("age");
            User user = new User(id, name, age);
            userList.add(user);
        }
        System.out.println(userList);
        //----------------------------------------------------------------------
        //在Mybatis中使用数据库连接池(类似线程池),提高了复用性
        //释放资源
        statement.close();
        conn.close();
    }
}

class User {
    int id;
    String name;
    short age;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public User() {
    }

    public User(int id, String name, short age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
