package 课程.实验课.实验2;

import java.util.*;

public class _4_17 {
    static HashMap<String, Integer> monthToDay = new HashMap<>();

    static {
        monthToDay.put("Jan", 31);
        monthToDay.put("Feb", 28);
        monthToDay.put("Mar", 31);
        monthToDay.put("Apr", 30);
        monthToDay.put("May", 31);
        monthToDay.put("Jun", 30);
        monthToDay.put("Jul", 31);
        monthToDay.put("Aug", 31);
        monthToDay.put("Set", 30);
        monthToDay.put("Oct", 31);
        monthToDay.put("Nov", 30);
        monthToDay.put("Dec", 31);
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
