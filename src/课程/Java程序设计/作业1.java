package 课程.Java程序设计;

import java.util.*;

public class 作业1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter x1 and y1:");
        double x1 = sc.nextDouble(), y1 = sc.nextDouble();
        System.out.println("Enter x2 and y2:");
        double x2 = sc.nextDouble(), y2 = sc.nextDouble();
        double dis = Math.pow((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1), 0.5);
        System.out.println("The distance of the two points is " + (int) (dis * 1000) / 1000.0);
    }
}
