package 课程.数值计算;

import java.math.BigInteger;
import java.util.Objects;

/**
 高精度_分数
 */
public class BigFraction {
    public BigInteger numerator;//分子
    public BigInteger denominator;//分母
    public static boolean isLazy = false;

    public BigFraction(BigFraction f) {
        this.numerator = f.numerator;
        this.denominator = f.denominator;
    }

    public BigFraction(BigInteger numerator, BigInteger denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public BigFraction(long numerator, long denominator) {
        this.numerator = BigInteger.valueOf(numerator);
        this.denominator = BigInteger.valueOf(denominator);
    }

    public BigFraction(long numerator, BigInteger denominator) {
        this.numerator = BigInteger.valueOf(numerator);
        this.denominator = denominator;
    }

    public BigFraction(BigInteger numerator, long denominator) {
        this.numerator = numerator;
        this.denominator = BigInteger.valueOf(denominator);
    }

    public BigFraction(BigInteger[] num) {
        this.numerator = num[0];
        this.denominator = num[1];
    }

    public BigFraction() {
        this.numerator = BigInteger.ZERO;
        this.denominator = BigInteger.ONE;
    }


    public BigFraction(BigInteger numerator) {
        this.numerator = numerator;
        this.denominator = BigInteger.ONE;
    }

    public BigFraction(double num) {
        int d = 1;
        while ((long) num != num) {
            num *= 10;
            d *= 10;
        }
        this.numerator = BigInteger.valueOf((long) num);
        this.denominator = BigInteger.valueOf(d);
    }

    /**
     分数加法
     */
    public BigFraction add(BigFraction B) {
        BigFraction fraction = new BigFraction(numerator.multiply(B.denominator)
                .add(B.numerator.multiply(denominator)), denominator.multiply(B.denominator));
        if (!isLazy) fraction = fraction.reduction();
        return fraction;
    }

    /**
     分数+整数
     */
    public BigFraction add(long v) {
        BigFraction fraction = new BigFraction(
                numerator.add(BigInteger.valueOf(v).multiply(denominator)), denominator);
        if (!isLazy) fraction = fraction.reduction();
        return fraction;
    }

    /**
     分数减法
     */
    public BigFraction sub(BigFraction B) {
        BigFraction fraction = new BigFraction(numerator.multiply(B.denominator).subtract(B.numerator.multiply(denominator)),
                denominator.multiply(B.denominator));
        if (!isLazy) fraction = fraction.reduction();
        return fraction;
    }

    /**
     分数-整数
     */
    public BigFraction sub(long v) {
        BigFraction fraction = new BigFraction(numerator.subtract(BigInteger.valueOf(v).multiply(denominator)), denominator);
        if (!isLazy) fraction = fraction.reduction();
        return fraction;
    }

    /*
    分数乘法
     */
    public BigFraction mul(BigFraction B) {
        BigFraction fraction = new BigFraction(numerator.multiply(B.numerator),
                denominator.multiply(B.denominator));
        if (!isLazy) fraction = fraction.reduction();
        return fraction;
    }

    /**
     分数*整数
     */
    public BigFraction mul(long v) {
        BigFraction fraction = new BigFraction(numerator.multiply(BigInteger.valueOf(v)), denominator);
        if (!isLazy) fraction = fraction.reduction();
        return fraction;
    }

    public BigFraction div(int m) {
        BigFraction fraction = new BigFraction(numerator, denominator.multiply(BigInteger.valueOf(m)));
        if (!isLazy) fraction = fraction.reduction();
        return fraction;
    }

    public BigFraction div(BigFraction B) {
        BigFraction fraction = new BigFraction(numerator.multiply(B.denominator), denominator.multiply(B.numerator));
        if (!isLazy) fraction = fraction.reduction();
        return fraction;
    }

    /**
     取倒数
     */
    public BigFraction reverse() {
        return new BigFraction(denominator, numerator);
    }

    /**
     约分
     */
    public BigFraction reduction() {
        BigInteger gcd = numerator.gcd(denominator);
        numerator = numerator.divide(gcd);
        denominator = denominator.divide(gcd);
        if (denominator.compareTo(BigInteger.ZERO) < 0) {
            numerator = numerator.negate();
            denominator = denominator.negate();
        }
        return this;
    }

    public BigFraction pow(int n) {
        BigFraction self = new BigFraction(numerator, denominator);
        BigFraction ans = new BigFraction(1, 1);
        while (n > 0) {
            if ((n & 1) == 1) ans = ans.mul(self);
            self = self.mul(self);
            n /= 2;
        }
        return ans;
    }

    public BigFraction sqrt() {
        BigInteger sqrt = numerator.multiply(denominator).sqrt();
        return new BigFraction(sqrt).mul(new BigFraction(1, denominator.multiply(denominator)));
    }

    public void setValue(long numerator, long denominator) {
        this.numerator = BigInteger.valueOf(numerator);
        this.denominator = BigInteger.valueOf(denominator);
    }

    public void setValue(BigFraction B) {
        numerator = B.numerator;
        denominator = B.denominator;
    }

    public void show() {
        if (!isLazy) reduction();
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
        BigFraction fraction = (BigFraction) o;
        this.reduction();
        fraction.reduction();
        return Objects.equals(numerator, fraction.numerator) && Objects.equals(denominator, fraction.denominator);
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
        return numerator.doubleValue() / denominator.doubleValue();
    }


}
