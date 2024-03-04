package 课程.实验课;

import java.math.BigDecimal;
import java.util.*;

public class 分割浮点数 {
    /**
     接收一个浮点数，将其分割成整数部分和小数部分，并分别输出
     要求对非法输入做出提示
     */
    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("123.456");
        System.out.println("请输入一个浮点数:");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        boolean hasDot = false;
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '.') {
                if (hasDot) {
                    System.out.println("输入浮点数格式有误");
                    return;
                }
                hasDot = true;
            } else if (ch == '-') {
                if (i != 0) {
                    System.out.println("输入浮点数格式有误");
                    return;
                }
            } else if (ch < '0' || ch > '9') {
                System.out.println("输入浮点数格式有误");
                return;
            }
        }
        if (!hasDot) {
            System.out.println("输入浮点数格式有误");
            return;
        }
        String[] split = input.split("\\.");
        System.out.println(input + "的整数部分为:" + split[0]);
        System.out.println(input + "的小数部分为:" + (input.charAt(0) == '-' ? "-" : "") + "0." + split[1]);
    }
}
