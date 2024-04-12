package 课程.实验课.实验3;

import java.util.Scanner;

public class _7_19 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter list: ");
        int n = sc.nextInt();
        int[] list = new int[n];
        for (int i = 0; i < n; i++) list[i] = sc.nextInt();

        boolean sorted = isSorted(list);
        if (sorted) {
            System.out.println("The list is already sorted");
        } else {
            System.out.println("The list is not sorted");
        }
    }

    public static boolean isSorted(int[] list) {
        for (int i = 1; i < list.length - 1; i++) {//第一个数表示列表长度
            if (list[i] > list[i + 1]) return false;
        }
        return true;
    }

}
