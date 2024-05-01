package 课程.数值计算;

/**
 高精度_分数
 */
public class Fraction {
    long numerator;//分子
    public long denominator;//分母

    public Fraction(long numerator, long denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }
    public Fraction(long numerator ) {
        this.numerator = numerator;
        this.denominator = 1;
    }

    /**
     分数加法
     */
    public Fraction add(Fraction B) {
        Fraction fraction = new Fraction(numerator * B.denominator + B.numerator * denominator,
                denominator * B.denominator);
        fraction.reduction();
        return fraction;
    }

    /**
     分数+整数
     */
    public Fraction add(long v) {
        Fraction fraction = new Fraction(numerator + v * denominator, denominator);
        fraction.reduction();
        return fraction;
    }

    /*
    分数乘法
     */
    public Fraction mul(Fraction B) {
        Fraction fraction = new Fraction(numerator * B.numerator, denominator * B.denominator);
        fraction.reduction();
        return fraction;
    }

    /**
     分数*整数
     */
    public Fraction mul(long v) {
        Fraction fraction = new Fraction(numerator * v, denominator);
        fraction.reduction();
        return fraction;
    }

    /**
     取倒数
     */
    public Fraction reverse() {
        return new Fraction(denominator, numerator);
    }

    public void reduction() {
        long gcd = gcd(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;
        if (denominator < 0) {
            denominator = -denominator;
            numerator = -numerator;
        }
    }

    public void show() {
        reduction();
        System.out.println(numerator + "/" + denominator);
    }

    @Override
    public String toString() {
        return "\\frac{ " + numerator + "/" + denominator + "}";
    }

    static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public double getValue() {
        reduction();
        return numerator / (double) denominator;
    }
}
