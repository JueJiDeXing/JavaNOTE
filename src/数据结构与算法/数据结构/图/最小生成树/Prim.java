package 数据结构与算法.数据结构.图.最小生成树;

import 数据结构与算法.数据结构.图.Edge;
import 数据结构与算法.数据结构.图.Vertex;

import java.util.*;

public class Prim {
    /**
     <h1>Prim 最小生成树算法</h1>
     1.将所有顶点标记为未访问<br>
     2.设置临时距离:起点设为0,其余设为无穷大<br>
     3.每次选择最小临时距离的未访问点作为当前顶点<br>
     4.遍历当前顶点邻居,更新邻居的距离值 min(邻居距离,边权)<br>
     5.当前顶点处理完所有邻居后当前顶点设为已访问<br>
     与Dijkstra算法仅在第4步不同,距离不累加,而是表示距离上一个节点的距离
     */
    public void prim(List<Vertex> graph, Vertex source) {
        ArrayList<Vertex> list = new ArrayList<>(graph);//未访问顶点
        source.distance = 0;//起点临时距离设为0
        while (!list.isEmpty()) {
            Vertex current = chooseMinDistanceVertex(list);//选择最小的临时距离的未访问点
            updateNeighboursDistance(current);//更新它的邻居的距离
            list.remove(current);//遍历后移除节点,标记为已未访问
            current.visited = true;
        }
        for (Vertex vertex : graph) {
            System.out.println(vertex.name + " <- " + (vertex.prev != null ? vertex.prev.name : "null"));
        }
    }

    /**
     寻找最小distance节点

     @param list 未访问节点
     @return 未访问节点中最小distance的节点
     */
    private Vertex chooseMinDistanceVertex(ArrayList<Vertex> list) {
        Vertex min = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).distance < min.distance) {
                min = list.get(i);
            }
        }
        return min;
    }

    /**
     更新邻居距离

     @param current 当前节点
     */
    private void updateNeighboursDistance(Vertex current) {
        for (Edge edge : current.edges) {
            Vertex n = edge.linked;
            if (!n.visited) { // 未访问节点
                int newDistance = edge.weight;
                if (newDistance < n.distance) {
                    n.distance = newDistance;
                    n.prev = current;//记录最短路径时的上级
                }
            }
        }
    }

    //传统输入格式------------------------------------------------------------------------

    /**
     map[i][j]=w,表示顶点i,j之间有一条权重为w的边

     @param map 图中顶点的关系
     @param n   n个顶点
     */
    public void prim2(int[][] map, int n) {

    }
}
