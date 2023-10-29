package 数据结构与算法.算法.leetcode;

public class _1281整数的各位积和之差 {
    //    给你一个整数 n，请你帮忙计算并返回该整数「各位数字之积」与「各位数字之和」的差。
    public static void main(String[] args) {
        int pro = 1;
        int sum = 0;
        int n = 234;
        while (n > 0) {
            int t = n % 10;
            sum += t;
            pro *= t;
            n = n / 10;
        }
        System.out.println(sum + " " + pro);
    }
}
