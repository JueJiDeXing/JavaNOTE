package 数据结构与算法.蓝桥杯真题.第7届省赛.Java大学A组;
/**
 已AC
 */
public class A煤球数目 {
    public static void main(String[] args) {
        long ans = 0;
        long cur = 1, add = 2;
        for (int i = 1; i <= 100; i++) {
            ans += cur;
            cur += add;
            add++;
        }
        System.out.println(ans);//171700
    }
}
