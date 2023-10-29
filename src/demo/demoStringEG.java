package demo;

import java.util.Scanner;

public class demoStringEG {
    public static void main(String[] args) {
        //获取输入金额
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入金额:");
        int num = scanner.nextInt();
        System.out.println(num);
        StringBuilder str =changeMoney(num);

    }

    //字符串例1,金额转换
    private static StringBuilder changeMoney(int num) {
        /*示例输入输出
        * 1209->零拾零万壹仟贰佰零拾玖元
        * 789->零拾零万零仟柒佰捌拾玖元
        * */
        //单位数组,钱数组,目标输出
        char[] arr_unit = {'佰', '拾', '万', '仟', '佰', '拾', '元'};
        char[] chinese_num = {'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'};
        StringBuilder res = new StringBuilder();
        //判断金额是否合法
        if (0 <= num && num <= 9999999) {
            //金额转换
            int j = 6;
            while (j > 0) {//倒序循环
                int i = num % 10;//获取金额的最后一位
                num = num / 10;
                res.insert(0, String.valueOf(chinese_num[i]) + arr_unit[j]);//金额加单位拼接到目标输出的前面
                j--;
            }
            return res;
        } else {
            return new StringBuilder("金额无效");
        }

    }
    //字符串例2,手机号加密
    private static void phoneNumPassword(){

    }
}

