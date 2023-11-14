package 数据结构与算法.数据结构.并查集;

public class DisjointSetUnionByHeight {


    int[] parent;
    /**
     以 i 为根结点的子树的高度（引入了路径压缩以后该定义并不准确）
     */
    int[] rank;

    public DisjointSetUnionByHeight(int n) {
        this.parent = new int[n];
        this.rank = new int[n];
        for (int i = 0; i < n; i++) {
            this.parent[i] = i;
            this.rank[i] = 1;
        }
    }

    public void union(int x, int y) {
        int rootX = find(x), rootY = find(y);
        if (rootX == rootY) {
            return;
        }

        if (rank[rootX] == rank[rootY]) {
            parent[rootX] = rootY;
            // 此时以 rootY 为根结点的树的高度仅加了 1
            rank[rootY]++;
        } else if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
            // 此时以 rootY 为根结点的树的高度不变
        } else {
            // 同理，此时以 rootX 为根结点的树的高度不变
            parent[rootY] = rootX;
        }
    }

    public int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];

    }
}
