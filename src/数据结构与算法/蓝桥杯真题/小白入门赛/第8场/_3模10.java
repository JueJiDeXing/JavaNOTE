package 数据结构与算法.蓝桥杯真题.小白入门赛.第8场;

import java.math.BigInteger;
import java.util.Scanner;
/**
 已AC
 */
public class _3模10 {
    /*
    求x^p % 10
     */

    static BigInteger m4 = new BigInteger("4");
    static BigInteger m2 = new BigInteger("2");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int x = sc.nextInt() % 10;
            BigInteger p = new BigInteger(sc.next());
            if (x == 0 || x == 1 || x == 5 || x == 6) {
                System.out.println(x);
                continue;
            }
            if (x == 2 || x == 3 || x == 7 || x == 8) {
                int m = (int) p.mod(m4).longValue();
                if (m == 0) m = 4;
                System.out.println(pow(x, m) % 10);
                continue;
            }
            //4,9
            int m = (int) p.mod(m2).longValue();
            if (m == 0) m = 2;
            System.out.println(pow(x, m) % 10);
        }
    }

    static int pow(int x, int p) {
        int t = x;
        for (int i = 1; i < p; i++) {
            x = x * t;
        }
        return x;
    }
}
