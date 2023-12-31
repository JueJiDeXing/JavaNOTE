package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.创建型.factory工厂.before;


public class CoffeeStore {

    public Coffee orderCoffee(String type) {
        //声明Coffee类型的变量，根据不同类型创建不同的coffee子类对象
        Coffee coffee = null;
        if("american".equals(type)) {
            coffee = new AmericanCoffee();
        } else if("latte".equals(type)) {
            coffee = new LatteCoffee();
        } else {
            throw new RuntimeException("对不起，您所点的咖啡没有");
        }
        //加配料
        coffee.addMilk();
        coffee.addsugar();

        return coffee;
    }
}
