package 数据结构与算法.算法.动态规划_贪心.动态规划.区间dp;

import java.util.*;

public class 石子合并 {
    /*
    n堆石子排成1排,每次可以合并相邻两堆,代价为两堆个数之和
    TODO 1.扩展到连续k堆一起合并
    TODO 2.扩展到环形堆合并
    TODO 3.扩展到其他代价规则,如乘积
    求合并为1堆的总代价最小值
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
    }

    /**
     令dp[i][j]为合并[i,j]的代价
     假设合并[i,j]的最后一步是合并[i,k]和[k+1,j]
     合并的代价一定为sum[i,j]
     则dp[i][j]=dp[i,k]+dp[k,j]+sum[i,j]
     sum[i,j]可以使用前缀和预处理
     */
    public int merge(int[] stones) {
        int n = stones.length;
        int[] pre = new int[n + 1];
        for (int i = 1; i <= n; i++) pre[i] = pre[i - 1] + stones[i - 1];
        int[][] dp = new int[n + 1][n + 1];
        for (int len = 2; len <= n; len++) {
            for (int i = 1; i + len - 1 <= n; i++) {
                int j = i + len - 1;
                dp[i][j] = 0x3f3f3f3f;
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][k] + dp[k + 1][j] + pre[j] - pre[i - 1], dp[i][j]);
                }
            }
        }
        return dp[1][n];
    }

}
