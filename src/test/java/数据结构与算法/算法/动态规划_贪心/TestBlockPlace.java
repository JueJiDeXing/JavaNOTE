package 数据结构与算法.算法.动态规划_贪心;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestBlockPlace {

    @Test
    void place() {
        放积木 test = new 放积木();
        assertEquals(1, test.place(0));//C(0)=1
        assertEquals(1, test.place(1));//C(1)=1
        assertEquals(2, test.place(2));//C(2)=2
        assertEquals(5, test.place(3));//C(3)=5
        assertEquals(9, test.place(4));//C(4)=C(3)+C(2)+2C(1)=5+2+2=9
    }
}
