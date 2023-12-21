package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.创建型.builder建造者.demo1;


public abstract class Builder {

    //声明Bike类型的变量，并进行赋值
    protected Bike bike = new Bike();

    public abstract void buildFrame();

    public abstract void buildSeat();

    //构建自行车的方法
    public abstract Bike createBike();
}
