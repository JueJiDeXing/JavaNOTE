package 数据结构与算法.数据结构.二叉树.二叉树问题;

import 数据结构与算法.数据结构.二叉树.MyTreeNode;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicLong;

public class 搜索树校验 {

    // 中序遍历-非递归
    public static boolean isValidBST1(MyTreeNode node) {
        MyTreeNode p = node;
        LinkedList<MyTreeNode> stack = new LinkedList<>();
        long prev = Long.MIN_VALUE;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                MyTreeNode pop = stack.pop();
                if (prev >= (int) pop.value) {
                    return false;
                }
                prev = (int) pop.value;
                p = pop.right;
            }
        }
        return true;
    }

    // 中序遍历-全局变量-递归实现
    static Long prev = Long.MIN_VALUE;

    public static boolean isValidBST2(MyTreeNode node) {
        if (node == null) {
            return true;
        }
        boolean isValidLeft = isValidBST2(node.left);
        if (!isValidLeft) {
            return false;
        }
        if (prev >= (int) node.value) {
            //在多路递归里,如果把这里的prev作为参数传递,会造成变量不同步
            // 解决方案: 把prev设为全局变量 或 把prev设置为对象类型new AtomicLong(Long.MIN_VALUE)
            return false;
        }
        prev = (Long) node.value;
        return isValidBST2(node.right);
    }

    // 中序遍历-非全局变量-递归实现
    public static boolean isValidBST3(MyTreeNode node) {
        return doValid3(node, new AtomicLong(Long.MIN_VALUE));
    }

    private static boolean doValid3(MyTreeNode node, AtomicLong prev) {
        if (node == null) {
            return true;
        }
        boolean isValidLeft = doValid3(node.left, prev);
        if (!isValidLeft) {
            return false;
        }
        if (prev.get() >= (int) node.value) {
            //在多路递归里,如果把这里的prev作为参数传递,会造成变量不同步
            // 解决方案: 把prev设为全局变量 或 把prev设置为对象类型new AtomicLong(Long.MIN_VALUE)
            return false;
        }
        prev.set((long) node.value);
        return doValid3(node.right, prev);
    }

    // 上下限递归
    public boolean isValidBST4(MyTreeNode node) {

        return doValid4(node, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean doValid4(MyTreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        if ((int) node.value <= min || (int) node.value >= max) {
            return false;
        }
        return doValid4(node.left, min, (long) node.value) && doValid4(node.right, (long) node.value, max);
    }
}
