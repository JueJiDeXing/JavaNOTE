package 课程.实验课.实验2;

import java.util.*;

public class _3_29 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter circle1's center x-, y-coordinates, and radius:");
        double x1 = sc.nextDouble(), y1 = sc.nextDouble(), r1 = sc.nextDouble();
        System.out.print("Enter circle2's center x-, y-coordinates, and radius:");
        double x2 = sc.nextDouble(), y2 = sc.nextDouble(), r2 = sc.nextDouble();
        double dis = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        if (dis < Math.abs(r1 - r2)) {
            //2在1内
            System.out.println("circle2 is inside circle1");
        } else if (dis <= r1 + r2) {
            //相交
            System.out.println("circle2 overlaps circle1");
        } else {
            //不相交
            System.out.println("circle2 does not overlap circle1");
        }
    }
}
