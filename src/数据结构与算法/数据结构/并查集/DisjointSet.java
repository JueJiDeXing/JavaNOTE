package 数据结构与算法.数据结构.并查集;

import java.util.Arrays;

/**
 <h1>不相交集合（并查集合）</h1>
 */
public class DisjointSet {

    int[] s;
    // 索引对应顶点
    // 元素是用来表示与之有关系的顶点
    /*
        索引  0  1  2  3  4  5  6
        元素 [0, 1, 2, 3, 4, 5, 6] 表示一开始顶点直接没有联系（只与自己有联系）
    */

    public DisjointSet(int size) {
        s = new int[size];
        for (int i = 0; i < size; i++) {
            s[i] = i;
        }
    }

    // find 是找到老大 - 优化1：
    /*
        find(2) 0
          find(1) 0
           find(0)
     */
    public int find(int x) {
        if (x < 0 || x >= s.length) {
            throw new RuntimeException("索引越界");
        }
        if (x == s[x]) {//索引对应自身的为老大
            return x;
        }
        return s[x] = find(s[x]); //路径压缩,在查找中将老大的值赋值过来,下次在该集合里查找时路径缩短
    }

    // union 是让两个集合“相交”，即选出新老大，x、y 是原老大索引
    /*例:
    0 3 相连 -> union(0,3) -> 3索引对应值改为0 (令较小值为老大)
    5 6 相连 -> union(5,6) -> 6索引对应值改为5
    0 3再与5 6 相连 -> union(0,5) -> 5索引对应值改为0
     */
    public void union(int x, int y) {
        int rx = find(x), ry = find(y);
        s[ry] = rx;
    }

    @Override
    public String toString() {
        return Arrays.toString(s);
    }

    public boolean isConnect(int x, int y) {
        return find(x) == find(y);
    }
}


