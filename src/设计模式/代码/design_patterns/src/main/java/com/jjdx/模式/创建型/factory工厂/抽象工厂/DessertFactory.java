package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.创建型.factory工厂.抽象工厂;


public interface DessertFactory {

    //生产咖啡的功能
    Coffee createCoffee();

    //生产甜品的功能
    Dessert createDessert();
}
