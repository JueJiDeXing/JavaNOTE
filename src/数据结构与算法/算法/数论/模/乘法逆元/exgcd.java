package 数据结构与算法.算法.数论.模.乘法逆元;

public class exgcd {
    /*
    裴蜀定理:
    若a,b是整数, 且gcd(a,b)=d
    那么对于任意的整数和y, ax+by都一定是d的倍数
    特别地,一定存在整数x,y,使ax+by=d成立.
    它的一个重要推论是: a,b互质 <=> 存在整数x,y,使ax+by=1.

     exgcd:
     ax mod p = 1 的最小正整数解,等价与 不定方程 ax + by = c = gcd(a,b) 的x最小正整数解
     对于不定方程 ax + by = c = gcd(a,b)
     ∵ gcd(a,b) = gcd(b,a%b)
     ∴ bx1 + (a%b)y1 = d
     又∵ a%b = a - (a/b)b
     ∴ bx1 + (a-(a/b)b)y1 = d
        bx1 + ay - b(a/b)y1 = d
        ay1 + b(x1-(a/b)y1) = d
      与ax+by=d对比得: x=y1, y=x1-(a/b)y1
      这样可以递推求解ax+by=d的解
     */
    public int inv(int a, int b) {
        x = 0;
        y = 0;
        doExgcd(a, b);
        return (x + b) % b;
    }

    static int x, y;


    /**
     求解 ax + by = gcd(a,b)
     解由static变量存储

     @param a,b 方程参数
     */
    private void doExgcd(int a, int b) {
        if (b == 0) {
            x = 1;
            y = 0;
            return;
        }
        doExgcd(b, a % b);
        int x1 = x, y1 = y;
        x = y1;
        y = x1 - (a / b) * y1;
    }
}
