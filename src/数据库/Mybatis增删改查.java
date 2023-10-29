package 数据库;

public class Mybatis增删改查 {
}
//接口
/*
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

    //条件查询-------
    @Select("select * from emp where name like '%${name}%' and gender=#{gebder} " +
            "and entrydate between #{begin} and #{end} order by update_time desc")
    List<Emp> select3(String name, Short gender, LocalDate begin, LocalDate end);

    //条件查询优化
    @Select("select * from emp where name like concat('%',#{name},'%') and gender=#{gebder} " +
            "and entrydate between #{begin} and #{end} order by update_time desc")
    List<Emp> select4(String name, Short gender, LocalDate begin, LocalDate end);

}
*/
//实现类
/*实现类
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Short gender;
    private String image;
    private Short job;
    private LocalDate entrydate;
    private Integer deptId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
*/
//测试
/*
@SpringBootTest//单元测试
class MyBatisApplicationTests {
    //Emp测试---------------------------------
    @Autowired
    private EmpMapper empMapper;

    @Test
    public void testDelete() {
        empMapper.delete(17);//删除id为17的数据
    }

    @Test
    public void testInsert() {
        //构建员工对象
        Emp emp = new Emp();
        //emp.setId(1);主键不需要设置
        emp.setUsername("JueJiDeXing");
        emp.setPassword("123456");
        emp.setName("绝迹的星");
        emp.setGender((short) 1);
        emp.setImage("18.img");
        emp.setJob((short) 1);
        emp.setEntrydate(LocalDate.of(2022, 5, 20));
        emp.setDeptId(1);
        emp.setUpdateTime(LocalDateTime.now());
        emp.setCreateTime(LocalDateTime.now());

        //新增员工
        empMapper.insert(emp);
    }
    @Test
    public void testUpdate() {
        //构建员工对象
        Emp emp = new Emp();
        emp.setId(18);
        emp.setUsername("JueJiDeXing");
        emp.setPassword("123456");
        emp.setName("绝迹的星");
        emp.setGender((short) 1);
        emp.setImage("18.img");
        emp.setJob((short) 1);
        emp.setEntrydate(LocalDate.of(2022, 5, 20));
        emp.setDeptId(1);
        emp.setUpdateTime(LocalDateTime.now());
        //emp.setCreateTime(LocalDateTime.now());更新不改变创建时间

        //新增员工
        empMapper.update(emp);
    }
    @Test
    public void select(){
        System.out.println("--------------------");
        System.out.println(empMapper.select(18));
        //deptId=null, createTime=null, updateTime=null 实体类属性名与数据库字段名不一致不会自动封装
        //方法1:在查询语句中写别名,别名与类属性名一致   (具体实现见EmpMapper)
        //方法2:用@Results和@Result手动封装
        //方法3:开启驼峰命名自动封装功能 mybatis.configuration.map-underscore-to-camel-case=true
    }
    @Test
    public void conditionSelect(){
        System.out.println("--------------------");
        System.out.println(empMapper.select4("张",(short)1,LocalDate.of(2000,1,1),LocalDate.of(2022,1,1)));
        //deptId=null, createTime=null, updateTime=null 实体类属性名与数据库字段名不一致不会自动封装
        //方法1:在查询语句中写别名,别名与类属性名一致   (具体实现见EmpMapper)
        //方法2:用@Results和@Result手动封装
        //方法3:开启驼峰命名自动封装功能 mybatis.configuration.map-underscore-to-camel-case=true
    }
}

*/
