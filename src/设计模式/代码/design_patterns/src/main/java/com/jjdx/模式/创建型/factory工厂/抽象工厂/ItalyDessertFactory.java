package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.创建型.factory工厂.抽象工厂;


public class ItalyDessertFactory implements DessertFactory {

    public Coffee createCoffee() {
        return new LatteCoffee();
    }

    public Dessert createDessert() {
        return new Trimisu();
    }
}
