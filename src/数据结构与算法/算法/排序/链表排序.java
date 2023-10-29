package 数据结构与算法.算法.排序;

public class 链表排序 {
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class 归并 {
    public ListNode sortList(ListNode head) {
        return doSort(head, null);
    }

    public ListNode doSort(ListNode head, ListNode tail) {
        // 2.治
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        // 1.分
        //快慢指针找中间节点
        ListNode slow = head;
        ListNode fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode L1 = doSort(head, slow);
        ListNode L2 = doSort(slow, tail);
        // 3.合
        return merge(L1, L2);
    }

    public ListNode merge(ListNode L1, ListNode L2) {
        ListNode head = new ListNode(0);//哨兵头节点
        ListNode p1 = L1;
        ListNode p2 = L2;
        ListNode p3 = head;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                p3.next = p1;
                p1 = p1.next;
            } else {
                p3.next = p2;
                p2 = p2.next;
            }
            p3 = p3.next;
        }
        if (p1 != null) {
            p3.next = p1;
        } else {
            p3.next = p2;
        }
        return head.next;

    }
}
