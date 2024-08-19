package 课程.实验课.实验5._11_2;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 日期
 */
@AllArgsConstructor
@Getter
public class MyDate {
    int year, month, day;//month:从0开始

    public MyDate() {
        year = 1970;
        month = 0;
        day = 1;
    }

    public void setDate(long elapsedTime) {
        long days = elapsedTime / 86400;
        year = 1970;
        while (days >= 365) {
            if (isLeapYear(year)) {
                if (days < 366) break;
                days -= 366;
            } else {
                days -= 365;
            }
            year++;
        }
        month = 0;
        while (days >= getMonthDays(year, month)) {
            days -= getMonthDays(year, month);
            month++;
        }
        day = (int) (days + 1);
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    static int[] monthDay = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static int getMonthDays(int year, int month) { // month:从0开始
        if (month == 1) {
            return isLeapYear(year) ? 29 : 28;
        }
        return monthDay[month];
    }

    public long getElapsedTime() {
        return (year - 1970) * 365 * 86400L + (isLeapYear(year) ? 366 : 365) * (month + 1) * 86400L + (day - 1) * 86400L;
    }

    @Override
    public String toString() {
        return year + "-" + (month + 1) + "-" + day;
    }
}
