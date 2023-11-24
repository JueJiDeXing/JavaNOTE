package 数据结构与算法.算法.二分查找;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTest {
    二分查找_优化 test = new 二分查找_优化();
    int[] arr = new int[]{1, 3, 4, 5, 8, 9, 12, 16};
    int[] arr_repeat = new int[]{1, 3, 4, 4, 4, 5, 5, 8};

    @Test
    void binarySearch1_原() {
        assertEquals(0, test.binarySearch_原(arr, 1));
        assertEquals(-1, test.binarySearch_原(arr, 2));
        assertEquals(4, test.binarySearch_原(arr, 8));
        assertEquals(-1, test.binarySearch_原(arr, 10));
        assertEquals(7, test.binarySearch_原(arr, 16));
        assertEquals(-1, test.binarySearch_原(arr, 17));

    }

    @Test
    void binarySearch() {
        //插入点为i表示 前面应当有i个元素
        assertEquals(0, test.binarySearch(arr, 1));
        assertEquals(~1, test.binarySearch(arr, 2));//返回 -插入点-1 <=> ~插入点
        assertEquals(4, test.binarySearch(arr, 8));
        assertEquals(~6, test.binarySearch(arr, 10));
        assertEquals(7, test.binarySearch(arr, 16));
        assertEquals(~8, test.binarySearch(arr, 17));
    }

    @Test
    void binarySearch_balance() {
        assertEquals(0, test.binarySearch_balance(arr, 1));
        assertEquals(~1, test.binarySearch_balance(arr, 2));
        assertEquals(4, test.binarySearch_balance(arr, 8));
        assertEquals(~6, test.binarySearch_balance(arr, 10));
        assertEquals(7, test.binarySearch_balance(arr, 16));
        assertEquals(~8, test.binarySearch_balance(arr, 17));
    }

    @Test
    void binarySearch_repeat() {
        assertEquals(0, test.binarySearch_repeat(arr, 1));
        assertEquals(-1, test.binarySearch_repeat(arr, 2));
        assertEquals(4, test.binarySearch_repeat(arr, 8));
        assertEquals(-1, test.binarySearch_repeat(arr, 10));
        assertEquals(7, test.binarySearch_repeat(arr, 16));
        assertEquals(-1, test.binarySearch_repeat(arr, 17));

        assertEquals(2, test.binarySearch_repeat(arr_repeat, 4));
        assertEquals(5, test.binarySearch_repeat(arr_repeat, 5));
        assertEquals(-1, test.binarySearch_repeat(arr_repeat, 9));
    }

    @Test
    void binarySearch_leftMost() {
        assertEquals(0, test.binarySearch_leftMost(arr, 1));
        assertEquals(1, test.binarySearch_leftMost(arr, 2));
        assertEquals(4, test.binarySearch_leftMost(arr, 8));
        assertEquals(6, test.binarySearch_leftMost(arr, 10));
        assertEquals(7, test.binarySearch_leftMost(arr, 16));
        assertEquals(8, test.binarySearch_leftMost(arr, 17));

        assertEquals(2, test.binarySearch_leftMost(arr_repeat, 4));
        assertEquals(5, test.binarySearch_leftMost(arr_repeat, 5));
        assertEquals(8, test.binarySearch_leftMost(arr_repeat, 9));
    }

    @Test
    void binarySearch_rec() {
        int left = 0;
        int right = arr.length - 1;
        assertEquals(left, test.binarySearch_rec(arr, 1, left, right));
        assertEquals(-1, test.binarySearch_rec(arr, 2, left, right));
        assertEquals(4, test.binarySearch_rec(arr, 8, left, right));
        assertEquals(-1, test.binarySearch_rec(arr, 10, left, right));
        assertEquals(7, test.binarySearch_rec(arr, 16, left, right));
        assertEquals(-1, test.binarySearch_rec(arr, 17, left, right));

    }
}
