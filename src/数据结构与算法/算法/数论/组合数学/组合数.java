package 数据结构与算法.算法.数论.组合数学;

public class 组合数 {
    /**
     C(n, m) = C(n - 1, m) + C(n - 1, m - 1)
     右式含义:对于某个项考虑选出的组合是否包含它
     */
    long C(int n, int m) {
        if (m > n || n < 0) return 0;
        if (n == m) return 1;
        return C(n - 1, m) + C(n - 1, m - 1);
    }

    public static void main(String[] args) {
        组合数 test = new 组合数();
        System.out.println(test.C2(5, 3));
    }
    long C2(int n, int m) {
        if (m > n || n < 0) return 0;
        if (n == m) return 1;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n && i <= m; i++) {
            dp[i][i] = 1;
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
            }
        }
        return dp[n][m];

    }
}
