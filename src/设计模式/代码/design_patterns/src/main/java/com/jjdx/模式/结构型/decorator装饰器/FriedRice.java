package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.结构型.decorator装饰器;


public class FriedRice extends FastFood {

    public FriedRice() {
        super(10,"炒饭");
    }

    public float cost() {
        return getPrice();
    }
}
