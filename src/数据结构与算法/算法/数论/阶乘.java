package 数据结构与算法.算法.数论;

public class 阶乘 {//factorial

    public static void main(String[] args) {
        System.out.println(new 阶乘().fact2(20));
    }


    public int fact1(int n) {
        if (n < 0 || n > 12) {
            throw new RuntimeException();
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * fact1(n - 1);
    }

    public long fact2(int n) {
        if(n>20){
            throw new RuntimeException();
        }
        long dp = 1;
        for (int i = 2; i <= n; i++) {
            dp *= i;
        }
        return dp;
    }
    /*
    Γ(s) =∫ 0->+∞ t^(s-1) e^(-t) dt
    float n>1: n! = n(n-1)(n-2)...(1+n-int(n)) * (n-int(n))!
     */

}
