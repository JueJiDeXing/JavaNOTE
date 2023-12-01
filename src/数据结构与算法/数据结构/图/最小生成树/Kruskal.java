package 数据结构与算法.数据结构.图.最小生成树;

import 数据结构与算法.数据结构.图.Edge;
import 数据结构与算法.数据结构.并查集.DisjointSet;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Kruskal {
    /**
     <h1>Kruskal 最小生成树算法</h1>
     1.将所有顶点设为"不连通"<br>
     2.将所有边按边权升序<br>
     3.每次选择最小权边,如果两端是不连通的,则将两端连通<br>
     */
    public void kruskal(int size, PriorityQueue<Edge> queue) {
        List<Edge> list = new ArrayList<>();
        DisjointSet set = new DisjointSet(size);//使用并查集进行连通操作
        while (list.size() < size - 1) {//n个顶点要连接n-1条边
            Edge poll = queue.poll();//获取权重最小的边
            int i = set.find(poll.start);//查找集合的老大
            int j = set.find(poll.end);
            if (i != j) {//在一个集合里的所有元素的老大都是相等的,如果ij不等,说明start和end在两个集合中
                //没有连通
                set.union(i, j);//将i,j集合设为连通
                list.add(poll);//加这条边
            }
        }
        for (Edge edge : list) {
            System.out.println(edge);
        }
    }
}
