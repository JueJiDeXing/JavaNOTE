package 数据结构与算法.算法.数论.进制;

public class 不使用加减计算整数和 {
    public int getSum(int a, int b) {
        int carry=0;
        while(b!=0){
            carry=(a&b)<<1;//都为1则说明要进位
            a=a^b;//存储不需要进位的和
            b=carry;//下次循环计算:未进位的结果+进位值
        }
        return a;
    }
}
