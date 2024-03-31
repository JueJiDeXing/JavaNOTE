package 数据结构与算法.蓝桥杯.真题卷.第12届.省赛.Java大学A组;

import java.io.*;

/**
 这题动态规划我真写不了
 */
public class J分果果 {
    /*
    n包糖果分给m个人,每人至少一包
    糖果编号1~n,第i包糖果重w[i],每个人得到的糖果编号需要是连续的
    每包糖果可以有两份(必须分出一份出去,另一个可分可不分),但同一个人最多拿其中一份
    求分出的方案中最大重量与最小重量的差最小

    长度为n的数组分为m个长度大于0的子数组,求子数组和的最大值与最小值差最小为多少

     */
    /*
10 8
5 4 5 8 2 6 7 6 7 3
4

     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    /**
     dp[i][j][k]:前i个区间,最后一个用了1颗的在j,用了2颗的在k,的区间和的最大值
        xxxxx
     xxxxxx
        k   j
     转移方程:
     1. dp[i][j][k] = dp[i][j][k-1]
     2.
     * @param args
     */
    public static void main(String[] args) {
        n = Int();
        m = Int();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Int();
        preSum = new int[n + 1];//preSum[i] = sum(arr[0]~arr[i-1])
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + arr[i - 1];
        }

        int[][][]dp=new int[m+1][n+1][n+1];
    }

    static int n, m;
    static int[] arr, preSum;

}
