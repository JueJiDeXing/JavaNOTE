package 数据结构与算法.算法.数学.数论.模.例题;

public class 蓝桥杯_a余b小于c的数对 {
    /**
     count{ a%b <= C | 1<=a<=A && 1<=b<=B }<br>
     {@link 数据结构与算法.蓝桥杯.小白入门赛.第2场._4取余}
     */
    static long f(int A, int B, int C) {
        if (C < 0) return 0;
        long ans = 0;
        for (int b = 1; b <= B; b++) {
            if (b <= C) {// a%b < b && b<=C => a%b < C 则a取值任意
                ans += A;
            } else {
                // a∈[kb,kb+C] -> a%b < C
                // a<=A -> k_max = A/b -> 0 <= k < A/b
                // count1 = (A/b) * (C+1) 完整段
                // count2 = (A%b,C) 最后一段
                ans += (long) (A / b) * (C + 1) + Math.min(A % b, C);
            }
        }
        return ans;
    }
}
