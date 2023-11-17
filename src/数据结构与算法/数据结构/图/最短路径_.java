package 数据结构与算法.数据结构.图;

import java.util.*;
import java.util.stream.Collectors;
/*
有向图
 */
public class 最短路径_ {
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
    public static void dijkstra(List<Vertex> graph, Vertex source) {
        ArrayList<Vertex> list = new ArrayList<>(graph);//未访问顶点
        source.distance = 0;
        while (!list.isEmpty()) {
            Vertex current = chooseMinDistanceVertex(list);
            updateNeighboursDistance(current);
            list.remove(current);
            current.visited = true;
        }
        for (Vertex vertex : graph) {
            System.out.println(vertex.name + " " + vertex.distance + " " + (vertex.prev != null ? vertex.prev.name : "null"));
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
                int newDistance = current.distance + edge.weight;
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

    /**
     Dijkstra 最短路径算法<br>
     优化:使用优先级队列

     @param graph  图
     @param source 起点
     */
    public static void dijkstra2(List<Vertex> graph, Vertex source) {
        PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(v -> v.distance));//未访问顶点
        source.distance = 0;
        for (Vertex vertex : graph) {
            queue.offer(vertex);
        }
        while (!queue.isEmpty()) {
            Vertex current = queue.peek();
            updateNeighboursDistance2(current, queue);
            queue.poll();
            current.visited = true;
        }
        for (Vertex vertex : graph) {
            System.out.println(vertex.name + " " + vertex.distance + " " + (vertex.prev != null ? vertex.prev.name : "null"));
        }

    }

    /**
     更新邻居距离
     */
    private static void updateNeighboursDistance2(Vertex current, PriorityQueue<Vertex> queue) {
        for (Edge edge : current.edges) {
            Vertex n = edge.linked;
            if (!n.visited) { // list.contains(n)
                int newDistance = current.distance + edge.weight;
                if (newDistance < n.distance) {
                    n.distance = newDistance;
                    n.prev = current;//记录最短路径时的上级
                    queue.offer(n);
                }
            }
        }
    }

    /**
     <h1>BellManFord 单源最短路径算法</h1>
     1.设置临时距离:起点设为0,其余设为无穷大<br>
     2.对每条边进行标号(随机)<br>
     3.每轮对边的两端进行比较,如果 起点端+边权< 终点端 ,则更新终点端 ; 共需要进行n-1轮,其中n为顶点个数

     @param graph  图
     @param source 起点
     */
    public static void bellManFord(List<Vertex> graph, Vertex source) {
        source.distance = 0;
        int size = graph.size();
        for (int i = 0; i < size; i++) { //进行n-1轮更新,第n轮检测负环
            for (Vertex s : graph) {
                for (Edge edge : s.edges) {
                    Vertex e = edge.linked;
                    if (s.distance != Integer.MAX_VALUE && s.distance + edge.weight < e.distance) {
                        if (i == size - 1) {//如果第n轮还能找到更短的说明有负环
                            throw new RuntimeException("存在负环,无解");
                        }
                        e.distance = s.distance + edge.weight;
                        e.prev = s;
                    }
                }
            }
        }
        for (Vertex vertex : graph) {
            System.out.println(vertex.name + " " + vertex.distance + " " + (vertex.prev != null ? vertex.prev.name : "null"));
        }


    }

    /**
     <h1>Floyd-Warshall 多源最短路径算法</h1>
     1.创建邻接表<br>
     2.初始化,主对角线标0,有连接标上边权,无连接标为无穷<br>
     3.进行n轮循环,循环内Vj借助Vi到达其他顶点

     @param graph 图
     */
    public static void FloydWarshall(List<Vertex> graph) {
        int size = graph.size();
        int[][] distance = new int[size][size];
        Vertex[][] prev = new Vertex[size][size];//记录最短路径的上级
        //初始化
        for (int i = 0; i < size; i++) {
            Vertex v = graph.get(i);
            Map<Vertex, Integer> map = v.edges.stream().collect(Collectors.toMap(e -> e.linked, e -> e.weight));
            for (int j = 0; j < size; j++) {
                Vertex u = graph.get(j);
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
        ;
    }

    public static void print(int[][] arr) {
        //System.out.print("[");
        //for (int i = 0; i < arr.length; i++) {
        //    if (i == 0) {
        //        System.out.print("[");
        //    } else {
        //        System.out.print(" [");
        //    }
        //    for (int j = 0; j < arr[0].length; j++) {
        //        System.out.print(arr[i][j] == Integer.MAX_VALUE ? "∞" : arr[i][j]);
        //        if (j != arr[0].length - 1) {
        //            System.out.print(",");
        //        }
        //    }
        //    System.out.print("]");
        //    if (i != arr.length - 1) {
        //        System.out.println();
        //    }
        //}
        //System.out.println("]");
        System.out.println("-------------------");
        for (int[] row : arr) {
            System.out.println(Arrays.stream(row).boxed()
                    .map(x -> x == Integer.MAX_VALUE ? "∞" : String.valueOf(x))
                    .map(x -> String.format("%2s", x))
                    .collect(Collectors.joining(",", "[", "]")));
        }
    }

    public static void print(Vertex[][] prev) {
        System.out.println("-------------------");
        for (Vertex[] row : prev) {
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
    public static void path(Vertex[][] prev, List<Vertex> graph, int i, int j) {
        LinkedList<String> stack = new LinkedList<>();
        System.out.print("[" + graph.get(i).name + "," + graph.get(j).name + "]");
        stack.push(graph.get(j).name);
        while (i != j) {
            Vertex p = prev[i][j];
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

