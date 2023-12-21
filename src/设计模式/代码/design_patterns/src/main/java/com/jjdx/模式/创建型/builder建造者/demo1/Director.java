package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.创建型.builder建造者.demo1;


public class Director {

    //声明builder类型的变量
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    //组装自行车的功能
    public Bike construct() {
        builder.buildFrame();
        builder.buildSeat();
        return builder.createBike();
    }
}
