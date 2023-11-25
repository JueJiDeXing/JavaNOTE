package 数据结构与算法.数据结构.栈;

import org.junit.jupiter.api.Test;
import 数据结构与算法.数据结构.栈.表达式.表达式转换;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TestRPN {
    表达式转换 test = new 表达式转换();

    @Test
    void suffixToInfix() {
        assertEquals("1", test.suffixToInfix(new String[]{"1"}));
        assertThrows(RuntimeException.class, () -> test.suffixToInfix(new String[]{"*"}));
        assertEquals("+12", test.suffixToInfix(new String[]{"1", "2", "+"}));
        assertEquals("*+12+34", test.suffixToInfix(new String[]{"1", "2", "+", "3", "4", "+", "*"}));
        assertEquals("-1+23", test.suffixToInfix(new String[]{"1", "2", "3", "+", "-"}));
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
