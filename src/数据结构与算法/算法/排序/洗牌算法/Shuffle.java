package 数据结构与算法.算法.排序.洗牌算法;

import java.util.Random;

public class Shuffle {

    void shuffle(int[] arr, int[] res) {
        Random r = new Random();
        int k;
        for (int i = 0; i < arr.length-1; ++i) {
            k = r.nextInt(i+1,arr.length-1);
            swap(arr, i, k);
        }
    }

    void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
