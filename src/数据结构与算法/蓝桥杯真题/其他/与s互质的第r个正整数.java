package 数据结构与算法.蓝桥杯真题.其他;

import java.util.*;

/**
 已AC
 */
public class 与s互质的第r个正整数 {
    /*
    分解s的质因数 + 暴力枚举
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt(), r = sc.nextInt();
        //分解s的质因数
        for (int i = 2; i <= s; i++) {
            if (s % i == 0) {
                prime.add(i);
                while (s % i == 0) {
                    s /= i;
                }
            }
        }
        System.out.println(prime);

        int ans = 1;//枚举数
        int count = 1; //第几个有效互质数字
        while (count < r) {
            ans++;
            if (check(ans)) {//检查ans与s是否互质
                count++;
            }
        }
        System.out.println(ans);
    }

    static boolean check(int x) {
        //x是否与s互质
        for (int p : prime) {
            if (x % p == 0) return false;//x不能含有s的质因数
        }
        return true;
    }

    static List<Integer> prime = new ArrayList<>();
}
