package 数据结构与算法.数据结构.栈;

import java.util.Stack;

public class 后缀表达式 {


    /**
     <div color=rgb(155,200,80)>
     <h1>后缀转前缀</h1>
     不考虑错误输入
     后缀表达式 ~~~~~~~~ 前缀表达式<br>
     3 4 + ~~~~~~~~~~~ + 3 4<br>
     5 2 * 8 7 - + ~~~~~~~ + * 5 2 - 8 7<br>
     9 2 7 * - 8 3 / + ~~~~~ + - 9 * 2 7 / 8 3<br>

     </div>
     */
    public String change(String[] tokens) {
        Stack<String> numStack = new Stack<>(), res = new Stack<>();//分别存数字和结果
        if (tokens.length == 0) {
            return "";
        }
        int curr = 0;//计数器,表示当前可参与运算的数字个数,用于判断表达式是否合法
        for (String token : tokens) {
            switch (token) {
                case "+", "-", "*", "/" -> {
                    if (curr < 2) {//遇到双目运算符,算子需要大于等于2个
                        throw new RuntimeException("不是正确的表达式");
                    }
                    curr--;//计算后,两个数合为1个数,算子减1
                    //抛出两个数字,如果不够抛,从res里抛
                    if (numStack.isEmpty()) {
                        String s1 = res.pop();
                        String s2 = res.pop();
                        res.push(token + s2 + s1);
                    } else {
                        String s2 = numStack.pop();
                        String s1;
                        if (numStack.isEmpty()) {
                            s1 = res.pop();//s1是从res栈抛,那么数字栈抛出的数字是在s1前面还没运算的数
                            res.push(token + s2 + s1);
                        } else {
                            s1 = numStack.pop();
                            res.push(token + s1 + s2);//两个都从数字栈抛,先抛的放后面
                        }
                    }
                }
                default -> {
                    numStack.push(token);
                    curr++;//算子加1
                }
            }
        }
        if (!numStack.isEmpty()) {
            if (tokens.length == 1) {
                return tokens[0];
            }
            throw new RuntimeException("不是正确的表达式");
        }
        return res.pop();
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>计算后缀表达式:不带括号</h1>
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
     <h1>计算后缀表达式:不带括号</h1>
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

    /**
     <div color=rgb(155,200,80)>
     <h1>中缀转后缀</h1>
     遇到操作符则入栈,如果遇到更低级(或平级)的操作符则弹出栈内的高级和平级运算符<br>
     a+b*c-d -> a 栈[+] -> ab 栈[+,*] -> abc*+ 栈[-] -> abc*+d-<br>
     括号也当做运算符,左括号都直接入栈, 右括号出栈到左括号<br>
     (a+b*c-d)*e -> 栈[(] -> ab 栈[(,+] -> abc 栈[(,+,*] -> abc*+d 栈[(,-]
     -> abc*+d- 栈[] -> abc*+d-e 栈[*] -> abc*+d-e*<br>
     优先级:右括号<左括号<加号<乘号
     </div>
     */
    public String infixToSuffix(String tokens) {
        Stack<Character> stack = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder(tokens.length());
        for (int i = 0; i < tokens.length() - 1; i++) {
            char c = tokens.charAt(i);
            switch (c) {
                case '*', '+', '-', '/' -> {
                    if (stack.isEmpty()) {
                        stack.push(c);//空栈直接推入操作符
                    } else {
                        if (priority(c) > priority(stack.peek())) {
                            //优先级高的运算符需要入栈
                            stack.push(c);
                        } else {
                            //输入操作符优先级低(或平级)时,栈内优先级高的和平级的都出栈,再操作符入栈
                            while (!stack.isEmpty() && priority(stack.peek()) >= priority(c)) {
                                stringBuilder.append(stack.pop());
                            }
                            stack.push(c);
                        }
                    }
                }
                case '(' -> stack.push(c);//左括号直接入栈
                case ')' -> {
                    //右括号则出栈到左括号
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        stringBuilder.append(stack.pop());
                    }
                    stack.pop();
                }
                default -> stringBuilder.append(c);
            }
            //处理剩余栈内操作符
            while (!stack.isEmpty()) {
                stringBuilder.append(stack.pop());
            }
        }
        return stringBuilder.toString();
    }

    /**
     运算符优先级

     @param c 运算符
     @return 优先级权重, int
     */
    static int priority(char c) {
        return switch (c) {
            case '*', '/' -> 2;
            case '+', '-' -> 1;
            case '(' -> 0;
            default -> throw new RuntimeException("运算符不合法");
        };
    }

}
