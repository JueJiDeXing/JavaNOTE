package 数据结构与算法.数据结构.二叉树.遍历二叉树;

import 数据结构与算法.数据结构.二叉树.Node.MyTreeNode;
import 数据结构与算法.数据结构.队列.LinkedListQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//广度优先遍历
public class 层序遍历 {
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
        List<List<Integer>> result = Z字遍历.levelOrder(root);
        System.out.println(result);

    }

    /**
     <div color=rgb(155,200,80)>
     <h1>层序遍历</h1>
     按从左到右一层一层地遍历<br>
     使用队列</div>
     */
    public static List<List<Integer>> levelOrder(MyTreeNode root) {
        List<List<Integer>> result = new ArrayList<>();//存储层序遍历的结果
        if (root == null) {
            return result;
        }
        //每次从队列中获取元素,并把左右子树添加进队列
        LinkedListQueue<MyTreeNode> queue = new LinkedListQueue<>();
        queue.offer(root);
        int c1 = 1;//当前层数

        while (!queue.isEmpty()) {
            int c2 = 0;//下一层节点数
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < c1; i++) {
                MyTreeNode n = queue.poll();
                //System.out.print(n+" ");
                level.add((Integer) n.value);
                if (n.left != null) {
                    queue.offer(n.left);
                    c2++;//统计下一层节点数
                }
                if (n.right != null) {
                    queue.offer(n.right);
                    c2++;
                }
            }
            //System.out.println();//换行
            result.add(level);
            c1 = c2;//更新当前层的节点数
        }
        return result;
    }

}


/**
 <div color=rgb(155,200,80)>
 <h1>Z型层序遍历</h1>
 先从左向右,到下一层从右向左,又下一层再从左向右<br>
 使用双端队列</div>
 */
class Z字遍历 {
    public static List<List<Integer>> levelOrder(MyTreeNode root) {
        List<List<Integer>> result = new ArrayList<>();//存储层序遍历的结果
        if (root == null) {
            return result;
        }

        //每次从队列中获取元素,并把左右子树添加进队列
        LinkedListQueue<MyTreeNode> queue = new LinkedListQueue<>();
        queue.offer(root);
        int c1 = 1;//当前层数
        boolean odd = true;
        while (!queue.isEmpty()) {
            int c2 = 0;//下一层节点数
            LinkedList<Integer> level = new LinkedList<>();
            for (int i = 0; i < c1; i++) {
                MyTreeNode n = queue.poll();
                if (odd) {
                    level.offerFirst((Integer) n.value);//奇数层则添加到头部[7,6,5,4]
                } else {
                    level.offerLast((Integer) n.value);//偶数层则正常添加到尾部[2,3]
                }

                if (n.left != null) {
                    queue.offer(n.left);
                    c2++;//统计下一层节点数
                }
                if (n.right != null) {
                    queue.offer(n.right);
                    c2++;
                }
            }
            odd = !odd;//奇偶互换
            result.add(level);
            c1 = c2;//更新当前层的节点数
        }
        return result;
    }
}
