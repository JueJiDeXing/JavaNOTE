package 课程.实验课.实验2;

import java.util.HashMap;
import java.util.Scanner;

public class _4_17 {
    static HashMap<String, Integer> monthToDay = new HashMap<>();

    static {
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Set", "Oct", "Nov", "Dec"};
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        for (int i = 0; i < months.length; i++) {
            monthToDay.put(months[i], days[i]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a year:");
        int year = sc.nextInt();
        System.out.print("Enter a month:");
        String month = sc.next();
        int day = monthToDay.get(month);
        if (month.equals("Feb") && isLeap(year)) day++;
        System.out.println(month + " " + year + " has " + day + " days");
    }

    static boolean isLeap(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }
}
