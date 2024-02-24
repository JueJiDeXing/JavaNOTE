package 基础.基础;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class 字符串 {
    public static void main(String[] args) {
        String name="abc";

        // 1 构造方法
        String name2= new String();
        //参数:空参/字符String original/字符数组char[] chs/字节数组byte[]chs
        String name3= new String("abc");
        char[]chs={'a','b','c'};
        String name4= new String(chs);
        byte[]bytes={97,98,100};
        String name5= new String(bytes);

        // 2 字符串池和字符串拼接原理
        String a1="abc";//"abc"存储在堆中
        String a2="abc";//a2和a1指向同一个值
        String b1= new String(chs);
        String b2= new String(chs);//new在堆中开辟新的空间,b2和b1指向不同

        //字符串拼接原理
        //无变量参与时
        String s1="a"+"b"+"c";//->String s="abc"
        String s2="abc";//复用字符串池的字符串
        System.out.println(s1==s2);//true,地址值相同

        // <JDK7
        String st1="abc";//"abc"载入字符串池
        String st2=st1+"d";//"d"载入字符串池,
        //堆中生成new StringBuilder,拼接字符,生成new String
        //等价于String s2=new StringBuilder().append("abc").append("d").toString();
        String st3="abcd";
        System.out.println(st3==st2);//false,st2是堆中new出来的引用类型,而st3受字符串池管理

        //字符串拼接原理>JDK8
        //预估字符长度,生成数组

        //StringBuilder源码分析
        /*默认创建容量(capacity)16的字节数组.添加的内容长度(length)小于16则直接存储,大于16则扩容至16*2+2=34,大于34则扩容至实际长度*/
        StringBuilder sb=new StringBuilder();
        sb.append("abc");
        System.out.println(sb.capacity());//16
        System.out.println(sb.length());//3

        //3 字符串的比较
        System.out.println(a1==a2);//true
        System.out.println(a1==b1);//false
        System.out.println(b1==b2);//false
        /*基本数据类型比较数据值,引用数据比较地址值*/
        //boolean equals()比较字符串内容
        System.out.println(a1.equals(b1));
        System.out.println(a1.equalsIgnoreCase(b1));//忽略大小写
        //键盘录入的字符串
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入字符串:");
        String str1= scanner.next();//abc
        String str2="abc";
        System.out.println(str1==str2);//false

        //4 遍历字符串
        //char .charAt(int index)根据索引返回字符
        //int .length()返回字符串长度
        str2="HelloWorld12345";
        System.out.println(str2.charAt(1)+" "+str2.length());//b 3
        for (int i = 0; i < str2.length(); i++) {//快捷键str2.length()fori     forr为倒序遍历
            char c=str2.charAt(i);
            if('A'<=c&&c<='Z'){
                System.out.println(c+"为大写字母");
            } else if ('a' <= c && c <= 'z') {
                System.out.println(c+"为小写字母");
            }else if ('0' <= c && c <= '9'){    //'0'=48,'9'=57,48<=c<=57
                System.out.println(c+"为数字");
            }
        }
        //5 字符串截取
        //String .substring(int beginindex[,int endindex])返回截取小串,左闭右开,默认截取到末尾
        String left=str2.substring(0,1);
        String right=str2.substring(str2.length()-1);
        System.out.println(left+","+right);//H,5

        //占位符
        int index=5;
        String format = String.format("index [%d]", index);

        //////////////
        //6 StringBuilder,解决字符串内存消耗
        //构造方法
        StringBuilder str3=new StringBuilder();
        System.out.println(str3);//打印StringBuilder对象,不是地址值而是属性值
        StringBuilder str4=new StringBuilder("abc");
        System.out.println(str4);//输出abc

        //参数:空参/字符String str/容量int capacity/字符序列CharSequence seq
        //成员函数
        // .length()返回长度
        int len=str4.length();
        System.out.println(len);//3

        // .append(str) 添加数据,返回对象本身
        str4.append("efg");
        System.out.println(str4);//输出abcefg

        // .insert(offset,str)在指定索引处添加元素
        str4.insert(0,'1');
        System.out.println(str4);//输出1abcedg

        // .reserve()倒转
        str4.reverse();
        System.out.println(str4);//输出gdecba1

        // .deleteCharAt(int index)删除指定位置字符
        str4.deleteCharAt(2);
        System.out.println(str4);//输出gfcba1

        // .delete(int beginindex,int endindex)删除指定部分,左闭右开
        str4.delete(1,3);
        System.out.println(str4);//输出gba1

        // int .indexOf(String str[,int fromIndex])(从指定位置)查找字符串,返回索引值,未找到返回-1
        // int .lastIndexOf(String str[,int fromIndex])(从指定位置开始)倒序查找字符串,返回索引值
        StringBuilder str5 = new StringBuilder("nnnbammma");
        int t = str5.lastIndexOf("ba",6);//在0~6区间上有ba,ba索引为3
        System.out.println(t);//3

        //.replace(int start,int end,String str)将指定部分替换为指定字符串
        str5.replace(1,8,"b");
        System.out.println(str5);//nba

        // toString()把StringBuilder转换为String
        String str6=str5.toString();
        System.out.println(str6);//nba

        //链式编程
        String str7 = str5.append("aaa").append("bbb").substring(5);

        /////////////////
        //7 StringJoiner
        StringJoiner sj=new StringJoiner(",","[","]");
        //参数:(间隔符 [,开始符,结束符])
        sj.add("1").add("2");
        System.out.println(sj);//[1,2]
        System.out.println(sj.toString());//[1,2]
        System.out.println(sj.length());//5 字符个数

        //8 字符串的转换
        char[]arr=str7.toCharArray();
        String str8= Arrays.toString(arr);//数组与字符串互转

        String num="12345";
        int number=0;
        for (int i = 0; i < num.length(); i++) {
            char c=num.charAt(i);
            int n= c-'0';//字符转数字等常通过ASCII码表转换
            number=number*10+n;
        }
        System.out.println(number);
        System.out.println(num.startsWith("1",0));//从第0位判断一个字符串的字符,toffset不写默认从开头判断
    }
}
