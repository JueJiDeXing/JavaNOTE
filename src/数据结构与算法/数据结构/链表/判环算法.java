package 数据结构与算法.数据结构.链表;

/**
 判断链表是否有环
 */
public class 判环算法 {
    /**
     <div color=rgb(155,200,80)>
     <h1>龟兔赛跑法:判断链表是否有环</h1>
     龟一次走一步,兔一次走两步<br>
     如果兔追上龟,则有环,否则兔到达链表尾,无环<br>
     </div>
     */
    public boolean hasCircle1(ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                return true;
            }
        }
        return false;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>龟兔赛跑法:查找环的入口</h1>
     龟一次走一步,兔一次走两步<br>
     当龟兔相遇后,将龟放置在起点,然后龟兔都每次走一步,返回相遇时的节点<br>
     <br>
     数学证明:<br>
     设起点到环入口距离为a,环的长度为b<br>
     那么相遇时兔走了 a+n1圈+k, 龟走了 a+n2圈+k,(k是距离环入口的距离)<br>
     又因为兔的速度是龟的两倍,所以s龟=s兔-s龟 = n3圈 ,(所以a+k是n4圈,n4+n2=n3)<br>
     那么只要兔从k处再走a步就回到了环的入口处(a+n4圈+k+a = a+n5圈)<br>
     如果将龟放在起点,龟再走a步也会到达环的入口<br>
     综上:将龟从起点,兔从相遇点,都一次走一步,等到相遇时就是环的入口
     </div>
     */
    public ListNode hasCircle2(ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                p1 = head;
                while (true) {
                    if (p1==p2){
                        //先进行判断,可包括链表头尾相接的情况(p1,p2第一次相遇时就是head)
                        return p1;
                    }
                    p1 = p1.next;
                    p2 = p2.next;
                }
            }
        }
        return null;
    }
}
