package 数据结构与算法.数据结构.图.建图的三种方法;

import java.util.ArrayList;
import java.util.List;

public class 邻接表 {
    List<List<Integer>> graph1 = new ArrayList<>();
    List<List<int[]>> graph2 = new ArrayList<>();

    public void creat(int n, int[][] edges) {
        for (int i = 0; i < n; i++) {
            graph1.add(new ArrayList<>());
            graph2.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph1.get(edge[0]).add(edge[1]);//无向
            graph1.get(edge[1]).add(edge[0]);//无向

            graph2.get(edge[0]).add(new int[]{edge[1], edge[2]});//有向
        }
    }

    public void travel(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(i + "邻居、边权:");
            for (int[] edge : graph2.get(i)) {
                System.out.print("(" + edge[0] + "," + edge[1] + ")");
            }
            System.out.println();
        }
    }
}
