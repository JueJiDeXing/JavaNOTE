package 数据结构与算法.算法.数论.组合数学;

public class 组合数 {
    public static void main(String[] args) {
        组合数 test = new 组合数();
        System.out.println(test.C3(11, 5));
        System.out.println(test.C(11, 5));
    }

    /**
     C(n, m) = C(n - 1, m) + C(n - 1, m - 1)
     右式含义:对于某个项考虑选出的组合是否包含它
     */
    long C(int n, int m) {
        if (m > n || n < 0) return 0;
        if (n == m) return 1;
        return C(n - 1, m) + C(n - 1, m - 1);
    }

    long C2(int n, int m) {
        int[][] c = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {//C_{n}^{1}=n
            c[i][1] = i;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 2; j <= m; j++) {
                c[i][j] = c[i - 1][j] + c[i - 1][j - 1];
            }
        }
        return c[n][m];
    }

    long C3(int n, int m) {
        int[] curr = new int[m + 1];
        curr[0] = 1;
        for (int i = 0; i <= n; i++) {
            for (int j = Math.min(i, m); j > 0; j--) {//j倒序遍历,因为curr[j]需要使用curr[j-1]
                curr[j] = curr[j] + curr[j - 1];
            }
        }
        return curr[m];
    }
}
