package 数据结构与算法.蓝桥杯真题.第7届国赛.Java大学A组;

import java.math.BigInteger;
/**
 已AC
 */
public class A阶乘位数 {
    /*
    9999! 二进制有多少位
     */
    public static void main(String[] args) {
        BigInteger ans=BigInteger.ONE;
        for (int i=2;i<10000;i++){
            ans=ans.multiply(BigInteger.valueOf(i));
        }
        System.out.println(ans.bitLength());//118445
    }
}
