package 数据结构与算法.蓝桥杯.第十四届省赛;

import java.util.Arrays;
import java.util.Scanner;

public class I高塔 {
    /*
    9 15
    3 2 5 7 1 4 6 8 3
    90159349
    [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90159349]
    [0, 2, 14, 116, 598, 2520, 11282, 66794, 462716, 2997358, 16726350, 80129472, 335092674, 248167203, 199550606, 0]
    [0, 5, 45, 190, 720, 3420, 23375, 170205, 1069360, 5597175, 24837065, 95780040, 328177840, 19154437, 0, 0]
    [0, 7, 21, 77, 434, 3451, 25375, 150465, 725732, 2942415, 10340617, 32290965, 91364630, 0, 0, 0]
    [0, 1, 6, 43, 380, 2701, 14738, 64311, 234488, 740217, 2078878, 5303331, 0, 0, 0, 0]
    [0, 4, 32, 300, 1984, 9716, 37536, 120604, 335552, 832932, 1885792, 0, 0, 0, 0, 0]
    [0, 6, 60, 354, 1512, 5022, 13812, 32970, 70608, 138870, 0, 0, 0, 0, 0, 0]
    [0, 8, 40, 144, 392, 880, 1728, 3080, 5104, 0, 0, 0, 0, 0, 0, 0]
    [0, 3, 9, 18, 30, 45, 63, 84, 0, 0, 0, 0, 0, 0, 0, 0]
     */
    static int MOD = 998244353;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();// n个回合,初始m点能量
        int[] A = new int[n];
        for (int i = 0; i < n; i++) A[i] = sc.nextInt();
        // 第i回合可以消耗Ci点能量(Ci>0),向上爬Ai*Ci层
        // 游戏结束条件:n个回合结束 或 第k个回合结束后能量耗尽
        // 求游戏方案数
        memo = new int[n][m + 1];
        System.out.println(dfs(n, m, A, 0));
       for(int []mem:memo){
           System.out.println(Arrays.toString(mem));
       }
    }

    static int[][] memo;

    static int dfs(int n, int m, int[] A, int curr) {
        if (curr == n || m == 0) {
            return 1;
        }
        if(memo[curr][m]!=0)return  memo[curr][m];
        int ans = 0;
        for (int Ci = 1; Ci <= m; Ci++) {
            //消耗Ci点能量
            int maxUp = A[curr] * Ci;//最多走的层数
            ans = (ans + maxUp * dfs(n, m - Ci, A, curr + 1) % MOD) % MOD;
        }
        memo[curr][m] = ans;
        return ans;
    }
}






