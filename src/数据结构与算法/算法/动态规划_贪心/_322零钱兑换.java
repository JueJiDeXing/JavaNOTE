package 数据结构与算法.算法.动态规划_贪心;

import java.util.*;

public class _322零钱兑换 {
    public static void main(String[] args) {
        System.out.println(change2(new int[]{1, 2, 5}, 5));
    }
    /*
    给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
    计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
    你可以认为每种硬币的数量是无限的。
     */

    /**
     <h1>动态规划_贪心</h1>
     最少硬币个数=min(1＋剩余容量下最少个数,当前个数)
     dp[i] = min(1 + dp[i - coin], dp[i])<br>
     */
    public static int change(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount + 1];
        for (int j = 1; j < amount + 1; j++) {
            if (j >= coins[0]) {
                dp[0][j] = dp[0][j - coins[0]] + 1;
            } else {
                dp[0][j] = amount + 1;//最大值
            }
        }
        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j < amount + 1; j++) {
                if (j >= coins[i]) {//能够装下硬币
                    //最少硬币个数=min(1＋剩余容量下最少个数,当前个数)
                    dp[i][j] = Math.min(1 + dp[i][j - coins[i]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        int res = dp[coins.length - 1][amount];
        return res > amount ? -1 : res;//如果是设置的最大值,则说明无法凑成,返回-1
    }

    /**
     <h1>动态规划_贪心:降维</h1>
     */
    public static int change2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {//i需要大于coin表示装得下,所以从coin开始
                //能够装下硬币
                //最少硬币个数=min(1＋剩余容量下最少个数,当前个数)
                dp[i] = Math.min(1 + dp[i - coin], dp[i]);
                //不能则不变
            }
        }
        int res = dp[amount];
        return res > amount ? -1 : res;
        /*示例
            0   1   2   3   4       5       6
        1   0   1   11  111 1111    11111   111111
        2   0   1   2   21  22      221     222
        5   0   1   2   21  22      5       51
         */
    }
}

class _518零钱兑换II {
    /*
    给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
    请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
    假设每一种面额的硬币有无限个。
    题目数据保证结果符合 32 位带符号整数。
     */
    public static void main(String[] args) {
        System.out.println(change2(new int[]{5, 2, 1}, 5));
    }

    /**
     <h1>动态规划_贪心</h1>
     dp[i][j]:使用硬币i,总金额为j的硬币组合数
     状态转移方程:
     放得下:上一次的数量+减去当前硬币后剩余空间的组合数
     dp[i][j]=dp[i-1][j]+dp[i][j-coin]
     放不下:等于上一次的数量
     dp[i][j]=dp[i-1][j]
     初始化:由于当前硬币正好等于总金额时(j-coin=0),次数为1,所以dp[i][0]=1
     */
    public static int change(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount + 1];
        for (int i = 0; i < coins.length; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= amount; i++) {//第一行
            if (i >= coins[0]) {//放得下
                dp[0][i] = dp[0][i - coins[0]];//组合数为1,因为只使用一种硬币,且个数固定
            }
            //放不下为0
        }
        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j >= coins[i]) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[coins.length - 1][amount];
    }

    /**
     <h1>搜索</h1>
     具体注释见 递归_回溯-搜索-排列组合-组数总和
     */
    public static int change2(int[] coins, int amount) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(coins, amount, 0, res, new ArrayList<>());
        return res.size();
    }

    public static void dfs(int[] candidates, int remainder, int start, List<List<Integer>> res, List<Integer> list) {

        if (remainder < 0) {
            return;
        } else if (remainder == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            int candidate = candidates[i];
            list.add(candidate);
            dfs(candidates, remainder - candidate, i, res, list);
            list.remove(list.size() - 1);
        }
    }

    /**
     <h1>暴力递归</h1>
     超时
     */
    public static int change3(int[] coins, int amount) {
        coins = Arrays.stream(coins)
                .boxed() // 将int数组中的元素包装为Integer对象
                .sorted(Comparator.reverseOrder()) // 使用Comparator.reverseOrder()反转排序顺序
                .mapToInt(Integer::intValue) // 将Integer对象重新转换为int
                .toArray();

        return rec(0, coins, amount, new LinkedList<>(), true);
    }

    /**
     求凑成剩余金额的解的个数<br>
     从索引0开始,分路暴力枚举,索引i只能选择i~coins.length-1的硬币,0~i-1的硬币已被处理过

     @param index     未处理的硬币索引
     @param coins     硬币
     @param remainder 剩余金额
     @param stack     报存组合的硬币信息
     @param first     是否为第一次调用函数
     @return 解的个数
     */
    private static int rec(int index, int[] coins, int remainder, LinkedList<Object> stack, boolean first) {
        if (!first) {
            stack.push(coins[index]);
        }
        int count = 0;
        if (remainder < 0) {//1.剩余金额<0 无解
            System.out.println("-无解:" + stack);

        } else if (remainder == 0) {//2.剩余金额==0 有解
            System.out.println("有解:" + stack);
            count = 1;
        } else {//3.剩余金额>0 继续递归
            for (int i = index; i < coins.length; i++) {
                // 剩余金额-当前硬币 进入下次递归
                count += rec(i, coins, remainder - coins[i], stack, false);
            }
        }
        if (!stack.isEmpty()) {
            stack.pop();
        }
        return count;
    }


}
