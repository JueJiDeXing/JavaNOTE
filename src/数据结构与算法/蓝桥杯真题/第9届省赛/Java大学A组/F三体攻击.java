package 数据结构与算法.蓝桥杯真题.第9届省赛.Java大学A组;

import java.io.*;
/**
 已AC
 */
public class F三体攻击 {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    /**
     TODO 前缀和+差分+二分
     */
    public static void main(String[] args) {
        int A = Int(), B = Int(), C = Int(), m = Int();
        int[][][] map = new int[A][B][C];
        for (int i = 0; i < A; i++) {
            for (int j = 0; j < B; j++) {
                for (int k = 0; k < C; k++) {
                    map[i][j][k] = Int();
                }
            }
        }


        //暴力 3AC 1TLE  C艹能过
        for (int cur = 0; cur < m; cur++) {
            int lat = Int() - 1, rat = Int() - 1, lbt = Int() - 1, rbt = Int() - 1, lct = Int() - 1, rct = Int() - 1, ht = Int();
            for (int i = lat; i <= rat; i++) {
                for (int j = lbt; j <= rbt; j++) {
                    for (int k = lct; k <= rct; k++) {
                        if (map[i][j][k] < ht) {
                            System.out.println(cur + 1);
                            return;
                        }
                        map[i][j][k] -= ht;
                    }
                }
            }
        }
    }
}
