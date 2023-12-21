package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.创建型.factory工厂.工厂方法;


public class LatteCoffeeFactory implements CoffeeFactory {

    public Coffee createCoffee() {
        return new LatteCoffee();
    }
}
