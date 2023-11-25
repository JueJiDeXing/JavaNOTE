package 数据结构与算法.数据结构.栈.表达式;

import java.util.Stack;

public class 计算后缀表达式 {
    /**
     <div color=rgb(155,200,80)>
     <h1>计算后缀表达式</h1>
     遇到数字则入栈,遇到操作符则弹出操作数,计算后再入栈<br>
     ["2","1","+","3","*"]-> 栈[2] -> 栈[2,1] -> 2+1 -> 栈[3] -> 栈[3,3] -> 3*3 -> 栈[9] ->9
     </div>
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            switch (token) {
                case "+" -> {
                    Integer b = stack.pop();//弹出两个(注意顺序)
                    Integer a = stack.pop();
                    stack.push(a + b);//计算后再入栈
                }
                case "-" -> {
                    Integer b = stack.pop();
                    Integer a = stack.pop();
                    stack.push(a - b);
                }
                case "*" -> {
                    Integer b = stack.pop();
                    Integer a = stack.pop();
                    stack.push(a * b);
                }
                case "/" -> {
                    Integer b = stack.pop();
                    Integer a = stack.pop();
                    stack.push(a / b);
                }
                default -> stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>计算后缀表达式</h1>
     自底而上的递归分治法
     </div>
     */
    int index;

    public int evalRPN2(String[] tokens) {
        index = tokens.length - 1;
        return getNext(tokens);
    }

    private int getNext(String[] tokens) {
        int tmp;
        switch (tokens[index]) {
            case "+" -> {
                index--;
                return getNext(tokens) + getNext(tokens);
            }
            case "-" -> {
                index--;
                tmp = getNext(tokens);
                return getNext(tokens) - tmp;
            }
            case "*" -> {
                index--;
                return getNext(tokens) * getNext(tokens);
            }
            case "/" -> {
                index--;
                tmp = getNext(tokens);
                return getNext(tokens) / tmp;
            }
            default -> {
                return Integer.parseInt(tokens[index--]);
            }
        }
    }

}
