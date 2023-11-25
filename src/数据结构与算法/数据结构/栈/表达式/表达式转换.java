package 数据结构与算法.数据结构.栈.表达式;

import java.util.Stack;

//已测试:src.java.数据结构与算法.数据结构.栈.TestRPN
public class 表达式转换 {
    /**
     <div color=rgb(155,200,80)>
     <h1>后缀转前缀</h1>
     考虑错误输入,但输入必须合法
     后缀表达式 ~~~~~~~~ 前缀表达式<br>
     3 4 + ~~~~~~~~~~~ + 3 4<br>
     5 2 * 8 7 - + ~~~~~~~ + * 5 2 - 8 7<br>
     9 2 7 * - 8 3 / + ~~~~~ + - 9 * 2 7 / 8 3<br>
     </div>
     */
    public String suffixToInfix(String[] tokens) {
        Stack<String> numStack = new Stack<>(), res = new Stack<>();//分别存数字和结果
        if (tokens.length == 0) {
            return "";
        }
        int curr = 0;//计数器,表示当前可参与运算的数字个数,用于判断表达式是否合法
        for (String token : tokens) {
            switch (token) {
                case "+", "-", "*", "/" -> {
                    if (curr < 2) throw new RuntimeException("不是正确的表达式");//遇到双目运算符,算子需要大于等于2个
                    curr--;//计算后,两个数合为1个数,算子减1
                    if (numStack.isEmpty()) {//抛出两个数字,如果不够抛,从res里抛
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
     <h1>中缀转后缀</h1>
     遇到操作符则入栈, 如果遇到更低级(或平级)的操作符 则弹出栈内的高级、平级运算符<br>
     a+b*c-d -> a 栈[+] -> ab 栈[+,*] -> abc*+ 栈[-] -> abc*+d-<br>
     括号也当做运算符, 左括号直接入栈, 右括号出栈到左括号<br>
     (a+b*c-d)*e -> 栈[(] -> ab 栈[(,+] -> abc 栈[(,+,*] -> abc*+d 栈[(,-]
     -> abc*+d- 栈[] -> abc*+d-e 栈[*] -> abc*+d-e*<br>
     优先级: 右括号<左括号<加号<乘号
     </div>
     */
    public String[] infixToSuffix(String[] tokens) {
        Stack<String> operatorStack = new Stack<>(), res = new Stack<>();
        for (String c : tokens) {
            switch (c) {
                case "*", "+", "-", "/" -> {
                    //输入操作符优先级低(或平级)时,栈内优先级高的和平级的都出栈,再入栈操作符
                    while (!operatorStack.isEmpty() && priority(operatorStack.peek()) >= priority(c)) {
                        res.push(operatorStack.pop());
                    }
                    //空栈直接推入操作符 、 优先级高的运算符直接入栈
                    operatorStack.push(c);
                }
                case "(" -> operatorStack.push(c);//左括号直接入栈
                case ")" -> {
                    //右括号则出栈到左括号
                    while (!operatorStack.isEmpty()) {
                        String pop = operatorStack.pop();
                        if (pop.equals("(")) break;
                        res.push(pop);
                    }
                }
                default -> res.push(c);
            }
        }
        //处理剩余栈内操作符
        while (!operatorStack.isEmpty()) {
            res.push(operatorStack.pop());
        }
        return res.toArray(new String[0]);
    }

    /**
     运算符优先级

     @param c 运算符
     @return 优先级权重, int
     */
    static int priority(String c) {
        return switch (c) {
            case "*", "/" -> 2;
            case "+", "-" -> 1;
            case "(" -> 0;
            default -> throw new RuntimeException("运算符不合法");
        };
    }

}
