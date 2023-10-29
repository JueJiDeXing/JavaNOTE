package 数据结构与算法.数据结构.堆;

import static 数据结构与算法.数据结构.堆.MaxHeap.IllegalIndex;

/**
 <h1>小顶堆</h1>
 每个子堆的堆顶为堆的最小值
 */
public class MinHeap {
    int[] array;
    int size;

    public MinHeap(int capacity) {
        this.array = new int[capacity];
    }

    public boolean offer(int offered) {
        if (isFull()) {
            return false;
        }
        int child = size++;//child:插入位置
        int parent = (child - 1) / 2;//父节点索引=(子节点索引-1)/2
        while (child > 0 && offered < array[parent]) {//比父节点小
            array[child] = array[parent];//将父节点向下移,插入位置变为父节点位置,再次比较
            child = parent;
            parent = (parent - 1) / 2;
        }
        //找到插入位置
        array[child] = offered;//插入
        return true;
    }


    public int poll() {
        if (isEmpty()) {
            throw IllegalIndex(0);
        }
        swap(0, size - 1);//交换头元素与尾元素再将尾元素抛出
        size--;
        int e = array[size];

        down(0);//下潜

        return e;
    }

    private void down(int parent) {//优先级低的元素下潜
        int left = 2 * parent + 1;
        int right = left + 1;
        int min = parent;//寻找 父,左,右 三者较大<!--小顶堆,查找最小-->的优先级
        if (left < size && array[left] < array[min]) {
            min = left;
        }
        if (right < size && array[right] < array[min]) {
            min = right;
        }
        if (min != parent) {//如果子节点比父节点优先级大
            swap(parent, min);// 交换
            down(min);//递归,直到max==parent,父节点大于左右子节点时停止
        }
    }

    /**
     替换堆顶元素
     */
    public void replace(int replaced) {
        if (isEmpty()) {
            throw IllegalIndex(0);
        }
        array[0] = replaced;
        down(0);
    }

    private void swap(int i, int j) {//交换
        int e = array[i];
        array[i] = array[j];
        array[j] = e;
    }

    public int peek() {
        if (isEmpty()) {
            throw IllegalIndex(0);
        }
        return array[0];//返回堆顶元素
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }
}
//求第k大的元素:算法-快速选择算法-第k大的元素



