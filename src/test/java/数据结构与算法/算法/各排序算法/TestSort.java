package 数据结构与算法.算法.各排序算法;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import 数据结构与算法.算法.排序.各排序算法.*;

import java.util.Arrays;

import static cn.hutool.core.util.PrimitiveArrayUtil.shuffle;


class TestSort {
    static int[] arr = new int[]{5, 8, 7, 6, 1, 9, 4, 2, 3};
    static int[] arrRight = arr.clone();

    static {
        Arrays.sort(arrRight);
    }

    int maxTestCount = 30;

    /*  cn.hutool.core.util.PrimitiveArrayUtil.shuffle源码:
      public static int[] shuffle(int[] array, Random random) {
      if (array != null && random != null && array.length > 1) {
          for(int i = array.length; i > 1; --i) {
              swap(array, i - 1, random.nextInt(i));
          }

          return array;
      } else {
          return array;//哈哈哈,搁这凑代码行数是吧?
      }
  }
       */
    @Test
    @DisplayName("a冒泡1")
    void test_bubbleSort1() {
        for (int i = 0; i < maxTestCount; i++) {
            shuffle(arr);
            new a冒泡().bubbleSort1(arr, arr.length - 1);
            Assertions.assertEquals(Arrays.toString(arrRight), Arrays.toString(arr));
        }

    }

    @Test
    @DisplayName("a冒泡2")
    void test_bubbleSort2() {
        for (int i = 0; i < maxTestCount; i++) {
            shuffle(arr);
            new a冒泡().bubbleSort2(arr);
            Assertions.assertEquals(Arrays.toString(arrRight), Arrays.toString(arr));
        }
    }

    @Test
    @DisplayName("b选择")
    void test_selectSort() {
        for (int i = 0; i < maxTestCount; i++) {
            shuffle(arr);
            new b选择().selectSort(arr);
            Assertions.assertEquals(Arrays.toString(arrRight), Arrays.toString(arr));
        }
    }

    @Test
    @DisplayName("c堆排")
    void test_heapSort() {
        for (int i = 0; i < maxTestCount; i++) {
            shuffle(arr);
            new c堆排().heapSort(arr);
            Assertions.assertEquals(Arrays.toString(arrRight), Arrays.toString(arr));
        }
    }

    @Test
    @DisplayName("d插入")
    void test_insertSort1() {
        for (int i = 0; i < maxTestCount; i++) {
            shuffle(arr);
            new d插入().insertSort(arr);
            Assertions.assertEquals(Arrays.toString(arrRight), Arrays.toString(arr));
        }
    }

    @Test
    @DisplayName("e希尔")
    void test_shellSort() {
        for (int i = 0; i < maxTestCount; i++) {
            shuffle(arr);
            new e希尔().shellSort(arr);
            Assertions.assertEquals(Arrays.toString(arrRight), Arrays.toString(arr));
        }
    }

    @Test
    @DisplayName("f归并1")
    void test_mergeSort1() {
        for (int i = 0; i < maxTestCount; i++) {
            shuffle(arr);
            new f归并().mergeSort(arr);
            Assertions.assertEquals(Arrays.toString(arrRight), Arrays.toString(arr));
        }
    }

    @Test
    @DisplayName("f归并2")
    void test_mergeSort2() {
        for (int i = 0; i < maxTestCount; i++) {
            shuffle(arr);
            new f归并().mergeSort2(arr);
            Assertions.assertEquals(Arrays.toString(arrRight), Arrays.toString(arr));
        }
    }

    @Test
    @DisplayName("g快速1")
    void test_quickSort1() {
        for (int i = 0; i < maxTestCount; i++) {
            shuffle(arr);
            new g快速().quickSort(arr, 1);
            Assertions.assertEquals(Arrays.toString(arrRight), Arrays.toString(arr));
        }
    }

    @Test
    @DisplayName("g快速2")
    void test_quickSort2() {
        for (int i = 0; i < maxTestCount; i++) {
            new g快速().quickSort(arr, 2);
            Assertions.assertEquals(Arrays.toString(arrRight), Arrays.toString(arr));
        }
    }

    @Test
    @DisplayName("g快速3")
    void test_quickSort3() {
        for (int i = 0; i < maxTestCount; i++) {
            shuffle(arr);
            new g快速().quickSort(arr, 3);
            Assertions.assertEquals(Arrays.toString(arrRight), Arrays.toString(arr));
        }
    }

    @Test
    @DisplayName("g快速4")
    void test_quickSort4() {
        for (int i = 0; i < maxTestCount; i++) {
            shuffle(arr);
            new g快速().quickSort(arr, 4);
            Assertions.assertEquals(Arrays.toString(arrRight), Arrays.toString(arr));
        }
    }

    @Test
    @DisplayName("h计数1")
    void test_countSort1() {
        for (int i = 0; i < maxTestCount; i++) {
            shuffle(arr);
            new h计数().countSort1(arr);
            Assertions.assertEquals(Arrays.toString(arrRight), Arrays.toString(arr));
        }
    }

    @Test
    @DisplayName("h计数2")
    void test_countSort2() {
        for (int i = 0; i < maxTestCount; i++) {
            shuffle(arr);
            new h计数().countSort2(arr);
            Assertions.assertEquals(Arrays.toString(arrRight), Arrays.toString(arr));
        }
    }

    @Test
    @DisplayName("I桶排")
    void test_bucketSort1() {
        for (int i = 0; i < maxTestCount; i++) {
            shuffle(arr);
            new I桶排().bucketSort1(arr);
            Assertions.assertEquals(Arrays.toString(arrRight), Arrays.toString(arr));
        }
    }

    @Test
    @DisplayName("I桶排")
    void test_bucketSort2() {
        for (int i = 0; i < maxTestCount; i++) {
            shuffle(arr);
            new I桶排().bucketSort2(arr, 2);
            Assertions.assertEquals(Arrays.toString(arrRight), Arrays.toString(arr));
        }
    }

    @Test
    @DisplayName("J基数")
    void test_radixSort1() {
        String[] arr = new String[]{"151", "183", "167", "135"};
        new J基数().radixSort1(arr, 3);
        Assertions.assertEquals("[135, 151, 167, 183]", Arrays.toString(arr));
    }
}
