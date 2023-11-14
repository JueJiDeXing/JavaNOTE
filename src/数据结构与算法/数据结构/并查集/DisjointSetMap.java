package 数据结构与算法.数据结构.并查集;

import java.util.HashMap;
import java.util.Map;

public class DisjointSetMap {
    Map<Integer, Integer> father;//使用Map映射

    public DisjointSetMap() {
        father = new HashMap<>();
    }

    public void add(int x) {
        if (father.containsKey(x)) {
            return;
        }
        father.put(x, null);
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            father.put(rootX, rootY);
        }
    }


    public int find(int x) {
        int root = x;
        while (father.get(root) != null) {//查找root
            root = father.get(root);
        }
        while (x != root) {//将路径上的点归并到root集合
            int curFather = father.get(x);
            father.put(x, root);
            x = curFather;
        }
        return root;
    }

    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }
}
