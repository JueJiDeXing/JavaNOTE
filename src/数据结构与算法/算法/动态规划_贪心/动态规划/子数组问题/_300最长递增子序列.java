package 数据结构与算法.算法.动态规划_贪心.动态规划.子数组问题;

import java.util.Arrays;

public class _300最长递增子序列 {
    /*
    [10,2,5,3,3,7,101,18]  --> result=len([2,3,7,101])=4
     */
    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6}));
    }

    /**
     dp[i]表示以i结尾组成的最长递增子序列长度
     状态转移方程:
     dp[i]=MAX(dp[j]+1) // i与j的数字进行组合(其中0<=j<i,且nums[j]与nums[i]满足升序)
     */
    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {//第一个数字不需要再处理
            for (int j = 0; j < i; j++) {//第i个数字与0~i进行组合
                if (nums[i] > nums[j]) {//满足升序
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            System.out.println(Arrays.toString(dp));
        }

        return Arrays.stream(dp).max().getAsInt();
    }
}
