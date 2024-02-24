package 基础.J_API;

import java.math.BigInteger;
import java.util.Random;

public class J_BigInteger {
    //大数
    public static void main(String[] args) {
        //BigInteger,可存储非常大的整数
        //构造函数
        // BigInteger(int num,Random rnd)获取随机大整数,[0,2^num-1]
        Random random=new Random();
        BigInteger bg1=new BigInteger(6,random);
        System.out.println(bg1);//55

        //BigInteger(String value)获取指定大整数
        BigInteger bg2=new BigInteger("99999");
        System.out.println(bg2);//99999

        //BigInteger(String value,int radix)获取指定进制的指定大整数
        BigInteger bg3=new BigInteger("6A48D12C",16);
        System.out.println(bg3);//1783157036

        //静态方法
        //BigInteger.valueOf(long value)获取BigInteger对象
        BigInteger bg4=BigInteger.valueOf(100);//只能存储long的范围的整数
        System.out.println(bg4);//100
        //对于-16~16的对象在内部已经创建好了,多次获取时为同一个对象

        //对象一旦创建不能修改
        BigInteger bg5 = bg1.add(bg2);//计算会生成一个新对象,原对象无法改变
        System.out.println(bg5);//100051

        //常见成员方法
        //BigInteger .add(BigInteger val)加    [subtract减,multiply乘,divide除]
        //BigInteger[] .divideAndRemainder(BigInteger val)  获取商和余数
        //boolean .equals(Object x)比较值是否相等
        //BigInteger .pow(int exponent)次幂
        //BigInteger max/min(BigInteger val)返回较大值/较小值
        //int intValue(BigInteger val)转出为整数(其他类型也有相应方法),超出范围数据有误

        //底层原理
        /*
        数据拆分,首位signum+32位*n添加到一个数组mag中存储
        首位:-1表示负数,0表示0,1表示正数
        例:bg=27670116110564327424 //110000..000 -> 11后面65-2=63个0
        signum=1,mag=[1,-2147483648,0]
        //存储上限:数组的最大长度为int的最大值(理论值,实际上电脑可能创建不了这么长的数组)

         */

    }
}
