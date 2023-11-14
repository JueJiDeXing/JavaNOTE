package 数据结构与算法.数据结构.二叉树.遍历二叉树;

import 数据结构与算法.数据结构.二叉树.MyTreeNode;
import 数据结构与算法.数据结构.栈.MyLinkedListStackClass;

import java.util.ArrayList;
import java.util.List;

public class 后序遍历 {
    public static void main(String[] args) {
        /*
             1
          2    3
        4  5  6  7
        前序: 1 2 4 5 3 6 7
        中序: 4 2 5 1 6 3 7
        后序: 4 5 2 6 7 3 1
         */
        MyTreeNode root = new MyTreeNode(
                new MyTreeNode(
                        new MyTreeNode(4),
                        2,
                        new MyTreeNode(5)
                ),
                1,
                new MyTreeNode(
                        new MyTreeNode(6),
                        3,
                        new MyTreeNode(7)
                )
        );
        postOrder_(root);
    }

    //先左 后右 再中
    // 将节点垂直映射到水平线上
    public static void postOrder(MyTreeNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.value + "\t");
    }

    /**
     非递归实现<br>
     */
    private static void postOrder_(MyTreeNode root) {
        MyLinkedListStackClass<MyTreeNode> stack = new MyLinkedListStackClass<>();
        MyTreeNode curr = root;
        MyTreeNode pop = null;//最近一次的弹栈元素
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {//左子树未遍历完
                //System.out.println("入栈---" + curr.value);//------前序遍历输出
                stack.push(curr);
                curr = curr.left;
            } else {//左边走到头
                //后序遍历情况
                MyTreeNode peek = stack.peek();
                if (peek.right == null || peek.right == pop) {  //右子树为空 或者 右子树已经处理完
                    //后序遍历,如果有右子树则不弹栈,等到右子树处理完再弹
                    //  pop为最后一次弹出的元素,且为右节点, peek.right == pop 表示为当前节点的右子树已经被抛出,即已处理过
                    pop = stack.pop();
                    System.out.println("弹栈---" + pop.value);//------后序遍历输出
                } else {
                    curr = peek.right;
                }
                /* 中序遍历情况
                MyTreeNode pop = stack.pop();
                System.out.println("弹栈---" + pop.value);//------中序遍历输出
                curr = pop.right;//开始遍历上个节点的右子树
                */
            }
        }
    }

}

class 前中后 {
    public static List<List<Integer>> allOrder(MyTreeNode root) {
        List<List<Integer>> list_all = new ArrayList<>();
        List<Integer> list_pre = new ArrayList<>();
        List<Integer> list_in = new ArrayList<>();
        List<Integer> list_next = new ArrayList<>();
        MyLinkedListStackClass<MyTreeNode> stack = new MyLinkedListStackClass<>();
        MyTreeNode curr = root;
        MyTreeNode pop = null;//最近一次的弹栈元素
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                list_pre.add((Integer) curr.value);
                colorPrint("前: " + curr.value, 34);//------前序遍历输出,在左子树处理前
                //待处理左子树
                curr = curr.left;
            } else {
                MyTreeNode peek = stack.peek();
                //没有右子树
                if (peek.right == null) {
                    list_in.add((Integer) peek.value);
                    colorPrint("中: " + peek.value, 36);//--中序遍历输出,在左子树处理之后,右子树处理之前
                    pop = stack.pop();
                    list_next.add((Integer) pop.value);
                    colorPrint("后: " + pop.value, 35);//--后序遍历输出,在右子树处理之后
                }
                //右子树处理完了
                else if (peek.right == pop) {
                    pop = stack.pop();
                    list_next.add((Integer) pop.value);
                    colorPrint("后: " + pop.value, 35);//--后序遍历输出,在右子树处理之后
                }
                //待处理右子树
                else {
                    list_in.add((Integer) peek.value);
                    colorPrint("中: " + peek.value, 36);//--中序遍历输出,在左子树处理之后,右子树处理之前
                    curr = peek.right;
                }
            }
        }
        list_all.add(list_pre);
        list_all.add(list_in);
        list_all.add(list_next);
        return list_all;
    }

    public static void colorPrint(String print, int color) {
        System.out.printf("\033[%dm%s\033[0m %n", color, print);
    }
}



