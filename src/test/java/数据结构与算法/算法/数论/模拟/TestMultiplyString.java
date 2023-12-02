package 数据结构与算法.算法.数论.模拟;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestMultiplyString {

    @Test
    void multiply() {
        _43字符串相乘 test = new _43字符串相乘();
        Random random = new Random();
        String n1, n2;
        BigInteger num1,num2;
        for (int i = 0; i < 100; i++) {
            n1=String.valueOf(Math.abs(random.nextLong()));
            n2=String.valueOf(Math.abs(random.nextLong()));
            System.out.println("n1:"+n1+",n2:"+n2);
            num1=new BigInteger(n1);
            num2=new BigInteger(n2);
            assertEquals(num1.multiply(num2).toString(), test.multiply(n1, n2));
        }

    }
}
