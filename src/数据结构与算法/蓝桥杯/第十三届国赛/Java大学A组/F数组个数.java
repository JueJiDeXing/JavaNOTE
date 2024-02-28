package 数据结构与算法.蓝桥杯.第十三届国赛.Java大学A组;

import java.util.*;

/**
 未完成
 */
public class F数组个数 {
    /*
    数组B=(b0,b1...bn-1)由环形数组A=(a0,a1...an-1)经过相邻最大化后得到
    b0=max(an-1,a0,a1),b1=max(a0,a1,a2)...
    输入数组B,问数组A可以有多少个
    元素非负,3<=n<=1000,0<=bi<=10

    B: 8 6 1 8 8
    A: 6 0 0 1 8
       6 0 1 0 8
       6 0 1 1 8
       6 1 0 0 8
       6 1 0 1 8
       6 1 1 0 8
       6 1 1 1 8

     */
    public static void main(String[] args) {
        main_enter();
    }

    private static void main_enter() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        int[] B = new int[2 * n];
        String[] arr = sc.nextLine().split(" ");
        int maxIdx = 0;//最大值索引
        for (int i = 0; i < n; i++) {
            B[i] = B[i + n] = Integer.parseInt(arr[i]);
            if (B[i] > B[maxIdx]) maxIdx = i;
        }
        if (maxIdx == 0 && B[n - 1] == B[0]) {
            for (int i = n - 2; i >= 0; --i) {
                if (B[i] != B[maxIdx]) {
                    maxIdx = i + 1;
                    break;
                }
            }
        }
        if (maxIdx == n - 1) {
            maxIdx = 0;
        } else {
            n += ++maxIdx;
        }
        int[][] dp = new int[2 * n][3];
        dp[maxIdx][2] = 1;
        for (int i = maxIdx + 1; i < n; ++i)
            for (int k = 0; k <= 10; ++k) {
                if (k < B[i - 1] && k < B[i] && k < B[i + 1]) {
                    dp[i][0] = (dp[i][0] + dp[i - 1][1]) % MOD;
                    dp[i][1] = (dp[i][1] + dp[i - 1][2]) % MOD;
                } else if (k == B[i + 1] && k <= B[i - 1] && k <= B[i]) {
                    dp[i][2] = (dp[i][2] + dp[i - 1][2]) % MOD;
                    if (k == B[i - 1]) dp[i][2] = (dp[i][2] + dp[i - 1][0]) % MOD;
                    if (k == B[i]) dp[i][2] = (dp[i][2] + dp[i - 1][1]) % MOD;
                } else if (k == B[i] && k <= B[i - 1] && k <= B[i + 1]) {
                    dp[i][1] = (dp[i][1] + dp[i - 1][1]) % MOD;
                    dp[i][1] = (dp[i][1] + dp[i - 1][2]) % MOD;
                    if (k == B[i - 1]) dp[i][1] = (dp[i][1] + dp[i - 1][0]) % MOD;
                } else if (k == B[i - 1] && k <= B[i] && k <= B[i + 1]) {
                    dp[i][0] = (dp[i][0] + dp[i - 1][0]) % MOD;
                    dp[i][0] = (dp[i][0] + dp[i - 1][1]) % MOD;
                    dp[i][0] = (dp[i][0] + dp[i - 1][2]) % MOD;
                }
            }
        System.out.println(dp[n - 1][0]);
    }

    private static final int MOD = 1000000007;


}
