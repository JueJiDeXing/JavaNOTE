package J_API;

public class J_Math {
    //数学
    public static void main(String[] args) {
        //number .abs(number x) 返回一个数的绝对值
        float a= -12.56f;//注:有范围限制
        System.out.println("a="+Math.abs(a));//输出12.56
        //number .absExact(int/long x)
        int b=-2147483648; // int类型-2147483648~2147483647
        //System.out.println(Math.absExact(b));  //如果超出范围则报错

        // double .ceil(double x)返回大于x的最小整数(进1)
        double c=13.8;
        System.out.println("c="+Math.ceil(c));//14.0
        // double .floor(double x)返回小于x的最大整数
        System.out.println("c="+Math.floor(c));//13

        //int/long .round(float/double x)四舍五入
        double d=15.69782;
        System.out.println("d="+Math.round(d));//16

        //number .max(number x,number y)返回同类型的两个值的较大值
        //number .min(number x,number y)返回同类型的两个值的较小值
        int f1=5;
        int f2=7;
        System.out.println("max="+Math.max(f1,f2));//7
        System.out.println("min="+Math.min(f1,f2));//5

        //double .pow(double x,double y)返回x的y次幂
        System.out.println(Math.pow(2,3));//8.0

        //double .sqrt(double x)对x开根号
        //double .cbrt(double x)对x开立方根
        System.out.println(Math.sqrt(4));//2.0
        System.out.println(Math.cbrt(8));//2.0

        //double .log(double x)x的自然对数
        System.out.println(Math.log(3));//ln3=1.098...
        //double .random()随机数[0.0,1.0)
        System.out.println(Math.floor(Math.random()*100)+1);//[1,100)

    }
}
