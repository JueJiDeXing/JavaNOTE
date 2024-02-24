package 基础.基础;

public class 包装类 {
    //包装类
    //包装类不可变
    public static void main(String[] args) {
        //Integer 缓存区-128~127用Integer.valueOf(int)获取的为同一个对象
        //new Integer(int)JDK5前使用的方法,已被淘汰

        //计算:拆箱为基本数据类型,相加后再封装为包装类
        //自动装箱:基本数据类型自动变为相应包装类
        // 自动拆箱:...     (底层还是调用valueOf方法)

        Integer i1=Integer.valueOf(10);
        //Integer i2=new Integer(11);已弃用并被移除
        Integer i2=12;
        Integer i3=i1+i2;

        //成员方法
        //String Integer.toXXXString(int)得到对应进制
        //int Integer.parseInt(String[,radix]) 纯数字型字符串(按对应进制)转整数

        String s="true";
        boolean b=Boolean.parseBoolean(s);
    }

}
