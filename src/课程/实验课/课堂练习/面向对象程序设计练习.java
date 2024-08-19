package 课程.实验课.课堂练习;

import java.util.*;

import static 课程.实验课.课堂练习.Relative.*;

public class 面向对象程序设计练习 {

    public static void main(String[] args) {
        //输入
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入第一个圆的圆心坐标和半径:");
        double x1 = sc.nextDouble(), y1 = sc.nextDouble(), r1 = sc.nextDouble();
        if (r1 <= 0) {
            throw new IllegalArgumentException("半径必须大于0");
        }
        System.out.print("请输入第二个圆的圆心坐标和半径:");
        double x2 = sc.nextDouble(), y2 = sc.nextDouble(), r2 = sc.nextDouble();
        if (r2 <= 0) {
            throw new IllegalArgumentException("半径必须大于0");
        }
        //实例化圆对象
        Circle c1 = new Circle(x1, y1, r1), c2 = new Circle(x2, y2, r2);
        //调用方法,计算关系
        switch (c1.relative(c2)) {
            case overlap -> System.out.println("两圆重叠");
            case tangency -> System.out.println("两圆相切");
            case disjoint -> System.out.println("两圆相离");
            default -> {
                assert false;
            }
        }
    }
}

enum Relative {//与另一个圆的关系
    overlap, tangency, disjoint
}

class Circle {
    double x, y;//圆心坐标
    double r;//半径


    public Circle(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    /**
     计算与另一个圆的关系

     @param other 另一个圆
     @return 返回一个Relative枚举类型, 表示相切、相离、重叠三种关系
     */
    public Relative relative(Circle other) {
        //计算与另一个圆的圆心距
        double d = Math.sqrt((x - other.x) * (x - other.x)
                + (y - other.y) * (y - other.y));
        if (Math.abs(r + other.r - d) < 1e-6) {//浮点数有精度问题,所以设置精度1e-6
            //两圆半径和恰好等于圆心距,相切
            return tangency;
        }
        if (d > r + other.r) {
            //两圆圆心距大于半径和,相离
            return disjoint;
        }
        return overlap;//重叠
    }
}
