package 数据结构与算法.蓝桥杯.真题卷.第11届.省赛.Java大学A组;

import java.util.*;
/**
 已AC
 */
public class D七段码 {

    public static void main(String[] args) {
        int ans = 0;
        dfs(0, new int[7]);//生成灯亮组合
        for (int[] light : list) {
            if (check(light)) {
                ans++;
            }
        }
        System.out.println(ans);//80
    }

    static List<int[]> list = new ArrayList<>();

    static void dfs(int i, int[] light) {
        if (i == 7) {
            list.add(light.clone());
            return;
        }
        light[i] = 0;
        dfs(i + 1, light);
        light[i] = 1;
        dfs(i + 1, light);
    }

    static boolean check(int[] light) {
        int sum = 0;
        for (int i : light) sum += i;
        if (sum == 0) return false;//没有灯亮
        if (sum == 1) return true;//一个亮,不用考虑连通性
        int a = light[0], b = light[1], c = light[2], d = light[3],
                e = light[4], f = light[5], g = light[6];
        //每个灯都需要有与它连通的
        if (a == 1 && b == 0 && f == 0) return false;
        if (b == 1 && a == 0 && g == 0 && c == 0) return false;
        if (c == 1 && b == 0 && g == 0 && d == 0) return false;
        if (d == 1 && c == 0 && e == 0) return false;
        if (e == 1 && d == 0 && g == 0 && f == 0) return false;
        if (f == 1 && a == 0 && e == 0 && g == 0) return false;
        if (g == 1 && b == 0 && c == 0 && e == 0 && f == 0) return false;
        if (sum == 2) return true;
        // 因为每个灯都有与它连通的, 所以现在不连通的情况只有2,2 或 2,3 或 3,3
        // 2,3不连通和3,3不连通情况不存在
        // 现在只需要考虑2,2不连通
        if (a == 1 && b == 1 && c == 0 && f == 0 && g == 0) {
            return false;//ab与ed
        }
        if (b == 1 && c == 1 && a == 0 && g == 0 && d == 0) {
            return false;//bc与ef
        }
        if (c == 1 && d == 1 && b == 0 && e == 0 && g == 0) {
            return false;//cd与af
        }
        return true;
    }


    private static void solve1() {
        int n = 0;
        for (int a = 0; a < 2; a++) {
            for (int b = 0; b < 2; b++) {
                for (int c = 0; c < 2; c++) {
                    for (int d = 0; d < 2; d++) {
                        for (int e = 0; e < 2; e++) {
                            for (int f = 0; f < 2; f++) {
                                for (int g = 0; g < 2; g++) {
                                    if (a + b + c + d + e + f + g == 1) {
                                        n++;
                                        continue;
                                    }
                                    if (a + b + c + d + e + f + g == 0) {
                                        continue;
                                    }
                                    if (a == 1 && b == 0 && f == 0) {
                                        continue;
                                    }
                                    if (b == 1 && a == 0 && g == 0 && c == 0) {
                                        continue;
                                    }
                                    if (c == 1 && b == 0 && g == 0 && d == 0) {
                                        continue;
                                    }
                                    if (d == 1 && c == 0 && e == 0) {
                                        continue;
                                    }
                                    if (e == 1 && d == 0 && g == 0 && f == 0) {
                                        continue;
                                    }
                                    if (f == 1 && a == 0 && g == 0 && e == 0) {
                                        continue;
                                    }
                                    if (g == 1 && b == 0 && c == 0 && e == 0 && f == 0) {
                                        continue;
                                    }
                                    if (a + b + c + d + e + f + g != 2) {
                                        if (a == 1 && b == 1 && c == 0 && g == 0 && f == 0) {
                                            continue;
                                        }
                                        if (b == 1 && c == 1 && a == 0 && g == 0 && d == 0) {
                                            continue;
                                        }
                                        if (c == 1 && d == 1 && b == 0 && g == 0 && e == 0) {
                                            continue;
                                        }
                                    }
                                    n++;
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(n);
    }
}
