package 课程.实验课.实验5;

import java.util.HashMap;
import java.util.Scanner;

public class 完全平方 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a integer m: ");
        int m = sc.nextInt();
        // 寻找最小的n, 使得m*n是完全平方数
        int n = 1;
        HashMap<Integer, Integer> factor = split(m);// 对m分解因子, 因子->计数
        for (var e : factor.entrySet()) {
            int cnt = e.getValue();
            if (cnt % 2 == 1) {// 因子个数是奇数,需要凑成偶数才能是完全平方数
                n *= e.getKey();
            }
        }
        System.out.println("The smallest number n for m * n to be a perfect square is " + n);
        System.out.println("m*n is " + m * n);
    }

    static HashMap<Integer, Integer> split(int x) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 2; i * i < x; i++) {
            if (x % i != 0) continue;// 不是x的因子
            // i是x的因子
            int cnt = 0;// 计数
            while (x % i == 0) {
                x /= i;
                cnt++;
            }
            map.put(i, cnt);
        }
        if (x != 1) map.put(x, 1);
        return map;
    }


}
