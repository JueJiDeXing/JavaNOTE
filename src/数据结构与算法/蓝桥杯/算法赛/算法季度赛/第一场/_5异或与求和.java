package 数据结构与算法.蓝桥杯.算法赛.算法季度赛.第一场;

import java.io.*;

public class _5异或与求和 {
    /*
    求 sum{ a[i1]^a[i2]+a[i3]^a[i4] | 1<=i1<i2<i3<i4<=n }
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static int MOD = 998244353;
    static long inv2 = 1;

    static {
        long x = 2, n = MOD - 2;
        while (n > 0) {
            if ((n & 1) == 1) inv2 = inv2 * x % MOD;
            x = x * x % MOD;
            n >>= 1;
        }
    }

    public static void main(String[] args) {
        int n = nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = nextInt();
        }
        slove1(n, A);
    }

    private static void slove1(int n, int[] A) {
        long ans = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                long count1 = (long) i * (i - 1) % MOD * inv2 % MOD;
                long count2 = (long) (n - j - 1) * (n - j - 2) % MOD * inv2 % MOD;
                ans = (ans + (A[i] ^ A[j]) * (count1 + count2)) % MOD;
            }
        }
        System.out.println(ans);
    }


}
