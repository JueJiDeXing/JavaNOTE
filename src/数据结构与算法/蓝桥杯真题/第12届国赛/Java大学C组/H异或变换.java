package 数据结构与算法.蓝桥杯真题.第12届国赛.Java大学C组;

import java.util.Scanner;

public class H异或变换 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.nextInt();
        int k = sc.nextInt();
        sc.nextLine();
        char[] s = sc.next().toCharArray();
        int n = s.length;
        for (int i = 0; i < k; i++) {
            //变换k次
            for (int j = n - 1; j > 0; j--) {
                if (s[j] == s[j - 1]) {
                    s[j] = '0';
                } else {
                    s[j] = '1';
                }
            }
        }
        System.out.println(new String(s));
    }
}
