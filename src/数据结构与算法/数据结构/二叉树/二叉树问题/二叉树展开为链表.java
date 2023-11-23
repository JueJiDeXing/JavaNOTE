package 数据结构与算法.数据结构.二叉树.二叉树问题;


import 数据结构与算法.数据结构.二叉树.Node.TreeNode;

public class 二叉树展开为链表 {
    //二叉搜索树,节点全部放右节点
    TreeNode next;

    public void flatten(TreeNode root) {
        if (root == null) return;
        //右左中的后序遍历
        flatten(root.right);
        flatten(root.left);
        root.left = null;
        root.right = next;
        next = root;
    }
}
