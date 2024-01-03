package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.创建型.factory工厂.工厂方法;




public class CoffeeStore {

    private CoffeeFactory factory;

    public void setFactory(CoffeeFactory factory) {
        this.factory = factory;
    }

    //点咖啡功能
    public Coffee orderCoffee() {
        Coffee coffee = factory.createCoffee();
        //加配料
        coffee.addMilk();
        coffee.addsugar();
        return coffee;
    }
}
