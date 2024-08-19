package 课程.实验课.实验2;

import java.util.Scanner;

public class _3_23 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a point with two coordinates:");
        double x = sc.nextInt(), y = sc.nextInt();
        if (-10 <= x && x <= 10 && -5 <= y && y <= 5) {
            System.out.printf("Point (%.1f, %.1f) is in the rectangle", x, y);
        } else {
            System.out.printf("Point (%.1f, %.1f) is not in the rectangle", x, y);
        }
    }
}
