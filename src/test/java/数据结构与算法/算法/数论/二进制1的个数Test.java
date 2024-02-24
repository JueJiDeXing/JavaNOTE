package 数据结构与算法.算法.数论;

import org.junit.jupiter.api.Test;
import 数据结构与算法.算法.数论.进制.二进制1的个数;

import static org.junit.jupiter.api.Assertions.*;

class TestCount1 {
    二进制1的个数 test = new 二进制1的个数();

    @Test
    void count() {
        for (int i = 0; i < 10000; i++) {
            int c = test.count1(i);
            assertEquals(c, test.count2(i));
            assertEquals(c, test.count3(i));
            assertEquals(c, test.count4(i));
        }
    }
    /*
        0x5555 5555 -> 0b 0101 0101 0101 0101 0101 0101 0101 0101
        0xaaaa aaaa -> 0b 1010 1010 1010 1010 1010 1010 1010 1010

        0x3333 3333 -> 0b 0011 0011 0011 0011 0011 0011 0011 0011
        0xcccc cccc -> 0b 1100 1100 1100 1100 1100 1100 1100 1100

        0x0f0f 0f0f -> 0b 0000 1111 0000 1111 0000 1111 0000 1111
        0xf0f0 f0f0 -> 0b 1111 0000 1111 0000 1111 0000 1111 0000

        0x0000 ffff -> 0b 0000 0000 0000 0000 1111 1111 1111 1111
        0xffff 0000 -> 0b 1111 1111 1111 1111 0000 0000 0000 0000
         */
}
