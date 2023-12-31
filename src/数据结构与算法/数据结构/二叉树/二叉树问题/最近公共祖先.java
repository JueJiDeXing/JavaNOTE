package 数据结构与算法.数据结构.二叉树.二叉树问题;

import 数据结构与算法.数据结构.二叉树.Node.TreeNode;

public class 最近公共祖先 {
    //给定一颗二叉树根节点root和内部节点p和q , 求节点p和q的公共祖先

    //二叉搜索树,待查找节点p,q在某一节点r的两侧,那么r就是p,q的最近公共祖先(p或q就是r的话也算)
    // p<r<q
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode a = root;
        while ((p.val < a.val && q.val < a.val) || (p.val > a.val && q.val > a.val)) {
            //在同一侧则向下查找
            if (p.val < a.val) {
                a = a.left;
            } else {
                a = a.right;
            }
        }
        //退出循环 1.p与q在a的两侧 2.p或q与a相等
        return a;
    }
}
