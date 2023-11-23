package 数据结构与算法.数据结构.队列;

/**
 每个元素都有优先级权重,出队列时按优先级出队
 */
public class 优先级队列 {
}

/**
 基于无序数组实现
 */
class PriorityQueue1<E extends Priority> implements MyQueue<E> {
    /*
    入队:直接添加
    出队:选择排序找到优先级最高的元素出队
     */
    Priority[] array;
    int size;

    public PriorityQueue1(int capacity) {
        array = new Priority[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[size++] = value;
        return true;
    }

    /**
     @return 返回优先级最高的索引值
     */
    private int selectMax() {
        int max = 0;
        for (int i = 0; i < size; i++) {
            if (array[i].priority() > array[max].priority()) {
                max = i;
            }
        }
        return max;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        int max = selectMax();
        E value = (E) array[max];
        remove(max);
        return value;
    }

    private void remove(int index) {
        array[index] = null;//置空
        if (index < size - 1) {//不是最后一个元素
            //移动
            System.arraycopy(array, index + 1, array, index, size - index - 1);
        }
        size--;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        int index = selectMax();
        return (E) array[index];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }
}
//-----------------------------------------------------------------------

/**
 基于有序数组实现
 */
class PriorityQueue2<E extends Priority> implements MyQueue<E> {
    /*
    入队:入队时插入排序查找插入位置
    出队:数组尾元素出队
     */
    Priority[] array;
    int size;

    public PriorityQueue2(int capacity) {
        array = new Priority[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        insert(value);
        size++;
        return true;
    }

    private void insert(E value) {
        int i = size - 1;
        while (i >= 0 && array[i].priority() > value.priority()) {
            array[i + 1] = array[i];//向后移
            i--;
        }
        array[i + 1] = value;//插入
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E value = (E) array[size - 1];
        array[--size] = null;
        return value;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return (E) array[size - 1];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }
}
//-----------------------------------------------------------------------

/**
 <h1>基于<b>大顶堆</b>实现</h1>
 堆是一种基于树的结构,通常用完全二叉树实现,完全二叉树可以使用线性数组实现<br><br>
 如果从索引0开始存储节点:节点i的父节点为floor((i-1)/2)  [i>0] ,<br>
 左子节点为2i+1,右子节点为2i+2  [index < size]<br>
 如果从索引1开始存储节点:节点i的父节点为floor(i/2)  [i>0] ,<br>
 左子节点为2i,右子节点为2i+1 [index < size]
 */
class PriorityQueue3<E extends Priority> implements MyQueue<E> {
    /*
    入队:依次与其父节点比较,寻找插入点
    出队:将根与数组尾节点交换,再重新调整队列(把较大的子节点向上移,元素下潜)
     */
    Priority[] array;
    int size;

    public PriorityQueue3(int capacity) {
        array = new Priority[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        int child = size++;//从最底层(小)向上(大)找插入位置
        int parent = (child - 1) / 2;
        while (child > 0 && value.priority() > array[parent].priority()) {
            array[child] = array[parent];//比父节点大,父节点优先级小,将父节点向下移
            child = parent;
            parent = (parent - 1) / 2;
        }
        array[child] = value;//插入
        return true;
    }


    @Override
    public E poll() {//抛出堆顶元素(优先级最高)
        if (isEmpty()) {
            return null;
        }
        swap(0, size - 1);//交换头元素与尾元素再将尾元素抛出
        size--;
        Priority value = array[size];
        array[size] = null;
        down(0);//下潜

        return (E) value;
    }

    private void down(int parent) {//优先级小的元素下潜
        int left = 2 * parent + 1;
        int right = left + 1;
        int max = parent;//大顶堆,寻找 父,左,右 三者较大的优先级
        if (left < size && array[left].priority() > array[max].priority()) {
            max = left;
        }
        if (right < size && array[right].priority() > array[max].priority()) {
            max = right;
        }
        if (max != parent) {//如果子节点比父节点优先级大
            swap(parent, max);// 交换
            down(max);//递归,直到max==parent,父节点大于左右子节点时停止
        }
    }

    private void swap(int i, int j) {//交换
        Priority t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return (E) array[0];//返回堆顶元素
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }
}
