package 数据结构与算法.算法.数论;

public class 快速幂 {
    //直接for循环乘的次数为n次
    //使用快速幂,降低乘的次数

    /**
     <h1>奇偶快速幂</h1>
     1. n为奇数 a^n = a^(n/2) * a^(n/2) * a
     1. n为偶数 a^n = a^(n/2) * a^(n/2)
     */
    double fastPow1(double a, int n) {
        long N = n;
        if (n < 0) {//兼容负数
            a = 1 / a;
            N = -N;
        }
        if (N == 0) return 1.0;//兼容n为0的情况
        double temp = fastPow1(a, (int) (N / 2)); // 快速选择算法
        if (N % 2 == 1) return temp * temp * a;// 奇数个a，此时也可以写为if(n&1)
        // 偶数个a
        return temp * temp;
    }

    /**
     <h1>负次方的兼容</h1>
     */
    public double myPow(double x, int n) {
        long N = n;//这里使用long的转换,如果传入的是Integer.MIN_VALUE直接转为正数会溢出
        return N >= 0 ? quick(x, N) : 1.0 / quick(x, -N);
    }

    public double quick(double x, long N) {
        if (N == 0) return 1.0;
        double res = quick(x, N / 2);
        return N % 2 == 0 ? res * res : res * res * x;
    }

    /**
     <h1>二进制快速幂</h1>
     使用二进制拆分
     例如:a^13   13转二进制为[8,4,1] 1101  a^13 = a^8 * a^4 * a
     */
    int fastPow2(int a, int n) {
        int base = a; // 不用base直接用a也行
        int res = 1;  // 用res返回结果
        while (n != 0) {
            if ((n & 1) == 1) res *= base; // 如果n的最后一位是1，表示这个地方需要乘
            base *= base; // 推算乘积：a² --> (a²)² -->((a²)²)² ...
            n >>= 1;//n去掉最后一位
        }
        return res;
    }

    /*
    快速幂取模 计算a^n mod m
    模运算性质:
        (a[+ - *]b) mod m = ( (a mod m) [+ - *] (b mod m) ) mod m
     */
    int fastPow3(int a, int n, int m) {
        int base = a; // 不用base直接用a也行
        int res = 1;  // 用res返回结果
        while (n != 0) {
            if ((n & 1) == 1) // 如果n的最后一位是1，表示这个地方需要乘
                res = (res * base) % m;
            base = (base * base) % m; // 推算乘积：a² --> (a²)² -->((a²)²)² ...
            n >>= 1;
        }
        return res;
    }
}
