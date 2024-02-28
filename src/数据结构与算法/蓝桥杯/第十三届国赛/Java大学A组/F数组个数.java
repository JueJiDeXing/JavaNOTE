package 数据结构与算法.蓝桥杯.第十三届国赛.Java大学A组;

import java.util.*;

/**
 未完成
 */
public class F数组个数 {
    /*
    数组B=(b0,b1...bn-1)由环形数组A=(a0,a1...an-1)经过相邻最大化后得到
    b0=max(an-1,a0,a1),b1=max(a0,a1,a2)...
    输入数组B,问数组A可以有多少个
    元素非负,3<=n<=1000,0<=bi<=10
     */
    public static void main(String[] args) {
        main_enter();
    }

    private static void main_enter() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        int[] B = new int[n];
        String[] arr = sc.nextLine().split(" ");
        for (int i = 0; i < n; i++) {
            B[i] = Integer.parseInt(arr[i]);
        }
        System.out.println(getCount(B));
    }

    private static final int MOD = 1000000007;

    /**
     对于数组B,它的相邻最大化数组源A有多少个
     结果对1000000007取余
     */
    private static int getCount(int[] B) {
        //找到最小的元素位置
        int n = B.length;
        Integer[] minIdx = new Integer[n];
        for (int i = 0; i < n; i++) minIdx[i] = i;
        Arrays.sort(minIdx, (a, b) -> B[a] - B[b]);
        return 0;
    }

}
