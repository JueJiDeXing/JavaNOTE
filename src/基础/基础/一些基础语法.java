package 基础.基础;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;//导包写在类定义上面


//类是Java的基本单元,建议一个文件创建一个类(文件名和类名要相同)
/*
* public class 类名{成员变量(属性},成员方法(行为)}
* 创建类对象:    类名 对象名=new 类名();
* */
public class 一些基础语法 {//类名要与文件名相同,否则不能用public修饰类
    public static void main(String[] args) {
        //main程序主入口
        // String[] args 程序实参,可在"编辑配置"里填写,"运行"的左边
        //写main的为不测试类,写main的类为Javabean类
        int m = 0;
        //Binary
        int two = 0b1011;
        int eight = 034574;
        int sixteen = 0x67A5;
    }

    public static void Data() {
        //整型
        byte a = 100;
        short b = 400;
        int c = 20;
        long d = 999999999L;//后面加L/l
        //小数
        float e = 10.1F;//后面加F/f
        double f = 20.2;
        //字符
        char g = 'g';//字符+数字->转ASCII码表
        String g2 = "abcdefg";//字符串+数字->从左往右先计算,遇到字符后拼接
        /*
         *double > float > long > int > short > byte
         * 隐式转换:1.byte\short->int    2.小->大
         * 强制转换:int a=300;byte b=(byte)a;
         * 字符相加:1+99+'你好'->'100你好',从左到右
         */
        //布尔
        boolean h = true;
        /*
         * 逻辑与&  短路与&&
         * 逻辑或|  短路或||
         * 逻辑异或^
         * 逻辑非!
         */
    }

    public static void keyAndOperator() {
        System.out.print("请输入一个整数");
        Scanner sc = new Scanner(System.in);//创建Scanner对象
        int i = sc.nextInt();
        if (i % 4 == 0) {
            System.out.print(i);
        } else {
            for (; i < 100; i++) {
                System.out.print(i * i);
            }
        }
        //三元运算符:    表达式?返回值1:返回值2
        /*运算符优先级
         * #一 . () {}
         * #二 ! - ++ --
         * #三 * / %
         * #四 + -
         * #五 << >> >>>
         * #六 < <= > >=
         * #七 == != # &>^>|>&&>||
         * #八 ?: # = += -= #
         */
        Random r = new Random();
        int number = 1 + r.nextInt(100);//1到100的随机数
    }

    public static void array() {
        /*数据类型[]数组名=new数据类型[]{元素}
         前一个[]可以写在数组名后面
         new数据类型[]可以省略
         数据类型[]数组名=new数据类型[长度] //动态初始化不赋值,只声明类型和长度
        */
        int[] arr = new int[]{1, 2, 3};
        int[] arr2 = {4, 5, 6};
        int first_number = arr[0];

        double[] arr3 = {1.1, 2.2, 3.3};
        System.out.print(arr3);//输出[D@776ec8f
        //  [表示数组   D表示存储数据类型为double    @间隔符

        String[] arr4 = new String[3];
        arr4[0] = "张三";
        arr4[1] = "李四";
        System.out.print(arr4[2]);//输出null
        // 整型初始值0,小数初始值0.0,字符型'\u0000'->空格,布尔初始值false,引用数据类型null

        //遍历数组  快捷方式:数组名.fori
        for (int i1 = 0; i1 < arr2.length; i1++) {
            System.out.print(arr2[i1]);
            System.out.print(i1);
        }
        for (int i2 : arr2) {
            System.out.print(i2);
        }
        System.out.print(Arrays.toString(arr2));//打印数组,和python一样
        //二维数组
        int[][] arr6 = new int[][]{{1, 2}, {11, 22, 33}};
        int[][] arr7 = {
                {1},
                {2, 3},
                {4},
                {5, 6}
        };//sout(arr7[0])打印出第一个一维数组的地址


        //switch特性
        Scanner sc = new Scanner(System.in);
        int number=sc.nextInt();//有弊端,在遇到空格,制表符时也会停止读取
        //使用String s=sc.nextLine()读取一行,只有在遇到回车时停止录入
        //类型转换见包装类
        String str=switch (number){
            case 1->"1";//等价于 case 1: str="1"; break;
            case 2->"2";
            case 3->"3";
            case 4->"4";
            case 5->"5";
            case 6->"6";
            default -> "null";
        };
        loop:while(true){
            String n =sc.next();
            switch (n){
                case "1"-> System.out.println("Hello");
                case "2"-> {//case执行多条语句加大括号
                    System.out.println("ByeBye");
                    break loop;//跳出指定循环(只写break跳出的是switch)
                    //System.exit(0);停止虚拟机运行
                }
                default -> System.out.println("no");
            }
        }
    }


    public static int method(int a) {
        return a;
    }//抽取方法快捷键:ctrl+alt+m

    public static int method(int a, int b) {
        return a + b;//方法不允许嵌套
    }

    public static double method(double a, double b) {
        return a + b;//方法重载:(同一个class内)同名方法,参数(个数|类型|顺序)不同
    }
}
