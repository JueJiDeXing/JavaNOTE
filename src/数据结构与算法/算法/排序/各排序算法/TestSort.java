package 数据结构与算法.算法.排序.各排序算法;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class TestSort {
    void shuffle(int[] arr) {

    }

    @Test
    @DisplayName("a冒泡1")
    void test_bubbleSort1() {
        int[] arr = new int[]{5, 3, 4, 1, 6, 2};
        new a冒泡().bubbleSort1(arr, arr.length - 1);
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6]", Arrays.toString(arr));
    }

    @Test
    @DisplayName("a冒泡2")
    void test_bubbleSort2() {
        int[] arr = new int[]{5, 3, 4, 1, 6, 2};
        new a冒泡().bubbleSort2(arr);
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6]", Arrays.toString(arr));
    }

    @Test
    @DisplayName("b选择")
    void test_selectSort() {
        int[] arr = new int[]{5, 3, 4, 1, 6, 2};
        new b选择().selectSort(arr);
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6]", Arrays.toString(arr));
    }

    @Test
    @DisplayName("c堆排")
    void test_heapSort() {
        int[] arr = new int[]{5, 3, 4, 1, 6, 2};
        new c堆排().heapSort(arr);
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6]", Arrays.toString(arr));
    }

    @Test
    @DisplayName("d插入")
    void test_insertSort1() {
        int[] arr = new int[]{5, 3, 4, 1, 6, 2};
        new d插入().insertSort(arr);
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6]", Arrays.toString(arr));
    }

    @Test
    @DisplayName("e希尔")
    void test_shellSort() {
        int[] arr = new int[]{5, 3, 4, 1, 6, 2};
        new e希尔().shellSort(arr);
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6]", Arrays.toString(arr));
    }

    @Test
    @DisplayName("f归并1")
    void test_mergeSort1() {
        int[] arr = new int[]{5, 3, 4, 1, 6, 2};
        new f归并().mergeSort(arr);
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6]", Arrays.toString(arr));
    }

    @Test
    @DisplayName("f归并2")
    void test_mergeSort2() {
        int[] arr = new int[]{5, 3, 4, 1, 6, 2};
        new f归并().mergeSort2(arr);
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6]", Arrays.toString(arr));
    }

    @Test
    @DisplayName("g快速1")
    void test_quickSort1() {
        int[] arr = new int[]{5, 3, 4, 1, 6, 2};
        new g快速().quickSort(arr, 1);
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6]", Arrays.toString(arr));
    }

    @Test
    @DisplayName("g快速2")
    void test_quickSort2() {
        int[] arr = new int[]{5, 3, 4, 1, 6, 2};
        new g快速().quickSort(arr, 2);
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6]", Arrays.toString(arr));
    }

    @Test
    @DisplayName("g快速3")
    void test_quickSort3() {
        int[] arr = new int[]{5, 3, 4, 1, 6, 2};
        new g快速().quickSort(arr, 3);
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6]", Arrays.toString(arr));
    }

    @Test
    @DisplayName("g快速4")
    void test_quickSort4() {
        int[] arr = new int[]{5, 3, 4, 1, 6, 2};
        new g快速().quickSort(arr, 4);
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6]", Arrays.toString(arr));
    }

    @Test
    @DisplayName("h计数1")
    void test_countSort1() {
        int[] arr = new int[]{5, 3, 4, 1, 6, 2};
        new h计数().countSort1(arr);
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6]", Arrays.toString(arr));
    }

    @Test
    @DisplayName("h计数2")
    void test_countSort2() {
        int[] arr = new int[]{5, 3, 4, 1, 6, 2};
        new h计数().countSort2(arr);
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6]", Arrays.toString(arr));
    }

    @Test
    @DisplayName("I桶排")
    void test_bucketSort1() {
        int[] arr = new int[]{5, 3, 4, 1, 6, 2};
        new I桶排().bucketSort1(arr);
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6]", Arrays.toString(arr));
    }

    @Test
    @DisplayName("I桶排")
    void test_bucketSort2() {
        int[] arr = new int[]{5, 3, 4, 1, 6, 2};
        new I桶排().bucketSort2(arr, 2);
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6]", Arrays.toString(arr));
    }

    @Test
    @DisplayName("J基数")
    void test_radixSort1() {
        String[] arr = new String[]{"151", "183", "167", "135"};
        new J基数().radixSort1(arr, 3);
        Assertions.assertEquals("[135, 151, 167, 183]", Arrays.toString(arr));
    }
}
