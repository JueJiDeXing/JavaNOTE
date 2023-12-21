package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.创建型.builder建造者.demo1;


public class MobileBuilder extends Builder {

    public void buildFrame() {
        bike.setFrame("碳纤维车架");
    }

    public void buildSeat() {
        bike.setSeat("真皮车座");
    }

    public Bike createBike() {
        return bike;
    }
}
