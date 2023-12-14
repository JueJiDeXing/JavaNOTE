package 数据结构与算法.算法.动态规划_贪心.动态规划;

public class _32最长有效括号 {
    public int longestValidParentheses(String s) {
        int ans = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '(') continue;//dp[i]=0
            //当前位为 ')'
            if (s.charAt(i - 1) == '(') {
                //前一位为'(',当前位可以与前一位匹配
                int prevLen = i >= 2 ? dp[i - 2] : 0;
                dp[i] = prevLen + 2;
            } else {
                //前一位为')',当前位需要向前找未匹配的'('
                int prevIndex = i - dp[i - 1];//i-dp[i-1]为前一个匹配项的起始位置
                // prevIndex > 0 前面需要有字符
                // s.charAt(prevIndex - 1) == '(' 且前一个字符为左括号与当前位匹配
                if (prevIndex > 0 && s.charAt(prevIndex - 1) == '(') {
                    //(prevIndex >= 2 ? dp[prevIndex - 2] : 0)查看匹配后的前面是否为匹配项,能否进行拼接
                    dp[i] = dp[i - 1] + 2 + (prevIndex >= 2 ? dp[prevIndex - 2] : 0);
                }
            }
            ans = Math.max(ans, dp[i]);

        }
        return ans;
    }
}
