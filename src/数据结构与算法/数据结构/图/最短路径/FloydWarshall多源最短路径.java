package 数据结构与算法.数据结构.图.最短路径;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Data;

import java.util.Objects;

class Edge3 implements Comparable<Edge1> {
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

class Vertex3 {
    String name;
    int distance = Integer.MAX_VALUE;//和起点的距离

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

public class FloydWarshall多源最短路径 {
    public static void main(String[] args) {
    }

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        /*
        给定一个无向、连通的树。树中有 n 个标记为 0...n-1 的节点以及 n-1 条边 。
        给定整数 n 和数组 edges ， edges[i] = [ai, bi]表示树中的节点 ai 和 bi 之间有一条边。
        返回长度为 n 的数组 answer ，其中 answer[i] 是树中第 i 个节点与所有其他节点之间的距离之和。
         */
        int[][] distance = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }
        for (int[] edge : edges) {
            distance[edge[0]][edge[1]] = 1;
            distance[edge[1]][edge[0]] = 1;
        }
        System.out.println(Arrays.deepToString(distance));
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distance[i][k] != Integer.MAX_VALUE && distance[k][j] != Integer.MAX_VALUE &&
                            distance[i][k] + distance[k][j] < distance[i][j]) {//如果借k到j比i直接到j更短则更新
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }
        System.out.println(Arrays.deepToString(distance));
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    ans[i] += distance[i][j];
                }
            }
        }
        System.out.println(Arrays.toString(ans));
        return ans;
    }

    /**
     <h1>Floyd-Warshall 多源最短路径算法</h1>
     1.创建邻接表<br>
     2.初始化,主对角线标0,有连接标上边权,无连接标为无穷<br>
     3.进行n轮循环,循环内Vj借助Vi到达其他顶点

     @param graph 图
     */
    public static void FloydWarshall(List<Vertex1> graph) {
        int size = graph.size();
        int[][] distance = new int[size][size];
        Vertex1[][] prev = new Vertex1[size][size];//记录最短路径的上级
        //初始化
        for (int i = 0; i < size; i++) {
            Vertex1 v = graph.get(i);
            Map<Vertex1, Integer> map = v.edge1s.stream().collect(Collectors.toMap(e -> e.linked, e -> e.weight));
            for (int j = 0; j < size; j++) {
                Vertex1 u = graph.get(j);
                if (v == u) {
                    distance[i][j] = 0;
                } else {
                    distance[i][j] = map.getOrDefault(u, Integer.MAX_VALUE);
                    prev[i][j] = map.get(u) != null ? v : null;
                }
            }
        }
        print(distance);
        /*借路示例:v2借v1到其他顶点
            v2->v1
          dist[1][0]  + dist[0][0] v1->v1
          dist[1][0]  + dist[0][1] v1->v2
          dist[1][0]  + dist[0][2] v1->v3
          dist[1][0]  + dist[0][3] v1->v4
         */
        for (int k = 0; k <= size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (distance[i][k] != Integer.MAX_VALUE && distance[k][j] != Integer.MAX_VALUE &&
                            distance[i][k] + distance[k][j] < distance[i][j]) {//如果借k到j比i直接到j更短则更新
                        distance[i][j] = distance[i][k] + distance[k][j];
                        prev[i][j] = prev[k][j];
                    }
                }
            }
        }
        for (int i = 0; i < size; i++) {
            //对角线小于0,节点借其他顶点到达自身路径比自身到达自身更短,说明有负环
            if (distance[i][i] < 0) {
                throw new RuntimeException("存在负环,无解");
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                path(prev, graph, i, j);
            }
        }

    }

    public static void print(int[][] arr) {
        for (int[] row : arr) {
            System.out.println(Arrays.stream(row).boxed()
                    .map(x -> x == Integer.MAX_VALUE ? "∞" : String.valueOf(x))
                    .map(x -> String.format("%2s", x))
                    .collect(Collectors.joining(",", "[", "]")));
        }
    }

    public static void print(Vertex1[][] prev) {
        System.out.println("-------------------");
        for (Vertex1[] row : prev) {
            System.out.println(Arrays.stream(row)
                    .map(x -> x == null ? "null" : x.name)
                    .map(x -> String.format("%5s", x))
                    .collect(Collectors.joining(",", "[", "]")));
        }
    }

    /**
     打印最短路径

     @param prev  最短路径表
     @param graph 图
     @param i     起点
     @param j     终点
     */
    public static void path(Vertex1[][] prev, List<Vertex1> graph, int i, int j) {
        LinkedList<String> stack = new LinkedList<>();
        System.out.print("[" + graph.get(i).name + "," + graph.get(j).name + "]");
        stack.push(graph.get(j).name);
        while (i != j) {
            Vertex1 p = prev[i][j];
            if (p == null) {
                stack.push("null");
                break;
            }
            stack.push(p.name);
            j = graph.indexOf(p);
        }
        System.out.println(stack);
    }
}



