package 数据结构与算法.数据结构.二叉树.二叉树问题;

import 数据结构与算法.数据结构.二叉树.Node.TreeNode;

public class 对称二叉树 {
    //判断二叉树是否对称

    public boolean isSymmetric(TreeNode root) {
        return check(root.left, root.right);
    }

    public boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) {//都为null
            return true;
        }
        if (right == null || left == null) {//有一个为null
            return false;
        }
        //都不为null
        if (left.val != right.val) {
            return false;
        }
        return check(left.left, right.right) && check(left.right, right.left);
    }
}
