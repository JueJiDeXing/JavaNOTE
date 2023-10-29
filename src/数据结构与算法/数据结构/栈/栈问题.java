package 数据结构与算法.数据结构.栈;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class 栈问题 {
}

class 双栈模拟队列{
    /*
       队列头            队列尾

       顶     底     底      顶
       s1                   s2

       入队列:调用s2.push()
       出队列:s1不为空则直接调用s1.pop(),否则先把s2移至s1,再调用s1.pop()
     */
    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();

    public void push(int x) {
        s2.push(x);
    }

    public int pop() {
        if (s1.isEmpty()) {
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }
        return s1.pop();
    }
    public  int peek(){
        if (s1.isEmpty()) {
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }
        return s1.peek();
    }
    public boolean isEmpty(){
        return s1.isEmpty()&&s2.isEmpty();
    }
}
class 队列模拟栈{
    /*
       栈顶                栈底

       队列头              队列尾


       入栈:栈为空则调用queue.offer(),不为空则添加后再把前面的元素从队列头移除加在队列尾
       出栈:调用queue.pop()
     */
    int size=0;
    Queue<Integer> queue=new LinkedList<>();
    public void push(int x){
        queue.offer(x);
        for (int i = 0; i < size; i++) {
            queue.offer(queue.poll());
        }
        size++;
    }
    public int pop(){
        if (queue.isEmpty()){
            throw new RuntimeException("空队列");
        }
        size--;
        return queue.poll();
    }
    public int top(){
        if (queue.isEmpty()){
            throw new RuntimeException("空队列");
        }
        return queue.peek();
    }
    public boolean isEmpty(){
        return queue.isEmpty();
    }

}
class 出栈序列{
    /*
    元素以 1,2,...,n进栈 ,进栈的同时随时可以出栈,最后一个元素入栈后,全部出栈
    问出栈顺序有多少
     */

    /*
    卡特兰数应用
    C(n)= &sum i=0 → n-1  C(i)*C(n-1-i)<br>
    例:n=4
    右节点表示在之前出栈的个数,左节点表示在之后出栈的个数
        1最先出        1第二出        1第三出        1最后出
        /   \          /  \         /   \         /   \
      C(3)  C(0)     C(2) C(1)    C(1)  C(2)     C(0)  C(3)
     */
    public int outNum(int n){
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            //求第C(i)的拆分
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - 1 - j];
            }

        }
        return dp[n];
    }
}
