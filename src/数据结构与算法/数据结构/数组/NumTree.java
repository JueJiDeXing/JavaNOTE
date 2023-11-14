package 数据结构与算法.数据结构.数组;

/**
 树状数组
 */
public class NumTree {
    int[] tree;

    int lowbit(int x) {
        return x & -x;
    }

    int query(int x) {//查询前缀和
        int ans = 0;
        for (int i = x; i > 0; i -= lowbit(i)) {
            ans += tree[i];
        }
        return ans;
    }

    void add(int x, int u) {// 在树状数组 x 位置中增加值 u
        for (int i = x; i <= n; i += lowbit(i)) {
            tree[i] += u;
        }
    }

    int[] nums;
    int n;

    public NumTree(int[] _nums) {
        nums = _nums;
        n = nums.length;
        tree = new int[n + 1];//从1开始
        for (int i = 0; i < n; i++) {
            add(i + 1, nums[i]);
        }
    }

    public void update(int i, int val) {//索引i位置改为val
        add(i + 1, val - nums[i]);
        nums[i] = val;
    }

    public int sumRange(int l, int r) {
        return query(r + 1) - query(l);//两前缀和相减
    }

}
