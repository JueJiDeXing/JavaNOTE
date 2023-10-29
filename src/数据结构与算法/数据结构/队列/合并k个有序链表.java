package 数据结构与算法.数据结构.队列;


import 数据结构与算法.数据结构.链表.ListNode;


//使用堆实现
public class 合并k个有序链表 {
    public ListNode mergeKLists(ListNode[] lists) {
        MinHeap heap = new MinHeap(lists.length);
        //链表头节点加入小顶堆
        for (ListNode h : lists) {
            if (h != null) {
                heap.offer(h);
            }
        }
        ListNode s = new ListNode(-1, null);//哨兵
        ListNode t = s;//尾指针
        while (!heap.isEmpty()) {//从堆顶(最小)移除元素,并添加到新链尾
            ListNode min = heap.poll();
            t.next = min;
            t = min;
            if (min.next != null) {
                heap.offer(min.next);//把移除的节点的下一个节点添加到堆
            }
        }
        return s.next;
    }
}

class MinHeap {
    ListNode[] array;
    int size;

    public MinHeap(int capacity) {
        this.array = new ListNode[capacity];
    }

    public boolean offer(ListNode offered) {
        if (isFull()) {
            return false;
        }
        int child = size++;
        int parent = (child - 1) / 2;
        while (child > 0 && offered.val < array[parent].val) {
            array[child] = array[parent];//比父节点大,将父节点向下移
            child = parent;
            parent = (parent - 1) / 2;
        }
        array[child] = offered;//插入
        return true;
    }


    public ListNode poll() {
        if (isEmpty()) {
            return null;
        }
        swap(0, size - 1);//交换头元素与尾元素再将尾元素抛出
        size--;
        ListNode e = array[size];
        array[size] = null;

        down(0);//下潜

        return e;
    }

    private void down(int parent) {//优先级低的元素下潜
        int left = 2 * parent + 1;
        int right = left + 1;
        int min = parent;//寻找 父,左,右 三者较大<!--小顶堆,查找最小-->的优先级
        if (left < size && array[left].val < array[min].val) {
            min = left;
        }
        if (right < size && array[right].val < array[min].val) {
            min = right;
        }
        if (min != parent) {//如果子节点比父节点优先级大
            swap(parent, min);// 交换
            down(min);//递归,直到max==parent,父节点大于左右子节点时停止
        }
    }

    private void swap(int i, int j) {//交换
        ListNode e = array[i];
        array[i] = array[j];
        array[j] = e;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }
}



