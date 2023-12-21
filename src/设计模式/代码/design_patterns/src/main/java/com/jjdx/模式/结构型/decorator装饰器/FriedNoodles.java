package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.结构型.decorator装饰器;


public class FriedNoodles extends FastFood {

    public FriedNoodles() {
        super(12,"炒面");
    }

    public float cost() {
        return getPrice();
    }
}
