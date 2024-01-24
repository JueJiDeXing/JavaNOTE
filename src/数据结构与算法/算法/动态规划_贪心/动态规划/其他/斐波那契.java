package 数据结构与算法.算法.动态规划_贪心.动态规划.其他;

public class 斐波那契 {
    /*
    动态规划入门
    f(n)=f(n-1)+f(n-2)
     */
    public int fibonacci(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int fibonacci2(int n) {
        if (n <= 1) return 1;
        int a = 0;//降维 , 一维数组->两个变量
        int b = 1;
        for (int i = 2; i <= n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }
        return b;
    }
}
