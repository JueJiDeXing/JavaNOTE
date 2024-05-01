package 数据结构与算法.数据结构.图.最短路径.重写;

import java.util.*;

public class Dijkstra {
    static List<int[]>[] graph;
    static long[] distance;
    static boolean[] isVisit;
    static PriorityQueue<Integer> queue = new PriorityQueue<>(
            (Comparator.comparingInt(o -> Math.toIntExact(distance[o]))));//未访问顶点

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        graph = new ArrayList[n + 1];//编号从1开始
        Arrays.setAll(graph, k -> new ArrayList<>());
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
        }
        distance = new long[n + 1];
        isVisit = new boolean[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;//source==1
        queue.clear();
        queue.offer(1);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (isVisit[current]) continue;
            update(current);
            isVisit[current] = true;
        }
        for (int i = 2; i <= n; i++) {
            System.out.println(distance[i]);
        }
    }

    private static void update(int u) {
        for (int[] edge : graph[u]) {
            int v = edge[0], w = edge[1];
            if (isVisit[v]) continue;
            long newDistance = distance[u] + w;
            if (newDistance < distance[v]) {
                distance[v] = newDistance;
                queue.offer(v);
            }
        }
    }
}
