package 课程.Java程序设计;

import java.util.*;

public class 作业4 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter list1 size and contents: ");
        int len1 = sc.nextInt();
        int[] list1 = new int[len1];
        for (int i = 0; i < len1; i++) {
            list1[i] = sc.nextInt();
        }
        System.out.print("Enter list2 size and contents: ");
        int len2 = sc.nextInt();
        int[] list2 = new int[len2];
        for (int i = 0; i < len2; i++) {
            list2[i] = sc.nextInt();
        }
        System.out.print("list1 is ");
        for (int a:list1){
            System.out.print(a+" ");
        }
        System.out.println();
        System.out.print("list2 is ");
        for (int a:list2){
            System.out.print(a+" ");
        }
        System.out.println();
        int[] ans =  merge(list1, list2);
        System.out.print ("The merged list is ");
        for (int a : ans) {
            System.out.print(a + " ");
        }
    }

    public static int[] merge(int[] list1, int[] list2) {
        int len1 = list1.length, len2 = list2.length;
        int[] ans = new int[len1 + len2];
        int i = 0, j = 0, k = 0;
        while (i < len1 && j < len2) {
            if (list1[i] < list2[j]) {
                ans[k++] = list1[i++];
            } else {
                ans[k++] = list2[j++];
            }
        }
        while (i < len1) {
            ans[k++] = list1[i++];
        }
        while (j < len2) {
            ans[k++] = list2[j++];
        }
        return ans;
    }
}
