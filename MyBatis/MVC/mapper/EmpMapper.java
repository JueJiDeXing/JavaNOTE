
package demo.my_batis.mapper;

import demo.my_batis.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    //1.删除------------------------------------------
    @Delete("delete from emp where id = #{id}")
    // #{}为占位符,大括号中写给方法传递的参数(如果只有一个参数,则{}里的名字可以随便写
    void delete(Integer id);

    //2.新增------------------------------------------
    @Options(keyProperty = "id", useGeneratedKeys = true)//主键返回,使用主键-封装到id属性
    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time)\n" +
            "VALUES (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime});")
    void insert(Emp emp);//通过emp对象传参

    //3.更新-------------------------------------------
    @Update("update emp set username=#{username},name=#{name},gender=#{gender},image=#{image}," +
            "job=#{job},entrydate=#{entrydate},update_time=#{updateTime},dept_id=#{deptId} where id=#{id}")
    void update(Emp emp);

    //4.查询---------------------------------------------
    //原:类属性名与数据库字段名不一致,无法自动封装
    @Select("select * from emp where id=#{id}")
    Emp select(Integer id);

    //方法1:起别名
    @Select("select id, username, password, name, gender, image, job, entrydate, " +
            "dept_id deptId, create_time createTime, update_time updateTime from emp where id=#{id}")
    Emp select1(Integer id);

    //方法2:注解手动映射
    @Results({
            @Result(column = "dept_id", property = "deptId"),//字段->属性
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    @Select("select * from emp where id=#{id}")
    Emp select2(Integer id);
    //方法3:配置文件配置驼峰自动映射
    //mybatis.configuration.map-underscore-to-camel-case=true (要求数据库字段为下划线命名,实体类属性为驼峰命名)

    //条件查询--
    @Select("select * from emp where name like '%${name}%' and gender=#{gebder} " +
            "and entrydate between #{begin} and #{end} order by update_time desc")
    List<Emp> select3(String name, Short gender, LocalDate begin, LocalDate end);

    //条件查询优化--
    @Select("select * from emp where name like concat('%',#{name},'%') and gender=#{gebder} " +
            "and entrydate between #{begin} and #{end} order by update_time desc")
    List<Emp> select4(String name, Short gender, LocalDate begin, LocalDate end);

    //XML映射查询,sql语句写在接口同名的配置文件中
    List<Emp> select5(String name, Short gender, LocalDate begin, LocalDate end);

    //动态查询,如果传递的参数为null,则不拼接这个查询条件
    List<Emp> select6(String name, Short gender, LocalDate begin, LocalDate end);

    //动态更新
    void update2(Emp emp);

    //批量删除
    void delete2(List<Integer> ids);

}
