package 数据结构与算法.数据结构.队列.队列问题;

import java.util.*;

public class _1962移除石子使总数最小 {
    public int minStoneSum1(int[] piles, int k) {
        Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int ans = 0;
        for (int i : piles) {
            queue.offer(i);
            ans += i;
        }
        for (int i = 0; i < k; i++) {
            int p = queue.poll();
            ans -= p / 2;
            queue.offer((p + 1) / 2);
        }
        return ans;
    }

    public int minStoneSum2(int[] piles, int k) {
        int[] queue = new int[10001];//使用数组代替优先级队列
        int ans = 0;
        for (int i : piles) {
            ans += i;
            queue[i]++;
        }
        int curr = 10000;//大数优先
        while (k > 0 && curr > 0) {
            while (k > 0 && queue[curr] > 0) {
                ans -= curr / 2;
                queue[curr - curr / 2]++;
                k--;
                queue[curr]--;
            }
            curr--;
        }
        return ans;
    }
}
