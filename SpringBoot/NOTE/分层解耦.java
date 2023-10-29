package SpringBoot.NOTE;

import com.example.pojo.Result;
import com.example.pojo.User;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

public class 分层解耦 {
    /*
    controller:控制层,接收客户端请求,解析请求,响应数据
    service:业务逻辑层,处理具体的业务逻辑
    dao:数据访问层,负责数据的访问,包括增删改查
     */
}

//------------------------------------------
//dao数据访问层
interface UserDao {//面向接口编程,提高代码的灵活性

    List<User> listEmp();
}

//dao实现类
@Component(value = "daoA") //解耦,控制反转,将当前类交给IOC容器管理,成为IOC中的bean
//bean名默认为类名首字母小写,可通过value指定bean名
// Component为bean的基础注解,推荐使用衍生注解Controller(控制层),Service(业务逻辑层),Repository(数据访问层)
//bean包,必须被@ComponentScan扫描到才能生效
class UserDaoA implements UserDao {
    //接口的A实现类
    @Override
    public List<User> listEmp() {
        //数据访问,增删改查
        //TODO 解析项目里的文件,并返回解析后的数据
        return new ArrayList<>();
    }
}

@Primary
@Component(value = "daoB")
class UserDaoB implements UserDao {
    //接口的A实现类
    @Override
    public List<User> listEmp() {
        //数据访问,增删改查
        //TODO 解析项目里的文件,并返回解析后的数据
        return new ArrayList<>();
    }
}

//------------------------------------------
//service业务逻辑层
interface UserService {
    List<User> listEmp();
}

//service实现类
@Component //将当前
class UserServiceA implements UserService {
    //private UserDao userDao = new UserDaoA();//面向接口
//    @Autowired //依赖注入
//    @Qualifier("UserDaoB")
    @Resource(name = "UserDaoB")
    private UserDao userDao;//解耦,service依赖dao,controller依赖service,直接new对象不便于维护
    //使用依赖注入解耦,运行时IOC会提供该类型变量并赋值
    //在切换类型时,例如UserDaoA切换至UserDaoB,需要在B上加@Component注解
    // 需要取消A的注解,如果有多个相同类型的bean则报错
    // 解决方案1:@Primary设置类优先级(加在类上表示优先注入)
    // 解决方案2:@Autowired+@Qualifier("bean名")设置按bean名注入 # bean类名默认为类名首字母小写)
    // 解决方案3:@Resource(name="bean名")代替@Autowired
    @Override
    public List<User> listEmp() {
        List<User> empList = userDao.listEmp();//参数来源于dao层对文件的解析
        //TODO 数据转换处理

        //返回
        return empList;
    }
}

//------------------------------------------
//controller控制层
@RestController
class UserController {
    //private UserService userService = new UserServiceA();
    @Autowired //依赖注入
    private UserService userService;//解耦

    @RequestMapping("/hello")
    public Result list() {
        List<User> userList = userService.listEmp();
        return Result.success(userList);
    }
}


