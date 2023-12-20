package demo.my_batis.mapper;

import demo.my_batis.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper //运行时会自动生成该接口的实现类对象,并交给IOC管理
public interface UserMapper {
    //查询用户信息
    @Select("select * from user.emp")
        List<User> list();
}
