package 数据结构与算法.数据结构.队列;

import 数据结构与算法.数据结构.Node;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 <div color=rgb(155,200,80)>
 <h1>队列1:链表队列</h1>
 手写队列(链表实现)</div>
 */
public class LinkedListQueue<E> implements MyQueue<E>, Iterable<E> {


    Node<E> head = new Node<>(null, null);//哨兵节点(无值)
    Node<E> tail = head;//尾节点(最后一个节点)
    private int size;//节点数
    private int capacity = Integer.MAX_VALUE;//容量

    {
        tail.next = head;
    }

    public LinkedListQueue() {
    }

    public LinkedListQueue(int capacity) {
        this.capacity = capacity;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>向队列尾插入值</h1>
     </div>

     @param value 待插入值
     @return 是否插入成功
     */
    public boolean offer(E value) {
        if (isFull()) {//满队列
            return false;
        }
        Node<E> added = new Node<>(value, head);
        tail.next = added;
        tail = added;
        size++;
        return true;//考虑容量时可能返回flase
    }


    /**
     <div color=rgb(155,200,80)>
     <h1>抛出队列头节点</h1>
     获取头节点并从队列中移除这个节点</div>

     @return 返回队列头节点, 如果为空返回null
     */
    public E poll() {
        if (isEmpty()) {//空则返回null
            return null;
        }
        Node<E> first = head.next;
        head.next = first.next;//将头节点指向下一个节点
        if (first == tail) {//删除了仅有的一个节点
            tail = head;
        }
        size--;
        return  first.value;
    }


    /**
     <div color=rgb(155,200,80)>
     <h1>获取队列头节点值</h1>
     </div>

     @return 返回队列头部值, 如果为空返回null
     */
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return head.next.value;


    }


    /**
     <div color=rgb(155,200,80)>
     <h1>检查队列是否为空</h1>
     </div>

     @return 空队列返回true, 否则返回false
     */
    public boolean isEmpty() {
        return head == tail;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>检查队列是否已满</h1>
     </div>

     @return 满队列返回true, 否则返回false
     */
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = head.next;

            @Override
            public boolean hasNext() {
                return p != head;//走到head即为迭代结束
            }

            @Override
            public E next() {
                E value = p.value;
                p = p.next;
                return value;
            }
        };
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<E> spliterator() {
        return Iterable.super.spliterator();
    }
}


//--------------------------------------------------------------------------------------------


/**
 <div color=rgb(155,200,80)>
 <h1>队列2:环形数组队列</h1>
 手写队列(环形数组实现)</div>
 */
class ArrayQueue1<E> implements Iterable<E> {

    private E[] array;
    private int head = 0;
    private int tail = 0;

    @SuppressWarnings("all")
    public ArrayQueue1(int capacity) {
        array = (E[]) new Object[capacity + 1];//留一个空位给tail,方便判断空与满的情况
    }

    public ArrayQueue1() {
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>向队列尾插入值</h1>
     </div>

     @param value 待插入值
     @return 是否插入成功
     */
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[tail] = value;//插入值
        tail = (tail + 1) % array.length;
        return true;
    }


    /**
     <div color=rgb(155,200,80)>
     <h1>抛出队列头</h1>
     </div>

     @return 返回队列头部值, 如果为空返回null
     */
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E value = array[head];
        head = (head + 1) % array.length;//head指针右移(如果到数组尾则更新到数组头)
        return value;
    }


    /**
     <div color=rgb(155,200,80)>
     <h1>获取队列头</h1>
     </div>

     @return 返回队列头部值, 如果为空返回null
     */
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[head];
    }


    /**
     <div color=rgb(155,200,80)>
     <h1>检查队列是否为空</h1>
     </div>

     @return 空队列返回true, 否则返回false
     */
    public boolean isEmpty() {
        return head == tail;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>检查队列是否已满</h1>
     </div>

     @return 满队列返回true, 否则返回false
     */
    public boolean isFull() {
        return (tail + 1) % 5 == head;//tail前进一步等于head为满(因为最后一位是不存数据的)
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = head;

            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public E next() {
                E value = array[p];
                p = (p + 1) % array.length;
                return value;
            }
        };
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<E> spliterator() {
        return Iterable.super.spliterator();
    }
}

//--------------------------------------------------------------------------------------------


/**
 <div color=rgb(155,200,80)>
 <h1>环形数组队列优化1</h1>
 不再留一个空位给尾指针</div>
 */
class ArrayQueue2<E> implements Iterable<E> {

    private E[] array;
    private int head = 0;
    private int tail = 0;
    int size;//元素个数

