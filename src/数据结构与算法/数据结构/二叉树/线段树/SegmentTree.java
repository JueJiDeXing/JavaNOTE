package 数据结构与算法.数据结构.二叉树.线段树;

public class SegmentTree {
    //用于区间问题
    static class Node {
        public int left, right, mid, val, lazy;
        public Node leftChild;
        public Node rightChild;

        public Node(int l, int r) {
            left = l;
            right = r;
            mid = left + ((right - left) >> 1);
            val = 0;
            lazy = -1;
            leftChild = rightChild = null;
        }
    }

    public Node root;

    public SegmentTree(int left, int right) {
        root = new Node(left, right);
    }

    public void update(int left, int right, int val) {
        _update(left, right, val, root);
    }

    public int query(int left, int right) {
        return _query(left, right, root);
    }

    private void _update(int left, int right, int val, Node node) {
        if (node.left >= left && node.right <= right) {
            node.lazy = val;
            node.val = val * (node.right - node.left + 1);
            return;
        }
        if (node.right < left || node.left > right) return;
        _pushdown(node);
        if (left <= node.mid) {
            _update(left, right, val, node.leftChild);
        }
        if (right > node.mid) {
            _update(left, right, val, node.rightChild);
        }
        _pushup(node);
    }

    private int _query(int left, int right, Node node) {
        if (node.left >= left && node.right <= right) {
            return node.val;
        }
        if (node.right < left || node.left > right) {
            return 0;
        }
        _pushdown(node);
        int res_left = 0;
        int res_right = 0;
        if (left <= node.mid) {
            res_left = _query(left, right, node.leftChild);
        }
        if (right > node.mid) {
            res_right = _query(left, right, node.rightChild);
        }
        return res_left + res_right;
    }

    private void _pushup(Node node) {
        if (node.leftChild != null && node.rightChild != null) {
            node.val = node.leftChild.val + node.rightChild.val;
        }
    }

    private void _pushdown(Node node) {
        if (node.leftChild == null) {
            node.leftChild = new Node(node.left, node.mid);
        }
        if (node.rightChild == null) {
            node.rightChild = new Node(node.mid + 1, node.right);
        }
        int lazy = node.lazy;
        if (node.lazy == -1) return;
        node.leftChild.lazy = lazy;
        node.leftChild.val = lazy * (node.leftChild.right - node.leftChild.left + 1);
        node.rightChild.lazy = lazy;
        node.rightChild.val = lazy * (node.rightChild.right - node.rightChild.left + 1);
        node.lazy = -1;
    }
}
