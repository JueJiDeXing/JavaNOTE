package 数据结构与算法.蓝桥杯真题.小白入门赛.第7场;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 测试通过:80% 20%TLE
 */
public class _6矩阵X {
    /*
    f(D`)为子矩阵D`的和,g(D`)为子矩阵最大值与最小值的差
    求矩阵D的最大f(D`)*g(D`)为多少
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static Queue<int[]> maxQueue = new PriorityQueue<>(((o1, o2) -> o2[0] - o1[0]));
    static Queue<int[]> minQueue = new PriorityQueue<>((Comparator.comparingInt(o -> o[0])));
    static long[][] D;

    /**
     D[i][j] 0 <= i < n , 0 <= j < m
     */
    static int getOriginD(int i, int j) {
        return (int) (D[i + 1][j + 1] - D[i][j + 1] - D[i + 1][j] + D[i][j]);
    }

    public static void main(String[] args) {//80%
        int n = Int(), m = Int(), n1 = Int(), m1 = Int();
        D = new long[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                D[i][j] = Int();
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                D[i][j] += D[i - 1][j] + D[i][j - 1] - D[i - 1][j - 1];
            }
        }
        maxQueue.clear();
        minQueue.clear();
        long ans = 0;
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < m1; j++) {
                maxQueue.offer(new int[]{getOriginD(i, j), i, j});
                minQueue.offer(new int[]{getOriginD(i, j), i, j});
            }
        }
        for (int i = 0; i <= n - n1; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j <= m - m1; j++) {//向右
                    int k = i + n1, l = j + m1;
                    long p = D[k][l] - D[i][l] - D[k][j] + D[i][j];
                    int max = popAndGet(maxQueue, i, j, k, l);
                    int min = popAndGet(minQueue, i, j, k, l);
                    ans = Math.max(ans, p * (max - min));
                    //System.out.println("p:" + p + ", [max,min]:" + "[" + max[0] + "," + min[0] + "]");
                    if (j != m - m1) {
                        shiftWindow(i, j, k, l, 1);
                    }
                }
                if (i != n - n1) shiftWindow(i, m - m1, i + n1, m, 2);//向下
            } else {
                for (int j = m - m1; j >= 0; j--) {//向左
                    int k = i + n1, l = j + m1;
                    long p = D[k][l] - D[i][l] - D[k][j] + D[i][j];
                    int max = popAndGet(maxQueue, i, j, k, l);
                    int min = popAndGet(minQueue, i, j, k, l);
                    ans = Math.max(ans, p * (max - min));
                    //System.out.println("p:" + p + ", [max,min]:" + "[" + max[0] + "," + min[0] + "]");
                    if (j != 0) {
                        shiftWindow(i, j, k, l, -1);
                    }
                }
                if (i != n - n1) shiftWindow(i, 0, i + n1, m1, 2);//向下
            }
        }
        System.out.println(ans);
    }

    /**
     当前子矩阵[i,j]~(k,l)
     */
    private static void shiftWindow(int i, int j, int k, int l, int direction) {
        if (direction == 1) {//右
            for (int x = i; x < k; x++) {
                maxQueue.offer(new int[]{getOriginD(x, l), x, l});
                minQueue.offer(new int[]{getOriginD(x, l), x, l});
            }
        } else if (direction == -1) {//左
            for (int x = i; x < k; x++) {
                maxQueue.offer(new int[]{getOriginD(x, j - 1), x, j - 1});
                minQueue.offer(new int[]{getOriginD(x, j - 1), x, j - 1});
            }
        } else if (direction == 2) {//下
            for (int x = j; x < l; x++) {
                maxQueue.offer(new int[]{getOriginD(k, x), k, x});
                minQueue.offer(new int[]{getOriginD(k, x), k, x});
            }
        }
    }

    private static int popAndGet(Queue<int[]> queue, int i, int j, int k, int l) {
        while (true) {
            int[] d = queue.peek();
            if (i <= d[1] && d[1] < k && j <= d[2] && d[2] < l) {
                return d[0];
            } else {
                queue.poll();
            }
        }
    }
}
