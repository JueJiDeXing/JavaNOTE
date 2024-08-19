package 课程.实验课.实验2;

import java.util.*;

public class _3_15 {
    /*
    三位数彩票
     */
    public static void main(String[] args) {
        int[] ans = create();
        System.out.println("测试信息: 产生的彩票数字为" + ans[0] + ans[1] + ans[2]);
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入一个三位数:");
        int guess = sc.nextInt();
        int[] gue = new int[]{guess / 100, (guess / 10) % 10, guess % 10};
        if (Arrays.equals(ans, gue)) {
            System.out.println("奖金 10000 美元");
            return;
        }
        int[] numCount1 = new int[10], numCount2 = new int[10];
        for (int i = 0; i < 3; i++) numCount1[ans[i]]++;
        for (int i = 0; i < 3; i++) numCount2[gue[i]]++;
        if (Arrays.equals(numCount1, numCount2)) {
            System.out.println("奖金 3000 美元");
            return;
        }
        for (int i = 0; i < 10; i++) {
            if (numCount1[i] > 0 && numCount2[i] > 0) {
                System.out.println("奖金 1000 美元");
                return;
            }
        }
        System.out.println("没有奖金");
    }

    static Random random = new Random();

    /**
     生成三位数彩票
     */
    static int[] create() {
        int[] ans = new int[3];
        for (int i = 0; i < 3; i++) {
            ans[i] = random.nextInt(10);
        }
        return ans;
    }
}
