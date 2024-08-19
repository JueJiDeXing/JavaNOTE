package 课程.数值计算;

import java.util.Objects;

/**
 高精度_分数
 */
public class Fraction {
    public   long numerator;//分子
    public long denominator;//分母

    public Fraction(Fraction f) {
        this.numerator = f.numerator;
        this.denominator = f.denominator;
    }

    public Fraction(long numerator, long denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction(long[] num) {
        this.numerator = num[0];
        this.denominator = num[1];
    }

    public Fraction() {
        this.numerator =0;
        this.denominator = 1;
    }

    public Fraction(int[] num) {
        this.numerator = num[0];
        this.denominator = num[1];
    }

    public Fraction(long numerator) {
        this.numerator = numerator;
        this.denominator = 1;
    }

    public Fraction(double num) {
        int d = 1;
        while ((long) num != num) {
            num *= 10;
            d *= 10;
        }
        this.numerator = (long) num;
        this.denominator = d;
    }

    /**
     分数加法
     */
    public Fraction add(Fraction B) {
        Fraction fraction = new Fraction(numerator * B.denominator + B.numerator * denominator, denominator * B.denominator);
        return fraction.reduction();
    }

    /**
     分数+整数
     */
    public Fraction add(long v) {
        Fraction fraction = new Fraction(numerator + v * denominator, denominator);
        return fraction.reduction();
    }

    /**
     分数减法
     */
    public Fraction sub(Fraction B) {
        Fraction fraction = new Fraction(numerator * B.denominator - B.numerator * denominator,
                denominator * B.denominator);
        return fraction.reduction();
    }

    /**
     分数-整数
     */
    public Fraction sub(long v) {
        Fraction fraction = new Fraction(numerator - v * denominator, denominator);
        return fraction.reduction();
    }

    /*
    分数乘法
     */
    public Fraction mul(Fraction B) {
        Fraction fraction = new Fraction(numerator * B.numerator, denominator * B.denominator);
        return fraction.reduction();
    }

    /**
     分数*整数
     */
    public Fraction mul(long v) {
        Fraction fraction = new Fraction(numerator * v, denominator);
        return fraction.reduction();
    }

    public Fraction div(int m) {
        Fraction fraction = new Fraction(numerator, m * denominator);
        return fraction.reduction();
    }

    public Fraction div(Fraction B) {
        Fraction fraction = new Fraction(numerator * B.denominator, denominator * B.numerator);
        return fraction.reduction();
    }

    /**
     取倒数
     */
    public Fraction reverse() {
        return new Fraction(denominator, numerator);
    }

    /**
     约分
     */
    public Fraction reduction() {
        long gcd = gcd(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;
        if (denominator < 0) {
            denominator = -denominator;
            numerator = -numerator;
        }
        return this;
    }

    public Fraction pow(int n) {
        Fraction self = new Fraction(numerator, denominator);
        Fraction ans = new Fraction(1, 1);
        while (n > 0) {
            if ((n & 1) == 1) ans = ans.mul(self);
            self = self.mul(self);
            n /= 2;
        }
        return ans;
    }

    public Fraction sqrt() {
        double t = Math.sqrt(numerator * denominator);
        return new Fraction(t).mul(new Fraction(1, denominator * denominator));
    }

    public void setValue(long numerator, long denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public void setValue(Fraction B) {
        numerator = B.numerator;
        denominator = B.denominator;
    }

    public void show() {
        reduction();
        System.out.println(numerator + "/" + denominator);
    }

    @Override
    public String toString() {
        return "{" + numerator + "/" + denominator + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fraction fraction = (Fraction) o;
        this.reduction();
        fraction.reduction();
        return numerator == fraction.numerator && denominator == fraction.denominator;
    }

    @Override
    public int hashCode() {
        this.reduction();
        return Objects.hash(numerator, denominator);
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
