package 课程.实验课.实验2;

import java.util.*;

public class _4_5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of sides:");
        int n = sc.nextInt();
        System.out.print("Enter the side:");
        double s = sc.nextDouble();
        double ans = n * s * s / (4 * Math.tan(Math.PI / n));
        System.out.println("The area of the polygon is " + ans);
    }
}
