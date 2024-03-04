package 课程.实验课.实验1;

import java.util.Scanner;

public class _2_6 {//Page 60
    public static void main(String[] args) {
        System.out.print("Enter a number between 0 and 1000:");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n < 0 || n > 1000) {
            throw new RuntimeException("输入数据不合法");
        }
        int ans = 0;
        while (n > 0) {
            ans += n % 10;
            n /= 10;
        }
        System.out.println("The sum of the digits is " + ans);
    }
}
