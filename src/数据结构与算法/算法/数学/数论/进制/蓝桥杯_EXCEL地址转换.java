package 数据结构与算法.算法.数学.数论.进制;

import java.util.*;

public class 蓝桥杯_EXCEL地址转换 {
/*
A -> 1
AA -> 27
第k(从0开始)位 位权为26^k
但是没有0
 */

    /**
     正常转26进制,但是在余数为0时需要特殊处理
     余数为0时,说明是26,需要将余数置为26
     并且在正常转26进制时,该位是26的话会进位1个,所以需要减掉一个进位,n--
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int t = n % 26;
            if (t == 0) {
                n--;
                t = 26;
            }
            sb.append(toChar(t));
            n /= 26;
        }
        System.out.println(sb.reverse());
        scan.close();
    }

    static char toChar(int t) {//t:字典序,从1开始
        return (char) ((t - 1) + 'A');
    }
}
