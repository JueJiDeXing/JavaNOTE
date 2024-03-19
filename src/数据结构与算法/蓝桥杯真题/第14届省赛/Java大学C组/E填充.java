package 数据结构与算法.蓝桥杯真题.第14届省赛.Java大学C组;

import java.util.*;

/**
 已AC
 */
public class E填充 {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        char[] str = scan.next().toCharArray();
        int n = str.length;
        for (int i = 1; i < n - 1; i++) {
            if (str[i] == '?') {
                if (str[i - 1] == str[i + 1] && str[i - 1] != '?') {
                    str[i] = str[i - 1];
                }
            }
        }
        int ans = 0;
        int k = 0;
        while (k < n - 1) {
            if (str[k] == '?' || str[k + 1] == '?'
                    || str[k] == str[k + 1]) {
                ans++;
                k += 2;
            } else {
                k++;
            }
        }
        System.out.println(ans);
        scan.close();
    }
}
