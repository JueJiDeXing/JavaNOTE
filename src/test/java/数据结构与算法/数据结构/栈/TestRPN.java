package 数据结构与算法.数据结构.栈;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestRPN {
    后缀表达式 test = new 后缀表达式();

    @Test
    void change() {
        assertEquals("1", test.change(new String[]{"1"}));
        assertThrows(RuntimeException.class, () -> test.change(new String[]{"*"}));
        assertEquals("+12", test.change(new String[]{"1", "2", "+"}));
        assertEquals("*+12+34", test.change(new String[]{"1", "2", "+", "3", "4", "+", "*"}));
        assertEquals("-1+23", test.change(new String[]{"1", "2", "3", "+", "-"}));
    }

    @Test
    void evalRPN() {
    }

    @Test
    void evalRPN2() {
    }

    @Test
    void infixToSuffix() {
    }
}
