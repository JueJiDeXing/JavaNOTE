package 数据结构与算法.算法.数论;

import java.util.Scanner;

public class 最大公约数 {
    //gcd(a,b)=gcd(b,a%b)
    //gcd(a,b)=gcd(b,a-b)

    //欧几里得（辗转相除法）
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);//当上一轮余数为0时(b==0),返回上一轮的除数(a),否则继续迭代
    }

    public static int gcd2(int x, int y) {
        if (x % y == 0) {
            return y;
        } else {
            return gcd2(y, x % y);
        }
    }

    public static int gcd3(int a, int b) {
        while (b > 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}
