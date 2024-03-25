package 数据结构与算法.蓝桥杯真题.第9届省赛.Java大学A组;
/**
 已AC
 */
public class A分数 {
    /*
    求 1/1 + 1/2 + 1/4+...
    20项
     */
    public static void main(String[] args) {
        // 1/1 3/2 7/4
        // 分子*2+1 分母*2
        int up = 1, down = 1;
        for (int i = 1; i < 20; i++) {
            up = 2 * up + 1;
            down *= 2;
        }
        System.out.println(up + "/" + down);//1048575/524288

    }
}
