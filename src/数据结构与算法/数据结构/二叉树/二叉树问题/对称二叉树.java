package 数据结构与算法.数据结构.二叉树.二叉树问题;

import 数据结构与算法.数据结构.二叉树.Node.MyTreeNode;

public class 对称二叉树 {
    public boolean isSymmetric(MyTreeNode root) {
        return check(root.left, root.right);
    }

    public boolean check(MyTreeNode left, MyTreeNode right) {
        if (left == null && right == null) {//都为null
            return true;
        }
        if (right == null || left == null) {//有一个为null
            return false;
        }
        //都不为null
        if (left.value != right.value) {
            return false;
        }
        return check(left.left, right.right) && check(left.right, right.left);
    }
}
