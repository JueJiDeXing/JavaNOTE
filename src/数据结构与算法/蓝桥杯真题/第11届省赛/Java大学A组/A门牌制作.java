package 数据结构与算法.蓝桥杯真题.第11届省赛.Java大学A组;
/**
 已AC
 */
public class A门牌制作 {
    /*
    [1,2020]有多少个2
     */
    public static void main(String[] args) {
        int ans = 0;
        for (int i = 1; i <= 2020; i++) {
            ans += count(i);
        }
        System.out.println(ans);//624
    }

    static int count(int num) {
        int ans = 0;
        while (num > 0) {
            if (num % 10 == 2) ans++;
            num /= 10;
        }
        return ans;
    }


}
