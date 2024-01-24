package 数据结构与算法.数据结构.链表.链表问题;

import 数据结构与算法.数据结构.链表.链表实现.ListNode;

/**
 升序链表合并
 */
public class 合并链表 {
    /**
     <div color=rgb(155,200,80)>
     <h1> 方法一:双指针</h1>
     双指针遍历链表,每次添加较小的元素至新链表尾部<br>
     </div>
     */
    public ListNode merge1(ListNode p1, ListNode p2) {
        ListNode s = new ListNode(-1, null);
        ListNode n = s;
        //取较小的节点添加到新链表尾部
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                n.next = p1;
                p1 = p1.next;
            } else {
                n.next = p2;
                p2 = p2.next;
            }
            n = n.next;
        }
        //把不为null的剩余链表接到新链表尾部
        if (p1 != null) {
            n.next = p1;
        }
        if (p2 != null) {
            n.next = p2;
        }
        return s.next;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1> 方法二:递归</h1>
     每次递归返回较小的节点,并让剩余节点与另一个链表再次递归<br>
     串起所有返回值
     </div>
     */
    public ListNode merge2(ListNode p1, ListNode p2) {
        if (p2 == null) {
            return p1;
        }
        if (p1 == null) {
            return p2;
        }
        if (p1.val < p2.val) {
            p1.next = merge2(p1.next, p2);//p1指向后续比较中的较小值
            return p1;
        } else {
            p2.next = merge2(p1, p2.next);
            return p2;
        }
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>合并k个有序链表</h1>
     </div>
     */
    public ListNode mergeK(ListNode[] listNodes) {
        if (listNodes.length == 0) {
            return null;
        }
        return split(listNodes,0, listNodes.length-1);
    }

    /**
     * @param listNodes 要合并的链表
     * @param i,j 左右边界
     * @return 返回合并后的链表
     */
    private ListNode split(ListNode[]listNodes,int i,int j){
        if (i==j){
            //数组内只有1个链表时退出
            return listNodes[i];
        }
        //有多个链表就分隔
        int m=(i+j)>>>1;
        ListNode left = split(listNodes, i, m);
        ListNode right = split(listNodes, m + 1, j);
        return merge1(left,right);//合并两个链表
    }
}

