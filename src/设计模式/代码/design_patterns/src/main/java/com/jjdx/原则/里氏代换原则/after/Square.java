package 设计模式.代码.design_patterns.src.main.java.com.jjdx.原则.里氏代换原则.after;


public class Square implements Quadrilateral {

    private double side;

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    public double getLength() {
        return side;
    }

    public double getWidth() {
        return side;
    }
}
