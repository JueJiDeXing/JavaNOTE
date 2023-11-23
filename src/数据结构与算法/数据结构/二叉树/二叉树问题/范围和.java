package 数据结构与算法.数据结构.二叉树.二叉树问题;

import 数据结构与算法.数据结构.二叉树.Node.MyTreeNode;

import java.util.LinkedList;

public class 范围和 {
    //中序遍历
    public int rangeSumBST1(MyTreeNode node, int low, int high) {
        MyTreeNode p = node;
        LinkedList<MyTreeNode> stack = new LinkedList<>();
        int sum = 0;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                MyTreeNode pop = stack.pop();
                int value = (int) pop.value;
                if (value > high) {//剪枝
                    break;
                }
                if (low <= value) {
                    sum += value;
                }

                p = pop.right;
            }
        }
        return sum;
    }

    //上下限递归
    public int rangeSumBST2(MyTreeNode node, int low, int high) {
        if (node == null) {
            return 0;
        }
        int value = (int) node.value;
        if (value < low) {
            return rangeSumBST2(node.right, low, high);
        }
        if (value > high) {
            return rangeSumBST2(node.left, low, high);
        }
        return value + rangeSumBST2(node.left, low, high) + rangeSumBST2(node.right, low, high);
    }
}
