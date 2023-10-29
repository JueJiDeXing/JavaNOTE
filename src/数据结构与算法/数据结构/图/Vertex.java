package 数据结构与算法.数据结构.图;

import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 顶点类
 */
@Data
public class Vertex {
    String name;
    List<Edge> edges;//与顶点连接的边
    boolean visited = false;//是否被访问过
    int inDegree;//入度
    int outDegree;//出度
    int status;//0-未访问 1-访问中 2-已访问
    int distance = Integer.MAX_VALUE;//和起点的距离
    Vertex prev = null;//上级(最短路径)

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(name, vertex.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return  name +"("+distance+")";
    }

    public Vertex(String name) {
        this.name = name;
    }

    public Vertex(String name, List<Edge> edges) {
        this.name = name;
        this.edges = edges;
    }
}
