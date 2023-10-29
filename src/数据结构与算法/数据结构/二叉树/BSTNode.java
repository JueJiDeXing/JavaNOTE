package 数据结构与算法.数据结构.二叉树;

public class BSTNode {
    int key;//使用key比较大小
    Object value;
    BSTNode left;
    BSTNode right;

    public BSTNode() {
    }

    public BSTNode(int key, Object value, BSTNode left, BSTNode right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;

    }

    public BSTNode(int key, Object value) {
        this.key = key;
        this.value = value;
    }

    public BSTNode(int key) {
        this.key = key;
    }
}
class BSTNode_T<K extends Comparable<K>,V> {
    K key;//使用key比较大小
    V value;
    BSTNode_T<K,V> left;
    BSTNode_T<K,V> right;

    public BSTNode_T() {
    }

    public BSTNode_T(K key, V value, BSTNode_T<K,V>  left, BSTNode_T<K,V>  right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public BSTNode_T(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public BSTNode_T(K key) {
        this.key = key;
    }
}
