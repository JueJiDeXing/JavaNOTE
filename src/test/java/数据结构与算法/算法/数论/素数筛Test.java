package 数据结构与算法.算法.数论;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestPrime {
    素数筛 test = new 素数筛();

    @Test
    void isCorrect_isPrime() {
        for (int n = 2; n < 1000; n++) {
            boolean f = test.isPrime1(n);
            assertEquals(f, test.isPrime2(n));
        }
    }

    @Test
    void isCorrect_Count() {
        for (int n = 10; n < 1000; n++) {
            int count = test.count1(n);
            assertEquals(count, test.count2(n));
            assertEquals(count, test.count3(n));
        }
    }
}
