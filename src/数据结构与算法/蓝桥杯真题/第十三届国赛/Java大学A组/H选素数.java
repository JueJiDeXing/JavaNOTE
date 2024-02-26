package 数据结构与算法.蓝桥杯真题.第十三届国赛.Java大学A组;

import java.util.Scanner;

public class H选素数 {
    /*
    对于一个数x的一次操作如下:
    随机选择一个小于x的素数p,将x不断加1直到x为p的倍数(如果x本就是p的倍数则不变)

    输入一个整数n,n为初始x经过两次操作后的值,问初始值x最小为多少,如果不存在,则输出-1
    1<=n<=10^6
     */
    public static void main(String[] args) {
        main_enter();
    }

    private static void main_enter() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(MinX(n));
        System.out.println("X0=" + X0 + ",P0=" + P0 + ",X1="+ X1 + ",P1=" + P1);
    }

    static int N = 1000000;
    static boolean[] isComposite = new boolean[N + 1];//合数
    static int[] prime = new int[N + 1];//存储素数
    static int pNum = 0;//素数个数
    static int X0 = -1, P0 = -1, X1 = -1, P1 = -1;

    static {
        //欧拉筛法筛素数
        isComposite[0] = isComposite[1] = true;
        for (int i = 2; i <= N; i++) {
            if (!isComposite[i]) {
                prime[pNum++] = i;
            }
            //把后面的合数( i与现有质数的倍数(含自身) )筛掉
            for (int j = 0; prime[j] * i <= N && j <= pNum; j++) {
                isComposite[prime[j] * i] = true;
                if (i % prime[j] == 0) break;//防止重复筛
            }
        }
    }

    private static int MinX(int n) {
        int ans = Integer.MAX_VALUE;
        //第二次操作,x1,选择prime[i],加到n,n是prime[i]的倍数
        for (int i = 0; i < pNum && prime[i] <= n; i++) {
            if (n % prime[i] != 0) continue;
            int k = n / prime[i];//[k-1倍+1,k倍]范围内的x1,可以加到n
            for (int x1 = (k - 1) * prime[i] + 1; x1 <= n; x1++) {
                if (!isComposite[x1]) {
                    if (x1 < ans) {
                        P0 = P1 = prime[i];
                        X0 = X1 = x1;
                    }

                    ans = Math.min(ans, x1);
                    continue;
                }
                //第一次操作,x0,选择prime[j],加到x1,x1是prime[j]的倍数
                for (int j = 0; j < pNum && prime[j] <= x1; j++) {
                    if (x1 % prime[j] != 0) continue;
                    int k2 = x1 / prime[j];//[k-1倍+1,k倍]范围内的x0,可以加到x1
                    int x0 = (k2 - 1) * prime[j] + 1;
                    if (x0 < ans) {
                        P0 = prime[j];
                        P1 = prime[i];
                        X0 = x0;
                        X1 = x1;
                    }
                    ans = Math.min(ans, x0);
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }


}
