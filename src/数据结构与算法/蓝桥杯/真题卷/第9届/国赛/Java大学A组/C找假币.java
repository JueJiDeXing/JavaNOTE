package 数据结构与算法.蓝桥杯.真题卷.第9届.国赛.Java大学A组;

import java.util.*;
/**
 已AC
 */
public class C找假币 {

    static int balance(int a, int b) {
        if (a < b) return -1;
        if (a > b) return 1;
        return 0;
    }

    static void judge(char[] data, int a, int b, int std) {
        switch (balance(data[a], data[std])) {
            case -1 -> System.out.println(a + " light");
            case 0 -> System.out.println(b + " heavy");
            case 1 -> System.out.println("err!");
        }
    }

    // data 中8个元素，有一个假币，或轻或重
    static void f(char[] data) {
        switch (balance(data[0] + data[1] + data[2], data[3] + data[4] + data[5])) {  //填空
            case -1 -> {
                switch (balance(data[0] + data[4], data[3] + data[1])) {
                    case -1 -> judge(data, 0, 3, 1);
                    case 0 -> judge(data, 2, 5, 0);
                    case 1 -> judge(data, 1, 4, 0);
                }
            }
            case 0 -> judge(data, 6, 7, 0);
            case 1 -> {
                switch (balance(data[0] + data[4], data[3] + data[1])) {
                    case -1 -> judge(data, 4, 1, 0);
                    case 0 -> judge(data, 5, 2, 0);
                    case 1 -> judge(data, 3, 0, 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();//55555558
        f(("" + n).toCharArray());//最后一个重 ->  7 heavy
    }
}
