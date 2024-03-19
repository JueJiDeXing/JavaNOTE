package 数据结构与算法.算法.数论.模;

public class 乘法逆元 {
    /*
    x在模p下的乘法逆元为 inv = x^(p-2) mod p
    a / x mod p != (a mod p) / (x mod p) mod p 除法不适用模性质
    a / x mod p = a * inv mod p = (a mod p) * (inv mod p) mod p 使用乘法逆元,将除法变为乘法
     */
    public static void main(String[] args) {
        //示例: 求 a(a+1)(2a+1)/6 % p 其中a非常大,它的乘法运算需要逐步取模
        int x = 6;
        int inv = 166666668;//6在MOD下的乘法逆元
        long a = 999999999L;

        System.out.println(a * (a + 1) * (2 * a + 1) / x % MOD);//WA, 因为 a * (a + 1)* (2 * a + 1)溢出

        long t = a * (a + 1) % MOD * (2 * a + 1) % MOD;
        System.out.println(t / x % MOD);//WA, 因为除法不适用模性质

        System.out.println(t * inv % MOD);// AC, 使用乘法逆元,乘法代替除法,可拆分

        long kMOD = 6L * MOD;
        System.out.println(a * (a + 1) % kMOD * (2 * a + 1) % kMOD / 6);//AC, 使用模上的除法提取
    }

    public static int modInverse(int a, int p) {
        return (int) quickPow(a, p - 2);
    }

    static final int MOD = (int) 1e9 + 7;

    // 快速幂用于求逆元
    static long quickPow(long base, long exp) {
        long res = 1L;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return res;
    }

}
