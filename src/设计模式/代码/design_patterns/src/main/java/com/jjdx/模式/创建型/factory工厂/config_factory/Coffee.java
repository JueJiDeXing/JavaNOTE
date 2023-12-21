package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.创建型.factory工厂.config_factory;


public abstract class Coffee {

    public abstract String getName();

    //加糖
    public void addsugar() {
        System.out.println("加糖");
    }

    //加奶
    public void addMilk() {
        System.out.println("加奶");
    }
}
