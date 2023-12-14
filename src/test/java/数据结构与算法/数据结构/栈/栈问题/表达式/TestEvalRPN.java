package 数据结构与算法.数据结构.栈.栈问题.表达式;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestEvalRPN {
    计算后缀表达式 test = new 计算后缀表达式();

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

}
