package SpringBoot.NOTE;

import com.example.pojo.Address;
import com.example.pojo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//@RestController=@Controller+@ResponseBody
@RestController
public class 响应体 {
    //返回值->@ResponseBody->浏览器
    @RequestMapping("/hello")
    public Result hello(String name, Integer age) {
        System.out.println(name + "," + age);
        //return "hello";
        //return new Result(1,"success",hello");

        // 为方便管理,使用result类统一响应
        return Result.success("hello");//见Result类
    }

    @RequestMapping("/hello2")
    public Address hello2() {
        Address address = new Address();
        address.setProvince("江西");
        address.setCity("景德镇");
        return address;//数组集合等会被解析为json格式
    }

    @RequestMapping("/hello3")
    public List<Address> hello3() {
        List<Address> list = new ArrayList<>();
        Address address1 = new Address();
        Address address2 = new Address();
        address1.setProvince("江西");
        address2.setProvince("湖南");
        address1.setCity("景德镇");
        address2.setCity("长沙");
        list.add(address1);
        list.add(address2);
        return list;//最外层为[]代表是一个数组,内部实例对象被解析为json格式
    }


}


