package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.创建型.factory工厂.静态工厂;


public class Client {
    public static void main(String[] args) {
        //创建咖啡店类对象
        CoffeeStore store = new CoffeeStore();
        Coffee coffee = store.orderCoffee("latte");

        System.out.println(coffee.getName());
    }
}
