package 数据结构与算法.蓝桥杯.小白入门赛.第2场;

import java.util.*;

/**
 已AC
 */
public class _1平方和 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        System.out.println((long) i * (i + 1) * (2L * i + 1) / 6);
    }
}
