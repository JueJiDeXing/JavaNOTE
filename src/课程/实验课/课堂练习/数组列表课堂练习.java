package 课程.实验课.课堂练习;

import java.util.ArrayList;
import java.util.Scanner;

public class 数组列表课堂练习 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> list1 = new ArrayList<>();
        System.out.print("Enter five integers for list1: ");
        for (int i = 0; i < 5; i++) {
            list1.add(sc.nextInt());
        }
        ArrayList<Integer> list2 = new ArrayList<>();
        System.out.print("Enter five integers for list2: ");
        for (int i = 0; i < 5; i++) {
            list2.add(sc.nextInt());
        }
        ArrayList<Integer> ans = union(list1, list2);
        System.out.print("The combined list is ");
        for (Integer a : ans) {
            System.out.print(a + " ");
        }
        System.out.println();
        ans = set(ans);
        ans.sort((a, b) -> a - b);
        System.out.print("The sorted list is ");
        for (Integer a : ans) {
            System.out.print(a + " ");
        }
    }

    /**
     并集
     */
    public static ArrayList<Integer> union(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        ArrayList<Integer> ans = new ArrayList<>();
        ans.addAll(list1);
        ans.addAll(list2);
        return ans;
    }

    /**
     去重
     */
    public static ArrayList<Integer> set(ArrayList<Integer> list) {
        ArrayList<Integer> ans = new ArrayList<>();
        for (Integer a : list) {
            if (!ans.contains(a)) ans.add(a);
        }
        return ans;
    }
}
