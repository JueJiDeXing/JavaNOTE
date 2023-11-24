package 数据结构与算法.数据结构.栈;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
        assertEquals(1, test.evalRPN(new String[]{"1"}));
        assertEquals(3, test.evalRPN(new String[]{"1", "2", "+"}));
        assertEquals(21, test.evalRPN(new String[]{"1", "2", "+", "3", "4", "+", "*"}));
        assertEquals(-4, test.evalRPN(new String[]{"1", "2", "3", "+", "-"}));
    }

    @Test
    void evalRPN2() {
        assertEquals(1, test.evalRPN2(new String[]{"1"}));
        assertEquals(3, test.evalRPN2(new String[]{"1", "2", "+"}));
        assertEquals(21, test.evalRPN2(new String[]{"1", "2", "+", "3", "4", "+", "*"}));
        assertEquals(-4, test.evalRPN2(new String[]{"1", "2", "3", "+", "-"}));
    }

    @Test
    void infixToSuffix() {
        assertEquals("[1]", Arrays.toString(test.infixToSuffix(new String[]{"1"})));
        assertEquals("[1, 2, +]", Arrays.toString(test.infixToSuffix(new String[]{"1", "+", "2"})));
        assertEquals("[1, 2, 3, *, +]", Arrays.toString(test.infixToSuffix(new String[]{"1", "+", "2", "*", "3"})));
        assertEquals("[1, 2, +, 3, *]", Arrays.toString(test.infixToSuffix(new String[]{"(", "1", "+", "2", ")", "*", "3"})));
        assertEquals("[1, 2, +, 3, 4, +, *]", Arrays.toString(test.infixToSuffix(new String[]{"(", "1", "+", "2", ")", "*", "(", "3", "+", "4", ")"})));
    }
}
