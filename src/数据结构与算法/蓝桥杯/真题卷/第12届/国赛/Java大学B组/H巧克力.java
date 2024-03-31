package 数据结构与算法.蓝桥杯.真题卷.第12届.国赛.Java大学B组;

import java.io.*;
import java.util.*;

public class H巧克力 {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    /**
     巧克力:种类、价格、数量、剩余保质期<br>
     花最少的钱买到x块巧克力,品种不限<br>
     <p>
     优先考虑后面能不能买到(保质期),在能买到的基础上选择最便宜的<br>
     从最后一天开始买,每买一块,所需的保质期就减少一天,扩大了选择范围<br>
     */
    public static void main(String[] args) {
        int x = Int(); // 吃巧克力的天数
        int n = Int();  // 巧克力的种类数
        // 巧克力
        int[][] cote = new int[n][3];
        for (int i = 0; i < n; ++i) {
            cote[i][0] = Int();// 单价
            cote[i][1] = Int();// 保质期还剩天数
            cote[i][2] = Int();// 数量
        }
        // 对数组排序，按单价进行排序，便于后面优先选择便宜的
        Arrays.sort(cote, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            if (a[1] != b[1]) return a[1] - b[1];
            return a[2] - b[2];
        });
        /*如果有保质期很长，价格很低，但你很早就吃完了，后面不得不选择昂贵的巧克力，也就是说它原本可以在很多天之后吃就行，
         * 现在却在前几天就吃了，吃的时候保质期还有很长的一段时间，到了后面可供选择的巧克力保质期就会越来越短，
         * 很可能会出现得不到一个可行方案的情况*/
        /*所以重点不应该是在价格上，而是在保质期上，价格你如果前几天买了便宜的，后面几天就得买贵的
         * 后面几天买便宜的，前面几天就得买贵的，所以优先考虑的是后面几天买的到买不到的问题
         * 后面几天如果可以买到，前面几天一定可以买到，而且优先选择的还是单价便宜的*/
        int day = x;
        long pay = 0;
        while (day > 0) {
            int i = 0;
            // 因为cote数组已经排过序了，所以直接从上往下选取便宜的即可
            while (i < n) {
                // 判断保质期是否符合要求,判断是否有剩余
                if (cote[i][1] >= day && cote[i][2] > 0) {
                    pay += cote[i][0];
                    cote[i][2]--;
                    break;
                }
                i++;
            }
            // 这一天任何巧克力都不符合要求，没买到
            if (i == n) {
                System.out.println(-1);
                return;
            }
            day--;
        }
        System.out.println(pay);
    }

}
