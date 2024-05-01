package 课程.实验课.实验4;

import java.util.Scanner;

public class _9_12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入第一条线段的两个端点(x1,y1),(x2,y2): ");
        double x1 = sc.nextDouble(), y1 = sc.nextDouble(), x2 = sc.nextDouble(), y2 = sc.nextDouble();
        System.out.print("请输入第二条线段的两个端点(x3,y3),(x4,y4): ");
        double x3 = sc.nextDouble(), y3 = sc.nextDouble(), x4 = sc.nextDouble(), y4 = sc.nextDouble();
        // 直线方程: ax+by+c=0
        // ax1+by1+c=0 ax2+by2+c=0  a(x1-x2)+b(y1-y2)=0
        double a1 = y1 - y2, b1 = -(x1 - x2), c1 = x1 * y2 - x2 * y1;
        double a2 = y3 - y4, b2 = -(x3 - x4), c2 = x3 * y4 - x4 * y3;
        // a1x+b1y+c1=0 a2x+b2y+c2=0
        LinearEquation equation = new LinearEquation(a1, b1, a2, b2, -c1, -c2);
        if (!equation.isSolvable()  ) {
            System.out.println("The equation has no solution.");
        } else {
            System.out.printf("The equation has a solution (%.2f,%.2f).\n", equation.getX(), equation.getY());
        }
    }

}
