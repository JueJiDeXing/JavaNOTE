package 数据结构与算法.数据结构.图;

import 数据结构与算法.数据结构.并查集.DisjointSet;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
无向图
 */
public class 最小生成树 {
    //在图中找到一颗树,使得从起点到其他节点距离最短
    // n+1个顶点会有n条边
    public static void main(String[] args) {
        /*
          v2---- v4
         /     /   \
       v1----v3     v5
         \     \   /
           ---- v6

         */
        //Vertex v1 = new Vertex("v1");
        //Vertex v2 = new Vertex("v2");
        //Vertex v3 = new Vertex("v3");
        //Vertex v4 = new Vertex("v4");
        //Vertex v5 = new Vertex("v5");
        //Vertex v6 = new Vertex("v6");
        //v1.edges = List.of(new Edge(v2, 7), new Edge(v3, 9), new Edge(v6, 14));
        //v2.edges = List.of(new Edge(v4, 15));
        //v3.edges = List.of(new Edge(v4, 11), new Edge(v6, 2));
        //v4.edges = List.of(new Edge(v5, 6));
        //v5.edges = List.of();
        //v6.edges = List.of(new Edge(v5, 9));
        //List<Vertex> graph = new ArrayList<>();
        //graph.add(v1);
        //graph.add(v2);
        //graph.add(v3);
        //graph.add(v4);
        //graph.add(v5);
        //graph.add(v6);
        //prim(graph,v1);
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");
        Vertex v7 = new Vertex("v7");
        List<Vertex> vertices = List.of(v1, v2, v3, v4, v5, v6, v7);

        PriorityQueue<Edge> queue = new PriorityQueue<>(List.of(
                new Edge(vertices, 0, 1, 2),
                new Edge(vertices, 0, 2, 4),
                new Edge(vertices, 0, 3, 1),
                new Edge(vertices, 1, 3, 3),
                new Edge(vertices, 1, 4, 10),
                new Edge(vertices, 2, 3, 2),
                new Edge(vertices, 2, 5, 5),
                new Edge(vertices, 3, 4, 7),
                new Edge(vertices, 3, 5, 8),
                new Edge(vertices, 3, 6, 4),
                new Edge(vertices, 4, 6, 6),
                new Edge(vertices, 5, 6, 1)
        ));
        kruskal(vertices.size(), queue);
    }

    /*
    <h1>Prim 最小生成树算法</h1>
     1.将所有顶点标记为未访问<br>
     2.设置临时距离:起点设为0,其余设为无穷大<br>
     3.每次选择最小临时距离的未访问点作为当前顶点<br>
     4.遍历当前顶点邻居,更新邻居的距离值 min(邻居距离,边权)<br>
     5.当前顶点处理完所有邻居后当前顶点设为已访问<br>
     与Dijkstra算法仅在第4步不同,距离不累加,而是表示距离上一个节点的距离
     */
    public static void prim(List<Vertex> graph, Vertex source) {
        ArrayList<Vertex> list = new ArrayList<>(graph);//未访问顶点
        source.distance = 0;
        while (!list.isEmpty()) {
            Vertex current = chooseMinDistanceVertex(list);
            updateNeighboursDistance(current);
            list.remove(current);
            current.visited = true;
        }
        for (Vertex vertex : graph) {
            System.out.println(vertex.name + " <- " + (vertex.prev != null ? vertex.prev.name : "null"));
        }
    }

    /**
     更新邻居距离

     @param current 当前节点
     */
    private static void updateNeighboursDistance(Vertex current) {
        for (Edge edge : current.edges) {
            Vertex n = edge.linked;
            if (!n.visited) { // list.contains(n)
                int newDistance = edge.weight;
                if (newDistance < n.distance) {
                    n.distance = newDistance;
                    n.prev = current;//记录最短路径时的上级
                }
            }
        }
    }

    /**
     寻找最小distance节点

     @param list 未访问节点
     @return 未访问节点中最小distance的节点
     */
    private static Vertex chooseMinDistanceVertex(ArrayList<Vertex> list) {
        Vertex min = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).distance < min.distance) {
                min = list.get(i);
            }
        }
        return min;
    }

    /*
   <h1>Kruskal 最小生成树算法</h1>
    1.将所有顶点设为"不连通"<br>
    2.将所有边按边权升序<br>
    3.每次选择最小权边,如果两端是不连通的,则将两端连通<br>

    */
    public static void kruskal(int size, PriorityQueue<Edge> queue) {
        List<Edge> list = new ArrayList<>();
        DisjointSet set = new DisjointSet(size);
        while (list.size() < size - 1) {
            Edge poll = queue.poll();//获取权重最小的边
            int i = set.find(poll.start);//查找集合的老大
            int j = set.find(poll.end);
            if (i != j) {//在一个集合里的所有元素的老大都是相等的,如果ij不等,说明start和end在两个集合中
                //没有连通
                list.add(poll);
                set.union(i, j);
            }
        }
        for (Edge edge:list){
            System.out.println(edge);
        }
    }

}
