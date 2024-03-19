package 数据结构与算法.蓝桥杯真题.第11届国赛.Java大学A组;

import java.util.*;

public class H蓝跳跳 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();
        p = sc.nextInt();
        long L = sc.nextLong();
        int ans = dfs(L, false);
        System.out.println(ans);
    }

    static int mod = 20201114;
    static int k, p;
    static HashMap<Args, Integer> memo = new HashMap<>();

    static class Args {
        long L;
        boolean isP;

        public Args(long l, boolean isP) {
            L = l;
            this.isP = isP;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Args args = (Args) o;
            return L == args.L && isP == args.isP;
        }

        @Override
        public int hashCode() {
            return Objects.hash(L, isP);
        }
    }

    static int dfs(long L, boolean isP) {
        if (L == 0) return 1;
        Args key = new Args(L, isP);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int ans = 0;
        int up = isP ? p - 1 : k;//如果上一步跳了大于等于p,这一步不能跳大于等于p
        for (int i = 1; i <= up && i <= L; i++) {
            ans = (ans + dfs(L - i, i >= p)) % mod;
        }
        memo.put(key, ans);
        return ans;
    }
}
