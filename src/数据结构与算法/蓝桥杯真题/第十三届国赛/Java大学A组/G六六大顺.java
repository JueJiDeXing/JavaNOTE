package 数据结构与算法.蓝桥杯真题.第十三届国赛.Java大学A组;

import java.math.BigInteger;
import java.util.Scanner;

public class G六六大顺 {
    /*
    A=(a1,...an)  ai由i+1个6组成,例如a2=666
    B=ai^2
    求数组B的前n项和,1<=n<=10^7
    Sn=[400(100^n -1) - 880(10^n -1) + 396n]/891
     */
    public static void main(String[] args) {
        main_enter();
    }

    private static void main_enter() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(Sum(n));
    }

    private static BigInteger Sum(int n) {
        BigInteger first = BigInteger.valueOf(400).multiply(BigInteger.TEN.pow(2 * n).subtract(BigInteger.ONE));
        BigInteger second = BigInteger.valueOf(880).multiply(BigInteger.TEN.pow(n).subtract(BigInteger.ONE));
        BigInteger third = BigInteger.valueOf(396).multiply(BigInteger.valueOf(n));
        return first.subtract(second).add(third)
                .divide(BigInteger.valueOf(891));
    }
}
