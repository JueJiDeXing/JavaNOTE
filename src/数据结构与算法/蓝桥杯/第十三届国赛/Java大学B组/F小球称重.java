package 数据结构与算法.蓝桥杯.第十三届国赛.Java大学B组;

import java.util.*;

public class F小球称重 {
    /*
    N个球(从1开始编号),有一个次品较轻,现在称了M次
    第k次称重,左边放a1,a2,...号球,右边放b1,b2,...号球,称重返回一个结果"<","=",">"
     求还有几个球有次品嫌疑
     */
    public static void main(String[] args) {
        main_enter();
    }

    private static void main_enter() {
        Scanner sc = new Scanner(System.in);
        sc.nextInt();
        int M = sc.nextInt();
        sc.nextLine();
        HashSet<String> set = new HashSet<>(), set2 = new HashSet<>();
        boolean first = true;
        List<String[]> equal = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            sc.nextLine();
            String[] A = sc.nextLine().split(" "), B = sc.nextLine().split(" ");
            char compare = sc.nextLine().charAt(0);
            if (compare == '=') {
                equal.add(A);
                equal.add(B);
            } else if (compare == '<') {
                if (first) {
                    first = false;
                    Collections.addAll(set, A);
                } else {//与A取交集
                    set2.clear();
                    Collections.addAll(set2, A);
                    set.retainAll(set2);
                }
            } else {
                if (first) {
                    first = false;
                    Collections.addAll(set, B);
                } else {
                    set2.clear();
                    Collections.addAll(set2, B);
                    set.retainAll(set2);
                }
            }
        }
        for (String[] s : equal) {
            Arrays.asList(s).forEach(set::remove);
        }
        System.out.println(set.size());
    }
}
