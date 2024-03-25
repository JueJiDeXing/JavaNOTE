package 课程.实验课.实验2;

import java.util.Scanner;

public class _3_9 {
    /*
    输入9位数字,计算第10位的校验和
     */
    public static void main(String[] args) {
        String enterMessage = "Enter the first 9 digits of an ISBN as integer: ";
        System.out.print(enterMessage);
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int m = 0;
        for (int i = 0; i < s.length(); i++) {
            int d = s.charAt(i) - '0';
            m += d * (i + 1);
        }
        m %= 11;
        System.out.print("The ISBN-10 number is ");
        if (m == 10) {
            System.out.println(s + "X");
        } else {
            System.out.println(s + m);
        }
    }
}
