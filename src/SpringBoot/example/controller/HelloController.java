package SpringBoot.example.controller;

import com.example.pojo.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
public class HelloController {
    @RequestMapping("/test")
    public String text() {
        //TODO
        return "";
    }
    @RequestMapping("/hello")
    public String hello(@RequestParam (name = "name" ,required = false) String userName , Integer age) {
        // @RequestParam (name = "name") 映射,防止参数与方法名不一致
        // required表示该项是否必须传递(默认为true),如果false且不传递name参数则返回状态码400
        System.out.println(userName+","+age);
        return "普通传参";
    }
    @RequestMapping("/hello2")
    public String hello2(User user) {
        //封装,要求浏览器传递的参数名与自定义类中的参数名一致
        //如果自定义类的某成员为另一个自定义类,浏览器传递参数时使用类.成员类
        System.out.println(user);
        return "对象传参";
    }
    @RequestMapping("/hello3")
    public String hello3(String[] hobby) {
        //当传入的参数有多个值(例如:复选框),这时可用数组接收参数(也可用集合,见hello4)
        //浏览器按key=value1&key=value2&...的方式传参
        System.out.println(Arrays.toString(hobby));
        return "数组传参";
    }
    @RequestMapping("/hello4")
    public String hello4(@RequestParam List<String> hobby) {
        //需要加上@注解
        System.out.println(hobby);
        return "集合传参";
    }
    @RequestMapping("/hello5")
    public String hello5(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime dateTime) {
        //需要指定日期格式
        System.out.println(dateTime);
        return "日期传参";
    }
    @RequestMapping("/hello6")
    public String hello6(@RequestBody User user) {
        //使用对象传参,传递的json的key需要与类中的变量名一致
        //浏览器必须为POST请求,并把json放入请求体中(Apifox里的地址就不用带这些参数了)
        System.out.println(user);
        return "json传参";
    }
    @RequestMapping("/hello7/{id}/{path}")
    public String hello7(@PathVariable Integer id,@PathVariable String path) {
        //浏览器参数不通过"?"符传递,而是直接作为路径,根据传递参数来动态设置地址
        //支持多层路径,需要在每个路径参数前加@PathVariable
        System.out.println(id+":"+path);
        return "路径传参";
    }
}
