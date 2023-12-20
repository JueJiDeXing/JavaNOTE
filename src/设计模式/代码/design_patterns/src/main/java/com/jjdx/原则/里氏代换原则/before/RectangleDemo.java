package 设计模式.代码.design_patterns.src.main.java.com.jjdx.原则.里氏代换原则.before;

/**
 正方形继承长方形,并且重写了setWidth方法
 违反了里氏代换原则,继承关系不成立
 */
public class RectangleDemo {

    public static void main(String[] args) {
        //创建长方形对象
        Rectangle r = new Rectangle();
        //设置长和宽
        r.setLength(20);
        r.setWidth(10);
        //调用resize方法进行扩宽
        resize(r);
        printLengthAndWidth(r);

        System.out.println("==================");
        //创建正方形对象
        Square s = new Square();
        //设置长和宽
        s.setLength(10);
        //调用resize方法进行扩宽
        resize(s); // Square继承Rectangle
        //死循环
        printLengthAndWidth(s);
    }

    //扩宽方法
    public static void resize(Rectangle rectangle) {
        //判断宽如果比长小，进行扩宽的操作
        while (rectangle.getWidth() <= rectangle.getLength()) {
            rectangle.setWidth(rectangle.getWidth() + 1);
        }
    }

    //打印长和宽
    public static void printLengthAndWidth(Rectangle rectangle) {
        System.out.println(rectangle.getLength());
        System.out.println(rectangle.getWidth());
    }
}
