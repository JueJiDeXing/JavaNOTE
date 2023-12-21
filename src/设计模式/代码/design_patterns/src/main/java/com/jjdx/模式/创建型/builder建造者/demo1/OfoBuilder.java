package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.创建型.builder建造者.demo1;


public class OfoBuilder extends Builder {
    public void buildFrame() {
        bike.setFrame("铝合金车架");
    }

    public void buildSeat() {
        bike.setSeat("橡胶车座");
    }

    public Bike createBike() {
        return bike;
    }
}
