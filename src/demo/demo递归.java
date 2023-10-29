package demo;

public class demo递归 {
    public static void main(String[] args) {
        //猴子吃桃
        int n = monkey_method(1);
        System.out.println(n);
        int n2 = monkey_method2(10);
        System.out.println(n2);
        //爬楼梯
        int n3 = louti(20);
        System.out.println(n3);
    }

    public static int monkey_method(int n) {//n->第一天
        if (n == 10) {
            return 1;//第十天还没吃的时候的桃子数量
        } else {
            return 2 * (monkey_method(n + 1) + 1);//第n天还没吃的时候桃子的数量,是后一天加1的两倍
        }
    }

    public static int monkey_method2(int n) {//n->最后一天
        if (n == 1) {
            return 1;//剩一个
        } else {
            return 2 * (monkey_method2(n - 1) + 1);//第n天还没吃的时候桃子的数量,是前一天加1的两倍

        }
    }

    public static int louti(int n) {//n->楼梯阶数
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            return louti(n - 1) + louti(n - 2);//爬上第n阶等于前两阶之和

        }
    }
}
