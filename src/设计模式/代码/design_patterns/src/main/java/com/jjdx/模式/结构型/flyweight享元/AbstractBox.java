package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.结构型.flyweight享元;


public abstract class AbstractBox {

    //获取图形的方法
    public abstract String getShape();

    //显示图形及颜色
    public void display(String color) {
        System.out.println("方块形状：" + getShape() + ", 颜色：" + color);
    }
}
