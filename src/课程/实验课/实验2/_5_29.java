package 课程.实验课.实验2;

import java.util.*;

public class _5_29 {
    static boolean isLeap(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    static int[] month = new int[]{-1, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static String[] monthName = new String[]{"", "January", "February", "March", "April", "May",
            "June", "July", "August", "September", "October", "November", "December"};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int year = sc.nextInt(), firstDay = sc.nextInt();// 年的第一天是星期几
        if (isLeap(year)) month[2] = 29;

        for (int i = 1; i <= 12; i++) {
            System.out.println("\t\t\t\t\t" + monthName[i] + " " + year);
            System.out.println("---------------------------------------------------");
            System.out.println(" Sun     Mon     Tue     Wed     Thu     Fri     Sat");
            print(firstDay, month[i]);
            firstDay = (month[i] + firstDay) % 7;
        }
    }

    static void print(int firstDay, int days) {
        System.out.print(" ");
        System.out.print("  \t\t ".repeat(firstDay));
        int cnt = firstDay;
        for (int i = 1; i <= days; i++) {
            if (i < 10) System.out.print(" ");
            System.out.print(i + "\t\t ");
            cnt++;
            if (cnt == 7) {
                cnt = 0;
                System.out.print("\n ");
            }
        }
        System.out.println();
    }
}
