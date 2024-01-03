package 数据结构与算法.数据结构.栈.栈问题.表达式;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TestRPN {


    @Test
    void suffixToInfix() {
        后缀转前缀 test = new 后缀转前缀();
        assertEquals("1", test.suffixToInfix(new String[]{"1"}));
        assertThrows(RuntimeException.class, () -> test.suffixToInfix(new String[]{"*"}));
        assertEquals("+12", test.suffixToInfix(new String[]{"1", "2", "+"}));
        assertEquals("*+12+34", test.suffixToInfix(new String[]{"1", "2", "+", "3", "4", "+", "*"}));
        assertEquals("-1+23", test.suffixToInfix(new String[]{"1", "2", "3", "+", "-"}));
    }



    @Test
    void infixToSuffix() {中缀转后缀 test = new 中缀转后缀();
        assertEquals("[1]", Arrays.toString(test.infixToSuffix(new String[]{"1"})));
        assertEquals("[1, 2, +]", Arrays.toString(test.infixToSuffix(new String[]{"1", "+", "2"})));
        assertEquals("[1, 2, 3, *, +]", Arrays.toString(test.infixToSuffix(new String[]{"1", "+", "2", "*", "3"})));
        assertEquals("[1, 2, +, 3, *]", Arrays.toString(test.infixToSuffix(new String[]{"(", "1", "+", "2", ")", "*", "3"})));
        assertEquals("[1, 2, +, 3, 4, +, *]", Arrays.toString(test.infixToSuffix(new String[]{"(", "1", "+", "2", ")", "*", "(", "3", "+", "4", ")"})));
    }
}
