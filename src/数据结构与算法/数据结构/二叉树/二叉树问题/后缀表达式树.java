package 数据结构与算法.数据结构.二叉树.二叉树问题;

import 数据结构与算法.数据结构.二叉树.MyTreeNode;

import java.util.LinkedList;

public class 后缀表达式树 {
    /*
    示例:
    中缀:(2-1)*3   后缀:21-3*
    后缀转树:
         *
       -   3
      2 1

    转换方法:
        遇到数字入栈,遇到运算符出栈,建立节点关系再入栈
    测试方法:
        后序遍历二叉树,其结果为原后缀表达式
     */
    public MyTreeNode constructExpressionTree(String[] tokens) {
        LinkedList<MyTreeNode> stack = new LinkedList<>();
        for (String t : tokens) {
            switch (t) {
                case "+", "-", "*", "/" -> {
                    //运算符
                    MyTreeNode right = stack.pop();
                    MyTreeNode left = stack.pop();
                    MyTreeNode parent = new MyTreeNode(t);
                    parent.left = left;
                    parent.right = right;
                    stack.push(parent);
                }
                default -> {
                    stack.push(new MyTreeNode(t));//数字
                }
            }
        }
        return stack.peek();
    }
}
