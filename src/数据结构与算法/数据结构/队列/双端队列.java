package 数据结构与算法.数据结构.队列;

import java.util.Iterator;

public class 双端队列 {
}

/**
 基于双向环形链表实现
 */
class LinkedListDeque<E> implements Deque<E>, Iterable<E> {

    static class Node<E> {
        Node<E> prev;
        E value;
        Node<E> next;

        public Node(Node<E> prev, E value, Node<E> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    int capacity;
    int size;
    Node<E> sentinel = new Node<>(null, null, null);

    public LinkedListDeque(int capacity) {
        this.capacity = capacity;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>向队列头插入值</h1>

     @param value 待插入值
     @return 是否插入成功
     */
    @Override
    public boolean offerFirst(E value) {
        if (isFull()) {
            return false;
        }
        Node<E> a = sentinel;
        Node<E> b = sentinel.next;
        Node<E> added = new Node<>(a, value, b);
        a.next = added;
        b.prev = added;
        size++;
        return true;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>向队列尾插入值</h1>

     @param value 待插入值
     @return 是否插入成功
     */
    @Override
    public boolean offerLast(E value) {
        if (isFull()) {
            return false;
        }
        Node<E> a = sentinel.prev;
        Node<E> b = sentinel;
        Node<E> added = new Node<>(a, value, b);
        a.next = added;
        b.prev = added;
        size++;
        return true;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>抛出队列头</h1>

     @return 返回队列头部值, 如果为空返回null
     */
    @Override
    public E pollFirst() {
        if (isEmpty()) {
            return null;
        }
        Node<E> a = sentinel;
        Node<E> removed = sentinel.next;
        Node<E> b = removed.next;
        a.next = b;
        b.prev = a;
        size--;
        return removed.value;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>抛出队列尾</h1>

     @return 返回队列头部值, 如果为空返回null
     */
    @Override
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        Node<E> b = sentinel;
        Node<E> removed = sentinel.prev;
        Node<E> a = removed.prev;
        a.next = b;
        b.prev = a;
        size--;
        return removed.value;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>获取队列头</h1>

     @return 返回队列头部值, 如果为空返回null
     */
    @Override
    public E peekFirst() {
        if (isEmpty()) {
            return null;
        }
        return sentinel.next.value;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>获取队列尾</h1>

     @return 返回队列尾部值, 如果为空返回null
     */
    @Override
    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return sentinel.prev.value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = sentinel.next;

            @Override
            public boolean hasNext() {
                return p != sentinel;
            }

            @Override
            public E next() {
                E value = p.value;
                p = p.next;
                return value;
            }
        };
    }


}
//---------------------------------------

/**
 基于循环数组实现1
 */
class ArrayDeque1<E> implements Deque<E>, Iterable<E> {

    /*
             h -> -1换算为3
          t    -> 留一个空位给tail
    0  1  2  3
    a  b     c  -> 示例:添加元素a,b到尾,再添加c到头

    添加到队列尾:添加到tail指针上,tail++
    添加到队列头:head--,添加到head指针上

    删除头:获取值,head++
    删除尾:tail--,获取值

    head==tail 空
    head ~ tail == 数组长-1 满
    */

    int head = 0;
    int tail = 0;
    E[] array;

    @SuppressWarnings("all")
    public ArrayDeque1(int capacity) {
        array = (E[]) new Object[capacity + 1];
    }

    /**
     两个工具方法,处理索引越界
     */
    static int inc(int i, int length) {
        if (i + 1 >= length) {
            return 0;//索引越界,恢复为0
        }
        return i + 1;//未越界,加1
    }

    static int dec(int i, int length) {
        if (i - 1 < 0) {
            return length - 1;//索引越界,恢复到数组尾
        }
        return i - 1;//未越界,减1
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>向队列头插入值</h1>

     @param value 待插入值
     @return 是否插入成功
     */
    @Override
    public boolean offerFirst(E value) {
        if (isFull()) {
            return false;
        }
        head = dec(head, array.length);//head指针左移,如果在数组头,则更新到数组尾
        array[head] = value;
        return true;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>向队列尾插入值</h1>

     @param value 待插入值
     @return 是否插入成功
     */
    @Override
    public boolean offerLast(E value) {
        if (isFull()) {
            return false;
        }
        array[tail] = value;
        tail = inc(tail, array.length);
        return true;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>抛出队列头</h1>

     @return 返回队列头部值, 如果为空返回null
     */
    @Override
    public E pollFirst() {
        if (isEmpty()) {
            return null;
        }
        E value = array[head];
        array[head]=null;
        head = inc(head, array.length);
        return value;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>抛出队列尾</h1>

     @return 返回队列头部值, 如果为空返回null
     */
    @Override
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        tail = dec(tail, array.length);
        E value = array[tail];
        array[tail]=null;
        return value;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>获取队列头</h1>

     @return 返回队列头部值, 如果为空返回null
     */
    @Override
    public E peekFirst() {
        if (isEmpty()) {
            return null;
        }
        return array[head];//直接获取头部值
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>获取队列尾</h1>

     @return 返回队列尾部值, 如果为空返回null
     */
    @Override
    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return array[dec(tail, array.length)];//尾部留有一个空位,需要获取tail减一的位置
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        //1. tail>head tail-head==array.length-1为满
        //2. head>tail head-tail==1
        if (tail > head) {
            return tail - head == array.length - 1;
        } else if (tail < head) {
            return head - tail == 1;
        } else {
            return false;
        }
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = head;

            @Override
            public boolean hasNext() {
                return p !=tail;
            }

            @Override
            public E next() {
                E value = array[p];
                p = inc(p,array.length);
                return value;
            }
        };
    }


}
