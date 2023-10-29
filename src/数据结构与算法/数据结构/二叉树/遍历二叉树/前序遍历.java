package 数据结构与算法.数据结构.二叉树.遍历二叉树;

import 数据结构与算法.数据结构.二叉树.MyTreeNode;
import 数据结构与算法.数据结构.栈.MyLinkedListStackClass;

//先中 再左 后右
public class 前序遍历 {
    public static void main(String[] args) {
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
        preOrder(root);
        //preOrder_(root);
    }
    //递归版
    public static void preOrder(MyTreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.value + "\t");
        preOrder(node.left);
        preOrder(node.right);
    }
    /**
     非递归实现<br>
     遍历左子树,依次入栈(包括该节点),如果左子树走到头则弹出该节点,并从该节点的右子树开始再次遍历
     */
    private static void preOrder_(MyTreeNode root) {
        MyLinkedListStackClass<MyTreeNode> stack = new MyLinkedListStackClass<>();
        MyTreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {//左子树未遍历完
                //System.out.println("入栈---" + curr.value);//------前序遍历输出
                stack.push(curr);
                curr = curr.left;
            } else {//左边走到头
                MyTreeNode pop = stack.pop();
                System.out.println("弹栈---" + pop.value);//------中序遍历输出
                curr = pop.right;//开始遍历上个节点的右子树
                /* 后序遍历情况
                MyTreeNode peek = stack.peek();
                if (peek.right == null || peek.right == pop) {  //右子树为空 或者 右子树已经处理完
                    //后序遍历,如果有右子树则不弹栈,等到右子树处理完再弹
                    //  pop为最后一次弹出的元素,且为右节点, peek.right == pop 表示为当前节点的右子树已经被抛出,即已处理过
                    pop = stack.pop();
                    System.out.println("弹栈---" + pop.value);//------后序遍历输出
                } else {
                    curr = peek.right;
                }
                 */
            }
        }
    }



}
