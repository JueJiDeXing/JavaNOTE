package SpringBoot.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan({"com.example","NOTE"})//一般把所有包都放在启动类所在包,减少冗余配置
@SpringBootApplication //@SpringBootApplication注解包含@ComponentScan,默认扫描当前包及其子包
public class MySpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(
                MySpringApplication.class, args);
    }

}
