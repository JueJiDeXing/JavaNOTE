package 数据结构与算法.蓝桥杯真题.第十三届国赛.Java大学A组;

import java.util.Scanner;
import java.util.*;

public class E交通信号 {
    /*
    n个节点,m条边
    每条边有红绿黄三种状态,初始都为绿灯,且状态按 绿,黄,红,黄,绿,黄,红...切换
    绿灯正向通行,红灯反向通行,黄灯不允许通行
    通过一条边的时间等于黄灯耗时,(在某条路上行进时不受灯的状态影响)
    //注意:红灯是反向通行而非禁止通行
    输入:
    第一行 n:n个节点; m:m条边; s,t:从节点s到节点t
    第k行  u,v:从节点u到节点v; g,r,d:绿,红,黄的持续时间
    输出:节点s到节点t的最短时间
     */
    public static void main(String[] args) {
        main_enter();
    }

    static class Edge {
        int from, to, g, r, d;
        boolean flag;

        public Edge(int from, int to, int g, int r, int d, boolean flag) {
            this.from = from;
            this.to = to;
            this.g = g;
            this.r = r;
            this.d = d;
            this.flag = flag;
        }
    }

    static class Vertex {
        int id;
        boolean isVisit;
        int dist = Integer.MAX_VALUE;
        List<Edge> edgeList = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "id=" + id +
                    ", dist=" + dist +
                    '}';
        }
    }

    private static void main_enter() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt(), s = sc.nextInt(), t = sc.nextInt();
        //建图
        List<Vertex> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new Vertex(i));
        }
        for (int i = 0; i < m; i++) {//m条边
            int u = sc.nextInt(), v = sc.nextInt(),
                    g = sc.nextInt(), r = sc.nextInt(), d = sc.nextInt();
            graph.get(u).edgeList.add(new Edge(u, v, g, r, d, true));
            graph.get(v).edgeList.add(new Edge(v, u, g, r, d, false));
        }

        Vertex sVertex = graph.get(s), tVertex = graph.get(t);
        sVertex.dist = 0;//起点距离
        //dijkstra
        PriorityQueue<Vertex> q = new PriorityQueue<>(Comparator.comparingInt(a -> a.dist));//存储[节点,时间]
        for (Vertex vertex : graph) q.offer(vertex);
        while (!q.isEmpty()) {//队列不为空
            Vertex now = q.poll();//取出队首节点
            now.isVisit = true;//设为已访问
            update(now, graph, q);//更新邻居距离
        }
        System.out.println("graph:" + graph);
        System.out.println(tVertex.dist);
    }

    private static void update(Vertex now, List<Vertex> graph, PriorityQueue<Vertex> q) {
        int time = now.dist;//当前时间
        for (Edge edge : now.edgeList) {//更新邻居距离
            Vertex to = graph.get(edge.to);
            if (to.isVisit) continue;//已访问跳过
            int tt = time % (edge.g + edge.r + edge.d * 2);
            if (tt < edge.g) {//绿灯
                int waitCurrLED = edge.g - tt;
                if (edge.flag) {//直接走
                    to.dist = Math.min(to.dist, time + edge.d);//走的时间等于黄灯时间
                } else {//等红灯
                    to.dist = Math.min(to.dist, time + waitCurrLED + edge.d);
                }
            } else if (tt < edge.g + edge.d) {//黄
                int waitCurrLED = edge.g + edge.d - tt;
                if (edge.flag) {//等绿灯
                    to.dist = Math.min(to.dist, time + waitCurrLED + edge.r + edge.d + edge.d);
                } else {//等红灯
                    to.dist = Math.min(to.dist, time + waitCurrLED + edge.d);
                }
            } else if (tt < edge.g + edge.d + edge.r) {//红
                int waitCurrLED = edge.g + edge.d + edge.r - tt;
                if (edge.flag) {//等绿灯
                    to.dist = Math.min(to.dist, time + waitCurrLED + edge.d + edge.d);
                } else {//直接走
                    to.dist = Math.min(to.dist, time + edge.d);
                }
            } else {//黄
                int waitCurrLED = edge.g + 2 * edge.d + edge.r - tt;
                if (edge.flag) {//等绿灯
                    to.dist = Math.min(to.dist, time + waitCurrLED + edge.d);
                } else {//等红灯
                    to.dist = Math.min(to.dist, time + waitCurrLED + edge.g + edge.d + edge.d);
                }
            }
            q.remove(to);
            q.offer(to);
        }
    }
}
