package 数据结构与算法.蓝桥杯.第十三届国赛.Java大学A组;

import java.util.Scanner;

/**
 已AC
 */
public class D斐波那契数组 {
    /*
    如果A=(a0,a1,..an-1)满足:
    1. n>=2
    2. a0=a1
    3. ai=ai-1+ai-2 (i>=2)
    则A为斐波那契数组
    输入一个数组A,问至少修改几个元素(大于0),A会变为斐波那契数组
     */
    public static void main(String[] args) {
        main_enter();
    }

    private static void main_enter() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String[] split = sc.nextLine().split(" ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(split[i]);
        }
        System.out.println(minChange(arr));
    }

    private static int minChange(int[] arr) {
        int ans = arr.length;
        //a0->b  a1->b
        for (int b = 0; b <= 1000000; b++) {
            ans = Math.min(ans, check(arr, b));
        }
        return ans;
    }

    /**
     在前两项为first的情况下要修改多少次后面的数
     */
    private static int check(int[] arr, int first) {
        int success = 0;
        if (arr[0] == first) success++;
        if (arr[1] == first) success++;
        int p1 = first, p2 = first;
        int p3;
        for (int i = 2; i < arr.length; i++) {
            p3 = p1 + p2;
            if (p3 > 1000000) break;
            if (arr[i] == p3) success++;
            p1 = p2;
            p2 = p3;
        }
        return arr.length - success;
    }
}
