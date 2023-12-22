package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.创建型.factory工厂.简单工厂加配置文件;


public class Client {
    public static void main(String[] args) {
        Coffee coffee = CoffeeFactory.createCoffee("american");
        System.out.println(coffee.getName());

        System.out.println("=============");
        Coffee latte = CoffeeFactory.createCoffee("latte");
        System.out.println(latte.getName());
    }
}
