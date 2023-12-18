package 数据结构与算法.数据结构.二叉树.二叉树问题;

import 数据结构与算法.数据结构.二叉树.Node.TreeNode;

public class 镜像翻转 {
    //将二叉树镜像翻转

    //交换左右子树
    public TreeNode invertTree(TreeNode root) {
        fn(root);
        return root;
    }

    private static void fn(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        fn(node.left);
        fn(node.right);
    }
}