    @SuppressWarnings("all")
    public ArrayQueue2(int capacity) {
        array = (E[]) new Object[capacity];//不留一个空位给tail,功能由size代替
    }

    public ArrayQueue2() {
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>向队列尾插入值</h1>
     </div>

     @param value 待插入值
     @return 是否插入成功
     */
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[tail] = value;//插入值
        tail = (tail + 1) % array.length;
        size++;
        return true;
    }


    /**
     <div color=rgb(155,200,80)>
     <h1>抛出队列头</h1>
     </div>

     @return 返回队列头部值, 如果为空返回null
     */
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E value = array[head];
        head = (head + 1) % array.length;//head指针右移(如果到数组尾则更新到数组头)
        size--;
        return value;
    }


    /**
     <div color=rgb(155,200,80)>
     <h1>获取队列头</h1>
     </div>

     @return 返回队列头部值, 如果为空返回null
     */
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[head];
    }


    /**
     <div color=rgb(155,200,80)>
     <h1>检查队列是否为空</h1>
     </div>

     @return 空队列返回true, 否则返回false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>检查队列是否已满</h1>
     </div>

     @return 满队列返回true, 否则返回false
     */
    public boolean isFull() {
        return size == array.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = head;
            int count = 0;//遍历的个数

            @Override
            public boolean hasNext() {
                //return p != tail;
                //因为没有留空位给tail,所以遍历完的时候头尾重合
                return count < size;
            }

            @Override
            public E next() {
                E value = array[p];
                p = (p + 1) % array.length;
                count++;
                return value;
            }
        };
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<E> spliterator() {
        return Iterable.super.spliterator();
    }
}
//--------------------------------------------------------------------------------------------


/**
 <div color=rgb(155,200,80)>
 <h1>环形数组队列优化2</h1>
 头尾指针不存储索引,而是直接增减<br>
 (int)Integer.toUnsignedLong(tail) % array.length 解决溢出问题<br>
 用X % 2^n = X & (2^n - 1)优化溢出解决方案</div>
 */
class ArrayQueue3<E> implements Iterable<E> {

    private E[] array;
    private int head = 0;
    private int tail = 0;

    @SuppressWarnings("all")
    public ArrayQueue3(int capacity) {//使容量为2^n次方
        //1.抛异常
//        if((capacity&capacity-1)!=0){
//            throw new RuntimeException("capacity必须是2的幂");
//        }

        //2.1改为最接近的2^n
//        capacity = ((int) (Math.log(capacity - 1) / Math.log(2)) + 1);
//        capacity = 1 << capacity;

        //2.2改为最接近的2^n
        capacity--;
        capacity = capacity | capacity >> 1;
        capacity = capacity | capacity >> 2;
        capacity = capacity | capacity >> 4;
        capacity = capacity | capacity >> 8;
        capacity = capacity | capacity >> 16;
        capacity++;

        array = (E[]) new Object[capacity];
    }

    public ArrayQueue3() {
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>向队列尾插入值</h1>
     </div>

     @param value 待插入值
     @return 是否插入成功
     */
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
//        array[(int)Integer.toUnsignedLong(tail) % array.length] = value;//新实现,tail本身不再为索引
        array[tail & array.length - 1] = value;//优化 X % 2^n = X & (2^n - 1)
        tail++;
        return true;
    }


    /**
     <div color=rgb(155,200,80)>
     <h1>抛出队列头</h1>
     </div>

     @return 返回队列头部值, 如果为空返回null
     */
    public E poll() {
        if (isEmpty()) {
            return null;
        }
//        E value = array[(int) Integer.toUnsignedLong(head) % array.length];
        E value = array[head & array.length - 1];
        head++;//新实现
        return value;
    }


    /**
     <div color=rgb(155,200,80)>
     <h1>获取队列头</h1>
     </div>

     @return 返回队列头部值, 如果为空返回null
     */
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[head];
    }


    /**
     <div color=rgb(155,200,80)>
     <h1>检查队列是否为空</h1>
     </div>

     @return 空队列返回true, 否则返回false
     */
    public boolean isEmpty() {
        return head == tail;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>检查队列是否已满</h1>
     </div>

     @return 满队列返回true, 否则返回false
     */
    public boolean isFull() {
        return tail - head == array.length;//无论tail有没有发生溢出,tail-head仍然是正确的
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = head;

            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public E next() {
//                E value = array[(int) Integer.toUnsignedLong(p) % array.length];
                E value = array[p & array.length - 1];
                p++;
                return value;
            }
        };
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<E> spliterator() {
        return Iterable.super.spliterator();
    }
}
