package 课程.实验课.实验2;

import java.util.Scanner;

public class _5_17 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("输入一个1~15的整数: ");
        int n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            System.out.print("  ".repeat(n - i));
            for (int j = i; j > 0; j--) {
                System.out.print(j + " ");
            }
            for (int j = 2; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}
