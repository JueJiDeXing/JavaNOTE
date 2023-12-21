package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.创建型.factory工厂.静态工厂;



public class CoffeeStore {

    public Coffee orderCoffee(String type) {

        /*SimpleCoffeeFactory factory工厂 = new SimpleCoffeeFactory();
        //调用生产咖啡的方法
        Coffee coffee = factory工厂.createCoffee(type);*/
        Coffee coffee = SimpleCoffeeFactory.createCoffee(type);

        //加配料
        coffee.addMilk();
        coffee.addsugar();

        return coffee;
    }
}
