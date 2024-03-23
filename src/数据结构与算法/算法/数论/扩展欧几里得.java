package 数据结构与算法.算法.数论;

public class 扩展欧几里得 {
    static class Pair {
        long x, y;

        public Pair(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    public static long exgcd(long a, long b, long x, long y) {
        Pair res = new Pair(x, y);
        return (res.x + b) % b;
    }

    /**
     扩展欧几里得算法：求方程ax+by=gcd(a, b)的解

     @param a: x的系数
     @param b: y的系数
     @param x: 当前方程的解1
     @param y: 当前方程的解2
     @return a和b的最大公约数
     */
    private static long exgcd(long a, long b, long[] x, long[] y) {
        if (b == 0) {
            x[0] = 1;
            y[0] = 0;
            return a;
        }
        long[] x1 = new long[1];
        long[] y1 = new long[1];

        long d;
        d = exgcd(b, a % b, x1, y1);
        x[0] = y1[0];
        y[0] = x1[0] - a / b * y1[0];
        return d;
    }


}
