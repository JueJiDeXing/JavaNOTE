package 数据结构与算法.蓝桥杯真题.第十三届国赛.Java大学A组;

import java.util.Scanner;

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
        //case1:a1->a0
        ans = Math.min(ans, check(arr, arr[0]) + (arr[0] == arr[1] ? 0 : 1));
        //case2:a0->a1
        ans = Math.min(ans, check(arr, arr[1]) + (arr[0] == arr[1] ? 0 : 1));
        //case3:a0->b a1->b
        for (int b = 0; b < 100000; b++) {
            ans = Math.min(ans, check(arr, b) + (arr[0] == b ? 0 : 1) + (arr[1] == b ? 0 : 1));
        }
        return ans;
    }

    /**
     在前两项为first的情况下要修改多少次后面的数
     */
    private static int check(int[] arr, int first) {
        int prev = 1, curr = 2;//倍数
        int count = 0;
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] != curr * first) count++;
            int t = curr;
            curr += prev;
            prev = t;
        }
        return count;
    }
}
