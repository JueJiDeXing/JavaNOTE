package 数据结构与算法.数据结构.二叉树;


/**
 <div color=rgb(155,200,80)>
 <h1> 二叉树节点类</h1>
 </div>
 */
public class MyTreeNode {
    public Object value;
    public MyTreeNode left;
    public MyTreeNode right;

    public MyTreeNode(Object value) {
        this.value = value;
    }

    public MyTreeNode() {
    }

    public MyTreeNode(MyTreeNode left,Object value, MyTreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
