package 数据结构与算法.数据结构.二叉树.二叉树问题;

import 数据结构与算法.数据结构.二叉树.Node.MyTreeNodeInt;

public class 最近公共祖先 {
    //待查找节点p,q在某一节点r的两侧,那么r就是p,q的最近公共祖先(p或q就是r的话也算)
    // p<r<q
    public MyTreeNodeInt lowestCommonAncestor(MyTreeNodeInt root, MyTreeNodeInt p, MyTreeNodeInt q) {
        MyTreeNodeInt a = root;
        while ((p.value < a.value && q.value < a.value) || (p.value > a.value && q.value > a.value)) {
            //在同一侧则向下查找
            if (p.value < a.value) {
                a = a.left;
            } else {
                a = a.right;
            }
        }
        //退出循环 1.p与q在a的两侧 2.p或q与a相等
        return a;
    }


}
