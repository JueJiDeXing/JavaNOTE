package 数据结构与算法.蓝桥杯真题.第12届省赛.Java大学A组;
/**
 已AC
 */
public class A相乘 {
    /*
    2021x % 1000000007 = 999999999
    求x,如果x不存在,输出0
     */

    public static void main(String[] args) {
        for (int x = 1; x < 1000000007; x++) {
            if ((long) 2021 * x % 1000000007 == 999999999) {
                System.out.println(x);
                break;
            }
        }
    }
}
