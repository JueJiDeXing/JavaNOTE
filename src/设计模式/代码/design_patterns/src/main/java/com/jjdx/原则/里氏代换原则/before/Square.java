package 设计模式.代码.design_patterns.src.main.java.com.jjdx.原则.里氏代换原则.before;


public class Square extends Rectangle {

    @Override
    public void setLength(double length) {
        super.setLength(length);
        super.setWidth(length);
    }

    @Override
    public void setWidth(double width) {
        super.setLength(width);
        super.setWidth(width);
    }
}
