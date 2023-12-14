package MyNote.J_API;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class J_BigDecimal {
    //精确小数
    public static void main(String[] args) {
        //不可变的,任意精度的,,有符号的十进制数
        //构造方法,参数(int...String val),其中String是精确的,其他类型比如double仍有可能不精确
        BigDecimal bd=new BigDecimal("0.01");
        //与BigInteger类似,但是多参数scale,保留小数位数,以及模式(四合五入等)
        BigDecimal bd2=bd.divide(BigDecimal.valueOf(2),5, RoundingMode.HALF_UP);
        System.out.println(bd2);//0.00500
        //静态方法 .valueOf(long val)   已经创建[0,10]的整数,多次获取为同一个对象
        //底层存储方式,每一位包括小数点,存入数组
    }
}
