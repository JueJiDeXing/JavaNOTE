package 课程.实验课.实验2;

import java.util.Scanner;

public class 分割浮点数 {
    /**
     接收一个浮点数，将其分割成整数部分和小数部分，并分别输出
     要求对非法输入做出提示
     */
    public static void main(String[] args) {
        System.out.println("请输入一个浮点数:");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if (isError(input)) {//判定浮点数是否是非法输入
            System.out.println("输入浮点数格式有误");
            return;
        }
        String[] split = input.split("\\.");
        System.out.println(input + "的整数部分为:" + split[0]);
        System.out.println(input + "的小数部分为:" + (input.charAt(0) == '-' ? "-" : "") + "0." + split[1]);
    }

    private static boolean isError(String input) {
        boolean hasDot = false;
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '.') {
                if (hasDot) return true;//不能有多个小数点
                hasDot = true;
            } else if (ch == '-') {
                if (i != 0)   return true;//负号只能出现在第一位
            } else if (ch < '0' || ch > '9') {
                  return true;//非法字符
            }
        }
        return !hasDot;//没有小数点
    }
}
