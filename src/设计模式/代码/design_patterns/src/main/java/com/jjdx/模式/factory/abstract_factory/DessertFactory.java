package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.factory.abstract_factory;

/**
 * @version v1.0
 * @ClassName: DessertFactory
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 黑马程序员
 */
public interface DessertFactory {

    //生产咖啡的功能
    Coffee createCoffee();

    //生产甜品的功能
    Dessert createDessert();
}
