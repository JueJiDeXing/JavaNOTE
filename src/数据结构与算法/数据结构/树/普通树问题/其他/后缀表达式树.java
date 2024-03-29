package 数据结构与算法.数据结构.树.普通树问题.其他;

import 数据结构与算法.数据结构.树.树实现.Node.MyTreeNode;

import java.util.LinkedList;

public class 后缀表达式树 {
    /*
    输入后缀表达式,构建后缀表达式树
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
    public MyTreeNode<String> constructExpressionTree(String[] tokens) {
        LinkedList<MyTreeNode<String>> stack = new LinkedList<>();
        for (String t : tokens) {
            switch (t) {
                case "+", "-", "*", "/" -> {
                    //运算符
                    MyTreeNode<String> right = stack.pop();
                    MyTreeNode<String> left = stack.pop();
                    MyTreeNode<String> parent = new MyTreeNode<>(t);
                    parent.left = left;
                    parent.right = right;
                    stack.push(parent);
                }
                default -> stack.push(new MyTreeNode<>(t));//数字
            }
        }
        return stack.peek();
    }
}
