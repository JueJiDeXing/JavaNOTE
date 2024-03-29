package 数据结构与算法.数据结构.树.树实现.Node;

public class AVLNode {
    public int key;
    public Object value;
    public AVLNode left;
    public AVLNode right;
    public int height = 1;//高度

    public AVLNode() {
    }

    public AVLNode(int key) {
        this.key = key;
    }

    public AVLNode(int key, Object value) {
        this.key = key;
        this.value = value;
    }

    public AVLNode(int key, Object value, AVLNode left, AVLNode right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }

}
