package 数据结构与算法.蓝桥杯真题.第9届省赛.Java大学A组;

import java.time.DayOfWeek;
import java.time.LocalDate;
/**
 已AC
 */
public class B星期一 {
    /*
    1901/1/1到2000/12/31 一共有多少个星期一
     */
    public static void main(String[] args) {
        LocalDate start = LocalDate.of(1901, 1, 1);
        LocalDate end = LocalDate.of(2000, 12, 31);
        int count = 0;
        while (!start.isAfter(end)) {
            if (start.getDayOfWeek() == DayOfWeek.MONDAY) count++;
            start = start.plusDays(1);
        }
        System.out.println(count);//5217
    }
}
