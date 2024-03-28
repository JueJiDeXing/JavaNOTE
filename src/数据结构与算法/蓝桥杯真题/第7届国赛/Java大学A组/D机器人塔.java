package 数据结构与算法.蓝桥杯真题.第7届国赛.Java大学A组;

import java.util.*;

public class D机器人塔 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt(), B = sc.nextInt();
        int k = check(A + B);
        if (k == -1) {
            System.out.println(0);
            return;
        }
        //long ans=dfs(0,)

    }
    /**
     n是否是 k*(k+1)/2
     */
    static int check(int n) {
        int k = 0;
        while (true) {
            int v = k * (k + 1) / 2;
            if (v == n) return k;
            if (v > n) return -1;
            k++;
        }
    }
}
