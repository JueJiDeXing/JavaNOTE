package 数据结构与算法.算法.动态规划_贪心;

//已测试:src.java.数据结构与算法.算法.动态规划_贪心.TestBlockPlace
public class 放积木 {
    /*
    输入一个整数N,代表有一个2行N列的矩形
    你有2种形状的积木,长条(2)和弯三角(3)
    ┌─┐  and ┌─┐
    │ │      │ └──┐
    └─┘      └────┘
    返回摆放积木的情况种数
     */

    /**
     C(N):N列有多少种摆放情况<br>
     选择:<br>
     1.竖放一个长条,剩余N-1列<br>
     2.横放两个长条,剩余N-2列<br>
     3.叠放两个弯三角(有两种叠放方式),剩余N-3列<br>
     递推式:C(N)=C(N-1)+C(N-2)+2C(N-3)<br>
     初始条件:C(0)=1 C(1)=1 C(2)=2
     */
    public int place(int N) {
        if (N <= 2) return N == 0 ? 1 : N;
        int[] dp = new int[N + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + 2 * dp[i - 3];
        }
        return dp[N];
    }
}
