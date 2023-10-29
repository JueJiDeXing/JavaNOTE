package 数据结构与算法.算法.排序.各排序算法;

import java.util.ArrayList;

public class J基数 {
    //场景:排序电话号码(字符串),数字太大用其他算法效率低

    //先按个位排序,再按十位排序...

    public void radixSort1(String[] a, int length) {//length:每个字符串的长度
        //准备桶
        ArrayList<String>[] buckets = new ArrayList[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        //按位 桶排序
        for (int i = length - 1; i >= 0; i--) {

            for (String s : a) {
                buckets[s.charAt(i) - '0'].add(s);
            }

            int k = 0;
            for (ArrayList<String> bucket : buckets) {
                for (String s : bucket) {
                    a[k++] = s;
                }
                bucket.clear();
            }
        }
    }
    //扩大排序范围:原来10个桶存储0~9,现在128个桶存储127个字符
    public void radixSort2(String[] a, int length) {//length:每个字符串的长度
        //准备桶
        ArrayList<String>[] buckets = new ArrayList[128];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        //按位 桶排序
        for (int i = length - 1; i >= 0; i--) {

            for (String s : a) {
                buckets[s.charAt(i)].add(s);
            }

            int k = 0;
            for (ArrayList<String> bucket : buckets) {
                for (String s : bucket) {
                    a[k++] = s;
                }
                bucket.clear();
            }
        }
    }
}
