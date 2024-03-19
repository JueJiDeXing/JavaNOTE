package 数据结构与算法.蓝桥杯真题.第11届国赛.Java大学A组;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class F奇偶覆盖 {
    /*
    给出n个矩形的对角坐标
    求叠奇数层的矩形面积覆盖 和 叠偶数层的矩形面积覆盖
     */static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    public static void main(String[] args) {


    }

}
