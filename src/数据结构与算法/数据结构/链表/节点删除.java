package 数据结构与算法.数据结构.链表;


/**
 根据值删除所有等于这个值的节点
 */
public class 节点删除 {

    /**
     <div color=rgb(155,200,80)>
     <h1> 方法一:双指针</h1>
     双指针遍历链表,不断移除元素<br>
     </div>
     */
    public ListNode removeElements1(ListNode head, int val) {
        ListNode s = new ListNode(-1, head);//哨兵节点,简化删除第一个节点的情况
        ListNode p1 = s;
        ListNode p2;
        while ((p2 = s.next) != null) {//赋值
            if (p2.val == val) {
                p1.next = p2.next;//移除节点
            } else {
                p1 = p2;//更新位置
            }
        }
        return s.next;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1> 方法二:递归</h1>
     递归链表,不断忽略元素<br>
     </div>
     */
    public ListNode removeElements2(ListNode p, int val) {
        if (p == null) {
            return null;
        }
        if (p.val == val) {
            //遇到需要删除的节点,直接返回下一个节点的返回结果,自身不要
            return removeElements2(p.next, val);
        } else {
            //返回自身,并且自身的next指向后续的返回值
            p.next = removeElements2(p.next, val);
            return p;
        }
    }
}

/**
 删除倒数第n个节点(不需要考虑非法情况)
 */
class 删除倒数节点 {
    /**
     <div color=rgb(155,200,80)>
     <h1> 方法一:递归</h1>
     递归链表,根据返回值判断是倒数第几个元素<br>
     </div>
     */
    public ListNode removeElements1(ListNode head, int n) {
        ListNode s = new ListNode(-1, head);
        recursion(s, n);
        return head;
    }

    public int recursion(ListNode p, int n) {
        if (p == null) {
            return 0;
        }
        int nth = recursion(p.next, n);//找下一个节点的倒数位置
        //每个节点处理它的下一个节点的返回值
        if (nth == n) {
            //要删除的节点的返回值为nth
            //例:p=3  p.next=4  要删除4,需要让3指向5
            p.next = p.next.next;
        }
        return nth + 1;//最后p.next为null时(倒数第一个节点)返回0,倒数第二个节点返回1,倒数第三个节点返回2...
    }

    /**
     <div color=rgb(155,200,80)>
     <h1> 方法二:距离差</h1>
     双指针距离为n,后指针查找最后一个节点,删除前指针指向的节点<br>
     </div>
     */
    public ListNode removeElements2(ListNode head, int n) {
        ListNode s = new ListNode(-1, head);
        ListNode p1 = s;
        ListNode p2 = s;
        for (int i = 0; i < n + 1; i++) {
            p2 = p2.next;//移动n+1次,p1与p2中间相隔n个数
        }
        while (p2 != null) {
            p1 = p1.next;//同时移动一位
            p2 = p2.next;
        }
        p1.next = p1.next.next;//此时p2为null,倒数第n个数为p1.next
        return s.next;
    }
}

/**
 有序链表,重复的节点只保留一个
 */
class 节点去重 {
    /**
     <div color=rgb(155,200,80)>
     <h1> 方法一:双指针</h1>
     双指针顺序遍历链表<br>
     </div>
     */
    public ListNode delete1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p1 = head;
        ListNode p2;

        while ((p2 = p1.next) != null) {
            if (p1.val == p2.val) {
                p1.next = p2.next;//删除p2
            } else {
                p1 = p1.next;//平移p1,p2(因为p2已经在while的判断条件平移了,所以这里只平移p1)
            }
        }
        return head;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1> 方法二:递归</h1>
     与节点删除方法二相似<br>
     </div>
     */
    public ListNode delete2(ListNode p) {
        if (p == null || p.next == null) {
            return p;
        }
        if (p.val == p.next.val) {
            //返回下一个节点开始的去重结果,去除自身
            return delete2(p.next);
            /* 去除所有重复值,一个不留:
            ListNode x=p.next.next;
            while(x!=null && x.val=p.val){
                x=x.next;
            }
            return delete2(x);
             */
        } else {
            //未重复,返回自身,并且自身的next指向从下一个节点开始的去重链表
            p.next = delete2(p.next);
            return p;
        }
    }

    /**
     <div color=rgb(155,200,80)>
     <h1> 方法三:三指针</h1>
     跳过所有重复的部分,使前节点指向下一个不重复的节点<br>
     </div>
     */
    public ListNode delete3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode s = new ListNode(-1, head);
        ListNode p1 = s;
        ListNode p2,p3;
        // 移动p2,p3
        // 如果p3的值与p2相等,p3继续向后移动,如果不相等,p1指向p3
        while ((p2 = p1.next) != null && (p3 = p2.next) != null) {
            if (p2.val == p3.val) {
                while ((p3 = p3.next) != null && p3.val == p2.val) {
                    //已经写在判断里了,所以是空方法
                }
                //找到不重复的值,使p1指向p3这个不重复的项,即略过中间重复的节点
                p1.next = p3;
            } else {
                //p2与p3不相等,p1,p2,p3整体后移1位
                p1 = p1.next;
            }

        }
        return s.next;
    }
}
