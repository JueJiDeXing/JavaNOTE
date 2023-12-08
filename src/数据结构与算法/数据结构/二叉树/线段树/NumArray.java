package 数据结构与算法.数据结构.二叉树.线段树;

//TODO 未理解线段树的操作方法
public class NumArray {
    class Node {
        int left, right, val;

        Node(int l, int r) {
            left = l;
            right = r;
        }
    }

    Node[] tree;
    int[] nums;

    public NumArray(int[] _nums) {
        nums = _nums;
        int n = nums.length;
        tree = new Node[n * 4];
        build(1, 1, n);//建树
        for (int i = 0; i < n; i++) update(1, i + 1, nums[i]);
    }

    void build(int u, int l, int r) {
        tree[u] = new Node(l, r);
        if (l == r) return;
        int mid = l + r >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
    }

    void update(int u, int x, int v) {
        if (tree[u].left == x && tree[u].right == x) {
            tree[u].val += v;
            return;
        }
        int mid = tree[u].left + tree[u].right >> 1;
        if (x <= mid) update(u << 1, x, v);
        else update(u << 1 | 1, x, v);
        pushup(u);
    }

    int query(int u, int l, int r) {
        if (l <= tree[u].left && tree[u].right <= r) return tree[u].val;
        int mid = tree[u].left + tree[u].right >> 1;
        int ans = 0;
        if (l <= mid) ans += query(u << 1, l, r);
        if (r > mid) ans += query(u << 1 | 1, l, r);
        return ans;
    }

    void pushup(int u) {
        tree[u].val = tree[u << 1].val + tree[u << 1 | 1].val;
    }


    public void update(int index, int val) {
        update(1, index + 1, val - nums[index]);
        nums[index] = val;
    }

    public int sumRange(int left, int right) {
        return query(1, left + 1, right + 1);
    }
}

