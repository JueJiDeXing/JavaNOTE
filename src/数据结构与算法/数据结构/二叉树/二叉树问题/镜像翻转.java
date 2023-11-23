package 数据结构与算法.数据结构.二叉树.二叉树问题;

import 数据结构与算法.数据结构.二叉树.Node.MyTreeNode;

public class 镜像翻转 {
    //交换左右子树
    public MyTreeNode invertTree(MyTreeNode root) {
        fn(root);
        return root;
    }

    private static void fn(MyTreeNode node) {
        if (node == null) {
            return;
        }
        MyTreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        fn(node.left);
        fn(node.right);
    }
}
