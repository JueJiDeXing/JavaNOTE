package 数据结构与算法.算法.leetcode;

import java.util.ArrayList;

public class 最大间距 {
    //数组排序后,相邻元素的最大差值

    //限制:O(n)

    public int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        // 排序
        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) max = nums[i];
            if (nums[i] < min) min = nums[i];
        }

        // 准备桶
        /* 计算桶个数               期望桶个数
        (max - min) / range + 1 =  a.length   -> range = (max - min) / (a.length -1)
        (max - min) / range + 1 =  a.length + 1   -> range = (max - min) / a.length
         */
        int range = Math.max((max - min) / nums.length, 1);//每个桶的元素个数
        int bucketSize = (max - min) / range + 1;//桶数比元素多一个,有空桶,将问题转化为桶的间距(有空桶的情况下,桶内间距不可能大于桶间距)
        Pair[] buckets = new Pair[bucketSize];

        //放入数据
        for (int num : nums) {
            int index = (num - min) / range;
            if (buckets[index] == null) {
                buckets[index] = new Pair();//每个桶只记录最大最小值
            }
            buckets[index].add(num);
        }

        // 相邻元素的最大差值
        int r = 0;
        int lastMax = buckets[0].max;
        for (int i = 1; i < nums.length; i++) {
            Pair bucket = buckets[i];
            if (bucket != null) {
                r = Math.max(r, bucket.max - lastMax);
                lastMax = bucket.max;
            }
        }
        return r;
    }

    static class Pair {
        int min = 1000_000_000;
        int max = 0;

        void add(int val) {
            max = Math.max(max, val);
            min = Math.min(min, val);
        }

        @Override
        public String toString() {
            return "(" + min + "," + max + ")";
        }
    }

    /**
     基数排序,桶排序会造成内存占用过多
     */
    public void radixSort(int[] a) {
        ArrayList<Integer>[] buckets = new ArrayList[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) max = a[i];
        }
        int m = 1;//除数
        while (m <= max) {
            for (int i : a) {
                buckets[i / m % 10].add(i);//i/m%10从个位往前,按位桶排
            }
            int k = 0;
            for (ArrayList<Integer> bucket : buckets) {
                for (Integer i : bucket) {
                    a[k++] = i;
                }
                bucket.clear();
            }
            m *= 10;
        }
    }

}
