package 数据结构与算法.数据结构.链表;

public class 奇偶链表 {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        public ListNode() {
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            ListNode p = this;
            StringBuilder sb = new StringBuilder();
            while (p != null) {
                sb.append(p.val).append("->");
                p = p.next;
            }
            sb.append("null");
            return sb.toString();
        }
    }

    ListNode createLinkedList(int[] values) {
        ListNode head = new ListNode(0);
        head.next = null;
        ListNode p = head;
        for (int value : values) {
            p.next = new ListNode(value);
            p = p.next;
        }
        return head.next;
    }

    public static void main(String[] args) {
        奇偶链表 test = new 奇偶链表();
        System.out.println(test.oddEvenList(test.createLinkedList(new int[]{1, 2, 3, 4, 5, 6, 7, 8})));
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        ListNode p = head;
        ListNode prev;
        ListNode odd = head;
        while (true) {
            odd = odd.next;
            if (odd == null) break;
            prev = odd;
            odd = odd.next;
            if (odd == null) break;
            prev.next = odd.next;
            odd.next = p.next;
            p.next = odd;
            p = odd;
            odd = prev;
        }
        return head;
    }
}
