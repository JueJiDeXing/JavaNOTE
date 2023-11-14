package 数据结构与算法.数据结构.图.最短路径;

import lombok.Data;

import java.util.*;


class Edge1 implements Comparable<Edge1> {
    public Vertex1 linked;//终点
    public int from;//起点距离
    public int to;//终点距离



    List<Vertex1> vertices;
    int start;
    public int weight;//边权
    int end;


    @Override
    public String toString() {
        return vertices.get(start).name + "<->" + vertices.get(end).name + "(" + weight + ")";
    }

    @Override
    public int compareTo(Edge1 o) {
        return Integer.compare(this.weight, o.weight);
    }
}

class Vertex1 {
    String name;
    List<Edge1> edge1s;//与顶点连接的边
    boolean visited = false;//是否被访问过
    int distance = Integer.MAX_VALUE;//和起点的距离
    Vertex1 prev = null;//上级(最短路径)

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex1 vertex1 = (Vertex1) o;
        return Objects.equals(name, vertex1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name + "(" + distance + ")";
    }

}

public class Dijkstra单源最短路径 {

    /**
     <h1>Dijkstra 单源最短路径算法</h1>
     1.将所有顶点标记为未访问<br>
     2.设置临时距离:起点设为0,其余设为无穷大<br>
     3.每次选择最小临时距离的未访问点作为当前顶点<br>
     4.遍历当前顶点邻居,更新邻居的距离值 min(邻居距离,当前顶点距离+边权)<br>
     5.当前顶点处理完所有邻居后当前顶点设为已访问<br>
     缺陷:不能处理负权重

     @param graph  图
     @param source 起点
     */
    public static void dijkstra(List<Vertex1> graph, Vertex1 source) {
        ArrayList<Vertex1> list = new ArrayList<>(graph);//未访问顶点
        source.distance = 0;
        while (!list.isEmpty()) {
            Vertex1 current = chooseMinDistanceVertex(list);
            updateNeighboursDistance(current);
            list.remove(current);
            current.visited = true;
        }
        for (Vertex1 vertex1 : graph) {
            System.out.println(vertex1.name + " " + vertex1.distance + " " + (vertex1.prev != null ? vertex1.prev.name : "null"));
        }

    }

    /**
     更新邻居距离

     @param current 当前节点
     */
    private static void updateNeighboursDistance(Vertex1 current) {
        for (Edge1 edge1 : current.edge1s) {
            Vertex1 n = edge1.linked;
            if (!n.visited) { // list.contains(n)
                int newDistance = current.distance + edge1.weight;
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
    private static Vertex1 chooseMinDistanceVertex(ArrayList<Vertex1> list) {
        Vertex1 min = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).distance < min.distance) {
                min = list.get(i);
            }
        }
        return min;
    }

    /**
     Dijkstra 最短路径算法<br>
     优化:使用优先级队列

     @param graph  图
     @param source 起点
     */
    public static void dijkstra2(List<Vertex1> graph, Vertex1 source) {
        PriorityQueue<Vertex1> queue = new PriorityQueue<>(Comparator.comparingInt(v -> v.distance));//未访问顶点
        source.distance = 0;
        for (Vertex1 vertex1 : graph) {
            queue.offer(vertex1);
        }
        while (!queue.isEmpty()) {
            Vertex1 current = queue.peek();
            updateNeighboursDistance2(current, queue);
            queue.poll();
            current.visited = true;
        }
        for (Vertex1 vertex1 : graph) {
            System.out.println(vertex1.name + " " + vertex1.distance + " " + (vertex1.prev != null ? vertex1.prev.name : "null"));
        }

    }

    /**
     更新邻居距离
     */
    private static void updateNeighboursDistance2(Vertex1 current, PriorityQueue<Vertex1> queue) {
        for (Edge1 edge1 : current.edge1s) {
            Vertex1 n = edge1.linked;
            if (!n.visited) { // list.contains(n)
                int newDistance = current.distance + edge1.weight;
                if (newDistance < n.distance) {
                    n.distance = newDistance;
                    n.prev = current;//记录最短路径时的上级
                    queue.offer(n);
                }
            }
        }
    }
}
