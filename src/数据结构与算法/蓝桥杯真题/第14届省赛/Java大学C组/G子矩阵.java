package 数据结构与算法.蓝桥杯真题.第14届省赛.Java大学C组;


import java.io.*;
import java.util.*;

/**

 */
public class G子矩阵 {
    /*
    矩阵的价值定义为矩阵最大值与最小值的乘积
    给定n*m的矩阵,求a*b大小的子矩阵的最大价值
     */

    static int MOD = 998244353;
    static int[][] arr;
    static int n, m;
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    public static void main(String[] args) {
        n = Int();
        m = Int();
        int a = Int(), b = Int();
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = Int();
            }
        }
        int[] q = new int[Math.max(m, n)];//滑动窗口
        //求最大值
        int[][] rowMax = new int[n][m], maxV = new int[n][m];
        for (int i = 0; i < n; i++) {
            int h = 0, t = -1;
            for (int j = 0; j < m; j++) {
                while (h <= t && q[h] < j - b + 1) h++;
                while (h <= t && arr[i][q[t]] <= arr[i][j]) t--;
                q[++t] = j;
                if (j - b + 1 >= 0) {
                    rowMax[i][j - b + 1] = arr[i][q[h]];
                }
            }
        }
        for (int j = 0; j < m; j++) {
            int h = 0, t = -1;
            for (int i = 0; i < n; i++) {
                while (h <= t && q[h] < i - a + 1) h++;
                while (h <= t && rowMax[q[t]][j] <= rowMax[i][j]) t--;
                q[++t] = j;
                if (i - a + 1 >= 0) {
                    maxV[i - a + 1][i] = rowMax[q[t]][j];
                }
            }
        }
        //求最小值
        int[][] rowMin = new int[n][m], minV = new int[n][m];
        for (int i = 0; i < n; i++) {
            int h = 0, t = -1;
            for (int j = 0; j < m; j++) {
                while (h <= t && q[h] < j - b + 1) h++;
                while (h <= t && arr[i][q[t]] >= arr[i][j]) t--;
                q[++t] = j;
                if (j - b + 1 >= 0) {
                    rowMin[i][j - b + 1] = arr[i][q[h]];
                }
            }
        }
        for (int j = 0; j < m; j++) {
            int h = 0, t = -1;
            for (int i = 0; i < n; i++) {
                while (h <= t && q[h] < i - a + 1) h++;
                while (h <= t && rowMin[q[t]][j] >= rowMin[i][j]) t--;
                q[++t] = j;
                if (i - a + 1 >= 0) {
                    minV[i - a + 1][i] = rowMin[q[t]][j];
                }
            }
        }
    }

    private static void main1() {//7/10 3TLE
        n = Int();
        m = Int();
        int a = Int(), b = Int();
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = Int();
            }
        }
        Queue<int[]> maxQ = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        Queue<int[]> minQ = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        for (int i = 0; i < a; i++) {//初始矩阵
            for (int j = 0; j < b; j++) {
                maxQ.offer(new int[]{arr[i][j], i, j});
                minQ.offer(new int[]{arr[i][j], i, j});
            }
        }
        long ans = 0;
        boolean toRight = true;
        for (int row = 0; row <= n - a; row++) {//Z字形滑动矩阵窗口
            if (toRight) {
                for (int col = 0; col <= m - b; col++) {
                    //取(row,col)~(row+a-1,col+b-1)子矩阵的最大值和最小值
                    int[] result = getResult(maxQ, minQ, row, col, a, b);
                    ans = (ans + (long) result[0] * result[1]) % MOD;
                    //将下一列的值加入
                    offset(maxQ, minQ, row, col, a, b, 1);
                }
                //下移一行
                offset(maxQ, minQ, row, m - b, a, b, 0);
            } else {
                for (int col = m - b; col >= 0; col--) {
                    //取(row,col)~(row+a-1,col+b-1)子矩阵的最大值和最小值
                    int[] result = getResult(maxQ, minQ, row, col, a, b);
                    ans = (ans + (long) result[0] * result[1]) % MOD;
                    //将下一列的值加入
                    offset(maxQ, minQ, row, col, a, b, -1);
                }
                //下移一行
                offset(maxQ, minQ, row, m - b, a, b, 0);
            }
            toRight = !toRight;
        }
        System.out.println(ans);
    }

    /**
     将子矩阵向direction方向偏移一格
     将下一行/列的值加入
     */
    private static void offset(Queue<int[]> maxQ, Queue<int[]> minQ, int row, int col, int a, int b, int direction) {
        if (direction == 1) {//右
            if (col + b < m) {
                for (int i = row; i < row + a; i++) {
                    maxQ.offer(new int[]{arr[i][col + b], i, col + b});
                    minQ.offer(new int[]{arr[i][col + b], i, col + b});
                }
            }
        } else if (direction == -1) {//左
            if (col - 1 >= 0) {
                for (int i = row; i < row + a; i++) {
                    maxQ.offer(new int[]{arr[i][col - 1], i, col - 1});
                    minQ.offer(new int[]{arr[i][col - 1], i, col - 1});
                }
            }
        } else if (direction == 0) {//下
            if (row + a < n) {
                for (int i = col; i < col + b; i++) {
                    maxQ.offer(new int[]{arr[row + a][i], row + a, i});
                    minQ.offer(new int[]{arr[row + a][i], row + a, i});
                }
            }
        }

    }

    private static int[] getResult(Queue<int[]> maxQ, Queue<int[]> minQ, int row, int col, int a, int b) {
        deal(maxQ, row, col, a, b);
        deal(minQ, row, col, a, b);
        int max = maxQ.peek()[0];
        int min = minQ.peek()[0];
        return new int[]{max, min};
    }


    /**
     矩形区域(row,col)~(row+a-1,col+b-1)
     */
    static void deal(Queue<int[]> queue, int row, int col, int a, int b) {
        while (!queue.isEmpty()) {
            int[] pos = queue.peek();
            int i = pos[1], j = pos[2];
            //检查(i,j)是否在矩形区域内
            if (i >= row && i < row + a && j >= col && j < col + b) {
                return;
            }
            queue.poll();//不在区域,抛出
        }
    }
}
