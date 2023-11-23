package 数据结构与算法.算法.排序.各排序算法;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static 数据结构与算法.算法.排序.各排序算法.a冒泡.bubbleSort1;
import static 数据结构与算法.算法.排序.各排序算法.a冒泡.bubbleSort2;
import static 数据结构与算法.算法.排序.各排序算法.b选择.selectSort;
import static 数据结构与算法.算法.排序.各排序算法.c堆排.heapSort;
import static 数据结构与算法.算法.排序.各排序算法.d插入.insertSort;

class TestSort {
    void shuffle(int[] arr) {

    }

    @Test
    @DisplayName("冒泡1")
    void test_bubbleSort1() {
        int[] arr = new int[]{5, 3, 4, 1, 6, 2};
        bubbleSort1(arr, arr.length - 1);
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6]", Arrays.toString(arr));
    }

    @Test
    @DisplayName("冒泡2")
    void test_bubbleSort2() {
        int[] arr = new int[]{5, 3, 4, 1, 6, 2};
        bubbleSort2(arr);
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6]", Arrays.toString(arr));
    }

    @Test
    @DisplayName("选择")
    void test_selectSort() {
        int[] arr = new int[]{5, 3, 4, 1, 6, 2};
        selectSort(arr);
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6]", Arrays.toString(arr));
    }

    @Test
    @DisplayName("堆排")
    void test_heapSort() {
        int[] arr = new int[]{5, 3, 4, 1, 6, 2};
        heapSort(arr);
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6]", Arrays.toString(arr));
    }

    @Test
    @DisplayName("插入")
    void test_insertSort1() {
        int[] arr = new int[]{5, 3, 4, 1, 6, 2};
        insertSort(arr);
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6]", Arrays.toString(arr));
    }

    @Test
    @DisplayName("插入")
    void test_insertSort2() {
        int[] arr = new int[]{5, 3, 4, 1, 6, 2};
        insertSort(arr);
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6]", Arrays.toString(arr));
    }
}
