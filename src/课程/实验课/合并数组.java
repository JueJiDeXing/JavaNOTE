package 课程.实验课;

import java.util.Arrays;

public class 合并数组 {
    public static void main(String[] args) {
        int[] list1 = {1, 3, 6, 7, 10};
        int[] list2 = {3, 4, 6, 6, 9};
        int[] merge = merge(list1, list2);
        System.out.println(Arrays.toString(merge));

    }

    public static int[] merge(int[] list1, int[] list2) {
        int len1 = list1.length, len2 = list2.length;
        int len = len1 + len2;
        int[] ans = new int[len];
        int i = 0, j = 0, k = 0;
        while (i < len1 && j < len2) {
            if (list1[i] < list2[j]) {
                ans[k++] = list1[i++];
            } else {
                ans[k++] = list2[j++];
            }
        }
        if (i < len1) {
            System.arraycopy(list1, i, ans, k, len1 - i);
        } else {
            System.arraycopy(list2, j, ans, k, len2 - j);
        }
        return ans;
    }
}
