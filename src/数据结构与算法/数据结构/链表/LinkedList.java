package 数据结构与算法.数据结构.链表;
//不连续地址存储
//每个节点存储自身的值与下一个节点的地址(双向链表还会存储上一个节点的地址)

//性能
//随机访问:根据index查找,时间复杂度O(n)
//插入删除:起始位置O(1)  结束位置O(n),双向链表O(1)   中间位置根据index查找时间+O(1)

import java.util.Iterator;
import java.util.function.Consumer;

public class LinkedList {
}

/**
 <div color=rgb(150,200,80)>
 <h1>单向链表</h1>
 手写单向链表源码(带哨兵)
 </div>
 */
class SinglyLinkedList implements Iterable<Integer> {
    //private Node head = null;//头指针
    private Node head = new Node(666, null);//哨兵节点作为头节点,可以省去判断链表为空的操作
    //哨兵节点索引为-1

    /**
     <div color=rgb(150,200,80)>
     <h1>索引非法异常</h1>
     </div>

     @param index 非法索引
     @return 返回报错信息
     */
    private static IllegalArgumentException IllegalIndex(int index) {
        return new IllegalArgumentException(
                String.format("index [%d] 不合法%n", index)
        );
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>添加节点到头部</h1>
     </div>

     @param value 添加的节点的值
     */
    public void addFirst(int value) {
        insert(0, value);
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>添加节点到尾部</h1>
     </div>

     @param value 添加的节点的值
     */
    public void addLast(int value) {
        //查找尾节点
        Node last = findLast();
//        if (last == null) {//链表为空,直接添加到头节点
//            addFirst(value);
//            return;
//        }
//由于存在哨兵节点,所以last不可能为null
        //使尾节点指向新节点
        last.next = new Node(value, null);
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>删除第一个节点</h1>
     </div>

     @throws IllegalArgumentException 链表为空,抛出index非法异常
     */
    public void removeFirst() {
        remove(0);
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>删除指定节点</h1>
     </div>

     @param index 要删除的索引位置节点
     @throws IllegalArgumentException 抛出index非法异常
     */
    public void remove(int index) {
        if (index == 0) {//索引为0,删除头节点
            removeFirst();
            return;
        }
        Node prev = findNode(index - 1);
        if (prev == null) {//找不到上一个节点
            throw IllegalIndex(index);
        }
        Node removed = prev.next;
        if (removed == null) {//被删除的节点不存在
            throw IllegalIndex(index);
        }
        prev.next = removed.next;//使上一个节点指向要删除的节点的下一个节点
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>查找最后一个节点</h1>
     </div>

     @return 返回最后一个节点
     */
    public Node findLast() {
//        if (head == null) {//空链表
//            return null;
//        }
//由于存在哨兵节点,所以head不可能为null
        Node p;
        for (p = head; p.next != null; p = p.next) {
        }
        return p;
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>遍历链表1</h1>
     while循环
     </div>

     @param consumer 对遍历的每个元素执行的函数
     */
    public void foreach1(Consumer<Integer> consumer) {
        Node p = head.next;//从哨兵节点的下一个节点开始遍历
        while (p != null) {//节点非空
            consumer.accept(p.value);//获取当前节点值
            p = p.next;//指向下一个节点
        }
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>遍历链表2</h1>
     for循环
     </div>

     @param consumer 对遍历的每个元素执行的函数
     */
    public void foreach2(Consumer<Integer> consumer) {
        for (Node p = head.next; p != null; p = p.next) {
            consumer.accept(p.value);
        }
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>遍历链表3</h1>
     迭代器(增强for)
     </div>
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node p = head.next;//从哨兵节点的下一个节点开始遍历

            @Override
            public boolean hasNext() {//是否有下一个元素
                return p != null;
            }

            @Override
            public Integer next() {//返回当前值,并把指针指向下一个节点
                int value = p.value;
                p = p.next;
                return value;
            }
        };
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>遍历链表4↓</h1>
     递归遍历
     </div>
     */
    public void loop(Consumer<Integer> before, Consumer<Integer> after) {
        recursion(head, before, after);
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>遍历链表4↑</h1>
     递归遍历
     </div>
     */
    public void recursion(Node curr, Consumer<Integer> before, Consumer<Integer> after) {
        if (curr == null) {
            return;
        }
        before.accept(curr.value);//正序执行
        recursion(curr.next, before, after);//依次出栈后执行下面的代码
        after.accept(curr.value);//利用栈的特性,实现前序遍历与后序遍历
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>查找节点</h1>
     通过索引访问节点
     </div>

     @param index 要查找的索引值
     @return 返回该索引的节点
     */
    private Node findNode(int index) {
        int i = -1;//哨兵节点索引为-1
        for (Node p = head; p != null; p = p.next, i++) {
            if (i == index) {
                return p;
            }
        }
        return null;//没有找到
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>查找元素</h1>
     通过索引访问节点的值
     </div>

     @param index 要查找的索引值
     @return 返回该索引节点的值
     @throws IllegalArgumentException 找不到元素,抛出index非法异常
     */
    public int get(int index) {
        Node node = findNode(index);
        if (node == null) {
            throw IllegalIndex(index);
        }
        return node.value;
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>插入元素</h1>
     通过索引插入节点的值
     </div>

     @param index 要查找的索引值
     @throws IllegalArgumentException 索引越界,抛出index非法异常
     */
    public void insert(int index, int value) {
//        if (index == 0) {
//            addFirst(value);
//            return;
//        }
        Node prev = findNode(index - 1);//上一个节点
        if (prev == null) {//找不到上一个节点
            throw IllegalIndex(index);
        }

        prev.next = new Node(value, prev.next);//新节点指向下一个节点,并使上一个节点指向该节点
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>节点类</h1>
     </div>
     */
    private static class Node {
        int value;//值
        Node next;//下一个节点

        /**
         <div color=rgb(150,200,80)>
         <h1>有参构造</h1>
         </div>

         @param value 值
         @param next  下一个节点
         */
        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
}

/**
 <div color=rgb(150,200,80)>
 <h1>双向链表</h1>
 手写双向链表源码(带哨兵)
 </div>
 */
class DoubleLinkedList implements Iterable<Integer> {
    private Node head;//头哨兵
    private Node tail;//尾哨兵
    //头哨兵节点索引为-1

    public DoubleLinkedList() {
        head = new Node(null, 666, null);
        tail = new Node(null, 888, null);
        head.next = tail;
        tail.prev = head;
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>索引非法异常</h1>
     </div>

     @param index 非法索引
     @return 返回报错信息
     */
    private static IllegalArgumentException IllegalIndex(int index) {
        return new IllegalArgumentException(
                String.format("index [%d] 不合法%n", index)
        );
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>添加节点到头部</h1>
     </div>

     @param value 添加的节点的值
     */
    public void addFirst(int value) {
        insert(0, value);
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>添加节点到尾部</h1>
     </div>

     @param value 添加的节点的值
     */
    public void addLast(int value) {
        Node prev = tail.prev;
        Node node = new Node(prev, value, tail);
        prev.next = node;
        tail.prev = node;
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>删除第一个节点</h1>
     </div>

     @throws IllegalArgumentException 链表为空,抛出index非法异常
     */
    public void removeFirst() {
        remove(0);
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>删除指定节点</h1>
     </div>

     @param index 要删除的索引位置节点
     @throws IllegalArgumentException 抛出index非法异常
     */
    public void remove(int index) {
        Node prev = findNode(index - 1);
        if (prev == null) {//找不到上一个节点
            throw IllegalIndex(index);
        }
        Node removed = prev.next;
        if (removed == tail) {//不能删除尾哨兵
            throw IllegalIndex(index);
        }
        Node next = removed.next;
        prev.next = next;
        next.prev = prev;
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>删除最后一个节点</h1>
     </div>

     @throws IllegalArgumentException 抛出index非法异常
     */
    public void removeLast() {
        Node removed = tail.prev;
        if (removed == head) {
            throw IllegalIndex(0);
        }
        Node prev = removed.prev;
        prev.next = tail;
        tail.prev = prev;
    }


    /**
     <div color=rgb(150,200,80)>
     <h1>遍历链表1</h1>
     while循环
     </div>

     @param consumer 对遍历的每个元素执行的函数
     */
    public void foreach1(Consumer<Integer> consumer) {
        Node p = head.next;//从哨兵节点的下一个节点开始遍历
        while (p != tail) {
            consumer.accept(p.value);//获取当前节点值
            p = p.next;//指向下一个节点
        }
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>遍历链表2</h1>
     for循环
     </div>

     @param consumer 对遍历的每个元素执行的函数
     */
    public void foreach2(Consumer<Integer> consumer) {
        for (Node p = head.next; p != tail; p = p.next) {
            consumer.accept(p.value);
        }
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>遍历链表3</h1>
     迭代器(增强for)
     </div>
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node p = head.next;//从哨兵节点的下一个节点开始遍历

            @Override
            public boolean hasNext() {//是否有下一个元素
                return p != tail;
            }

            @Override
            public Integer next() {//返回当前值,并把指针指向下一个节点
                int value = p.value;
                p = p.next;
                return value;
            }
        };
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>查找节点</h1>
     通过索引访问节点
     </div>

     @param index 要查找的索引值
     @return 返回该索引的节点
     */
    private Node findNode(int index) {
        int i = -1;//头哨兵节点索引为-1
        for (Node p = head; p != tail; p = p.next, i++) {
            if (i == index) {
                return p;
            }
        }
        return null;//没有找到
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>查找元素</h1>
     通过索引访问节点的值
     </div>

     @param index 要查找的索引值
     @return 返回该索引节点的值
     @throws IllegalArgumentException 找不到元素,抛出index非法异常
     */
    public int get(int index) {
        Node node = findNode(index);
        if (node == null) {
            throw IllegalIndex(index);
        }
        return node.value;
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>插入元素</h1>
     通过索引插入节点的值
     </div>

     @param index 要查找的索引值
     @throws IllegalArgumentException 索引越界,抛出index非法异常
     */
    public void insert(int index, int value) {
        Node prev = findNode(index - 1);//上一个节点
        if (prev == null) {
            throw IllegalIndex(index);
        }
        Node next = prev.next;//下一个节点

        Node Inserted = new Node(prev, value, next);//新指针
        prev.next = Inserted;
        next.prev = Inserted;

    }

    /**
     <div color=rgb(150,200,80)>
     <h1>双向节点类</h1>
     </div>
     */
    private static class Node {
        Node prev;
        int value;//值
        Node next;//下一个节点

        /**
         <div color=rgb(150,200,80)>
         <h1>有参构造</h1>
         </div>

         @param prev  上一个节点
         @param value 值
         @param next  下一个节点
         */
        public Node(Node prev, int value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }
}

/**
 <div color=rgb(150,200,80)>
 <h1>环链表</h1>
 手写环形链表源码(双向,带哨兵)
 </div>
 */
class CircleLinkedList implements Iterable<Integer> {
    private Node sentinel = new Node(null, 666, null);

    //头哨兵节点索引为-1

    public CircleLinkedList() {
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>索引非法异常</h1>
     </div>

     @param index 非法索引
     @return 返回报错信息
     */
    private static IllegalArgumentException IllegalIndex(int index) {
        return new IllegalArgumentException(
                String.format("index [%d] 不合法%n", index)
        );
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>添加节点到头部</h1>
     </div>

     @param value 添加的节点的值
     */
    public void addFirst(int value) {
        Node next = sentinel.next;
        Node node = new Node(sentinel, value, next);
        sentinel.next = node;
        next.prev = node;
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>添加节点到尾部</h1>
     </div>

     @param value 添加的节点的值
     */
    public void addLast(int value) {
        Node prev = sentinel.prev;
        Node node = new Node(prev, value, sentinel);
        prev.next = node;
        sentinel.prev = node;
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>删除第一个节点</h1>
     </div>

     @throws IllegalArgumentException 链表为空,抛出index非法异常
     */
    public void removeFirst() {
        remove(0);
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>删除指定节点</h1>
     </div>

     @param index 要删除的索引位置节点
     @throws IllegalArgumentException 抛出index非法异常
     */
    public void remove(int index) {
        Node prev = findByIndex(index - 1);
        if (prev == null) {//找不到上一个节点
            throw IllegalIndex(index);
        }
        Node removed = prev.next;
        if (removed == sentinel) {//不能删除尾哨兵
            throw IllegalIndex(index);
        }
        Node next = removed.next;
        prev.next = next;
        next.prev = prev;
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>删除最后一个节点</h1>
     </div>

     @throws IllegalArgumentException 抛出index非法异常
     */
    public void removeLast() {
        Node removed = sentinel.prev;
        if (removed == sentinel) {
            throw IllegalIndex(0);
        }
        Node prev = removed.prev;
        prev.next = sentinel;
        sentinel.prev = prev;
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>根据元素值删除节点</h1>
     </div>

     @param value 要删除的值
     @throws IllegalArgumentException 抛出index非法异常
     */
    public void removeByValue(int value) {
        Node removed = findByIndex(value);
        if (removed == null) {
            throw IllegalIndex(0);
        }
        Node prev = removed.prev;
        Node next = removed.next;
        prev.next = next;
        next.prev = prev;
    }


    /**
     <div color=rgb(150,200,80)>
     <h1>遍历链表1</h1>
     while循环
     </div>

     @param consumer 对遍历的每个元素执行的函数
     */
    public void foreach1(Consumer<Integer> consumer) {
        Node p = sentinel.next;//从哨兵节点的下一个节点开始遍历
        while (p != sentinel) {
            consumer.accept(p.value);//获取当前节点值
            p = p.next;//指向下一个节点
        }
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>遍历链表2</h1>
     for循环
     </div>

     @param consumer 对遍历的每个元素执行的函数
     */
    public void foreach2(Consumer<Integer> consumer) {
        for (Node p = sentinel.next; p != sentinel; p = p.next) {
            consumer.accept(p.value);
        }
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>遍历链表3</h1>
     迭代器(增强for)
     </div>
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node p = sentinel.next;//从哨兵节点的下一个节点开始遍历

            @Override
            public boolean hasNext() {//是否有下一个元素
                return p != sentinel;
            }

            @Override
            public Integer next() {//返回当前值,并把指针指向下一个节点
                int value = p.value;
                p = p.next;
                return value;
            }
        };
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>查找节点1</h1>
     通过索引访问节点
     </div>

     @param index 要查找的索引值
     @return 返回该索引的节点
     */
    private Node findByIndex(int index) {
        //TODO 这个方法未检查,可能有bug
        int i = 0;
        for (Node p = sentinel.next; p != sentinel; p = p.next, i++) {
            if (i == index) {
                return p;
            }
        }
        return null;//没有找到
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>查找节点2</h1>
     通过元素值访问节点
     </div>

     @param value 要查找的元素值
     @return 返回该索引的节点
     */
    private Node findByValue(int value) {
        Node p = sentinel.next;
        while (p != sentinel) {
            if (p.value == value) {
                return p;
            }
            p = p.next;
        }
        return null;//没有找到
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>查找元素</h1>
     通过索引访问节点的值
     </div>

     @param index 要查找的索引值
     @return 返回该索引节点的值
     @throws IllegalArgumentException 找不到元素,抛出index非法异常
     */
    public int get(int index) {
        Node node = findByIndex(index);
        if (node == null) {
            throw IllegalIndex(index);
        }
        return node.value;
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>插入元素</h1>
     通过索引插入节点的值
     </div>

     @param index 要查找的索引值
     @throws IllegalArgumentException 索引越界,抛出index非法异常
     */
    public void insert(int index, int value) {
        Node prev = findByIndex(index - 1);//上一个节点
        if (prev == null) {
            throw IllegalIndex(index);
        }
        Node next = prev.next;//下一个节点

        Node Inserted = new Node(prev, value, next);//新指针
        prev.next = Inserted;
        next.prev = Inserted;
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>双向节点类</h1>
     </div>
     */
    private static class Node {
        Node prev;
        int value;//值
        Node next;//下一个节点

        /**
         <div color=rgb(150,200,80)>
         <h1>有参构造</h1>
         </div>

         @param prev  上一个节点
         @param value 值
         @param next  下一个节点
         */
        public Node(Node prev, int value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }
}

