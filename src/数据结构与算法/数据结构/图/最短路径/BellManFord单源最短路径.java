package 数据结构与算法.数据结构.图.最短路径;

import lombok.Data;

import java.util.List;
import java.util.Objects;

class Edge2 implements Comparable<Edge1> {
    public Vertex1 linked;//终点
    public int from;//起点距离
    public int to;//终点距离

    public Edge2(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

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

class Vertex2 {
    String name;

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

public class BellManFord单源最短路径 {
    /**
     <h1>BellManFord 单源最短路径算法</h1>
     1.设置临时距离:起点设为0,其余设为无穷大<br>
     2.对每条边进行标号(随机)<br>
     3.每轮对边的两端进行比较,如果 起点端+边权< 终点端 ,则更新终点端 ; 共需要进行n-1轮,其中n为顶点个数

     @param graph  图
     @param source 起点
     */
    public static void bellManFord(List<Vertex1> graph, Vertex1 source) {
        source.distance = 0;
        int size = graph.size();
        for (int i = 0; i < size; i++) { //进行n-1轮更新,第n轮检测负环
            for (Vertex1 s : graph) {
                for (Edge1 edge1 : s.edge1s) {
                    Vertex1 e = edge1.linked;
                    if (s.distance != Integer.MAX_VALUE && s.distance + edge1.weight < e.distance) {
                        if (i == size - 1) {//如果第n轮还能找到更短的说明有负环
                            throw new RuntimeException("存在负环,无解");
                        }
                        e.distance = s.distance + edge1.weight;
                        e.prev = s;
                    }
                }
            }
        }
        for (Vertex1 vertex1 : graph) {
            System.out.println(vertex1.name + " " + vertex1.distance + " " + (vertex1.prev != null ? vertex1.prev.name : "null"));
        }


    }
}
