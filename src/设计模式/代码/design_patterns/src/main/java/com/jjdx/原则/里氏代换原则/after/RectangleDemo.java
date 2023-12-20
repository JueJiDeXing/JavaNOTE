package 设计模式.代码.design_patterns.src.main.java.com.jjdx.原则.里氏代换原则.after;

/**
 <h1>里氏代换原则</h1>
 任何基类可以出现的地方，子类一定可以出现。<br>
 通俗理解：子类可以扩展父类的功能，但不能改变父类原有的功能。<br>
 换句话说，子类继承父类时，除添加新的方法完成新增功能外，尽量不要重写父类的方法。<br>
 <p>
 如果通过重写父类的方法来完成新的功能，这样写起来虽然简单，但是整个继承体系的可复用性会比较差<br>
 特别是运用多态比较频繁时，程序运行出错的概率会非常大。
 */
public class RectangleDemo {
    public static void main(String[] args) {
        //创建长方形对象
        Rectangle r = new Rectangle();
        r.setLength(20);
        r.setWidth(10);
        //调用方法进行扩宽操作
        resize(r);

        printLengthAndWidth(r);

        //正方形不再继承长方形
    }

    //扩宽的方法
    public static void resize(Rectangle rectangle) {
        //判断宽如果比长小，进行扩宽的操作
        while (rectangle.getWidth() <= rectangle.getLength()) {
            rectangle.setWidth(rectangle.getWidth() + 1);
        }
    }

    //打印长和宽
    public static void printLengthAndWidth(Quadrilateral quadrilateral) {
        System.out.println(quadrilateral.getLength());
        System.out.println(quadrilateral.getWidth());
    }
}
