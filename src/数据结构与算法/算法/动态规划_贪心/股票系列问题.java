package 数据结构与算法.算法.动态规划_贪心;

import java.util.Arrays;

public class 股票系列问题 {
}

/**
 最多买卖一次,求最大利润
 */
class _121_I {
/*
    给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
    你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。
    设计一个算法来计算你所能获取的最大利润。
    返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 */

    /**
     <h1>动态规划_贪心</h1>
     */
    public int maxProfit(int[] prices) {
        int res = 0;
        int minOfPre = prices[0];//前缀最小值
        for (int price : prices) {
            res = Math.max(res, price - minOfPre);//尝试卖出
            minOfPre = Math.min(minOfPre, price);//更新最小值
        }
        return res;
    }

    /**
     <h1>双指针</h1>
     */
    public int maxProfit2(int[] prices) {
        int i = 0;
        int j = 1;
        int res = 0;
        while (j < prices.length) {
            if (prices[j] > prices[i]) {//涨
                res = Math.max(res, prices[j] - prices[i]);
            } else {//跌
                i = j;//更新最小值
            }
            j++;
        }
        return res;
    }
}

/**
 可以买卖多次,同一时间只能持有一股,求最大利润
 */
class _122_II {
    /*
    给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
    在每一天，你可以决定是否购买和/或出售股票。
    你在任何时候 最多 只能持有 一股 股票。
    你也可以先购买，然后在 同一天 出售。
    返回 你能获得的 最大 利润 。
     */

    /**
     <h1>贪心算法</h1>
     只要后一天大于前一天就可以买卖<br>
     例:[1,2,3,4]<br>
     res = (2-1) + (3-2) + (4-3) = 3<br>
     证明:<br>
     如果任取贪心算法下的两段相邻买卖 [a1,a2]  [b1,b2]<br>
     1. a2 = b1,则两段买卖等价b2-a1的一段买卖,利润相同(允许同一天买入和卖出)<br>
     2. a2 > b1,则a2至b1为跌,b2-a1的一段买卖利润低于两段买卖<br>
     3. a2 < b1,则a2至b1为涨,那么中间必然有另一段买卖,取出的两段买卖不是相邻的<br>
     综上:贪心算法的任意两段相邻买卖的利润大于等于合并后的利润
     */
    public int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }
}

/**
 含手续费,不允许同一天买入卖出
 */
class _714_III {
    /*
    给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；
    整数 fee 代表了交易股票的手续费用。

    你可以无限次地完成交易，但是你每笔交易都需要付手续费。
    如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
    返回获得利润的最大值。

    注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
     */

    /**
     <h1>动态规划_贪心</h1>
     dp[i][0]表示第i天持有股票,dp[i][1]表示第i天不持有股票<br>
     状态转移方程:<br>
     <ul>
     <li>第i天选择持有股票:<br>
     1. 前一天有股票 不卖<br>
     2. 前一天没有股票 买入<br>
     dp[i][0]=max(dp[i-1][0],dp[i][1]-prices[i])</li>
     <li>第i天选择不持有股票:<br>
     1. 前一天有股票 卖出<br>
     2. 前一天没有股票 不买<br>
     dp[i][1]=max(dp[i-1][0]+prices[i]-fee,dp[i-1][1])</li>
     </ul>
     */
    public int maxProfit(int[] prices, int fee) {
        int len = prices.length;
        int[][] dp = new int[len][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i] - fee);
        }
        return dp[len - 1][1];
    }

    //从二维数组的等价拆分
    public int maxProfit2(int[] prices, int fee) {
        int len = prices.length;
        int[] buy = new int[len], sell = new int[len];
        buy[0] = -prices[0];
        sell[0] = 0;
        for (int i = 1; i < len; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i] - fee);
        }
        return sell[len - 1];//最后必然是卖出的
    }

    //降维
    public int maxProfit3(int[] prices, int fee) {
        int len = prices.length;
        int buy = -prices[0];
        int sell = 0;
        for (int i = 1; i < len; i++) {
            //int last_buy = buy;
            buy = Math.max(buy, sell - prices[i]);
            sell = Math.max(sell, buy + prices[i] - fee);
        }
        return sell;//最后必然是卖出的
    }
}

/**
 无手续费,含1天冷冻期,卖出后隔天才能买入
 */
class _309_IV {
    /*
    给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。

    设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:

    卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
    注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     */

    /**
     <h1>动态规划_贪心</h1>
     买入时的比较对象变为前两天卖出的结果
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len == 1) return 0;
        int[] buy = new int[len], sell = new int[len];
        buy[0] = -prices[0];
        buy[1] = -Math.min(prices[0], prices[1]);
        sell[0] = 0;
        sell[1] = Math.max(0, prices[1] - prices[0]);
        for (int i = 2; i < len; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }
        return Math.max(sell[len - 1], sell[len - 2]);
    }
}

/**
 最多买卖两次
 */
class _123_V {
    /*
    给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
    设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
    注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     */

    /**
     <h1>动态规划_贪心</h1>
     第一次买卖:<br>
     买入不依赖于卖出,买入 = 当日价格<br>
     卖出 = 昨天第一次买 + 当日价格<br>
     第二次买卖:<br>
     买入依赖于第一次的卖出,买入 = 昨天第一次卖 - 当日价格<br>
     卖出 = 昨天第二次买 + 当日价格
     */
    public int maxProfit1(int[] prices) {
        int len = prices.length;
        int[] firstBuy = new int[len], firstSell = new int[len];
        int[] secondBuy = new int[len], secondSell = new int[len];
        secondBuy[0] = firstBuy[0] = -prices[0];
        secondSell[0] = firstSell[0] = 0;
        for (int i = 1; i < len; i++) {
            firstBuy[i] = Math.max(firstBuy[i - 1], -prices[i]);
            firstSell[i] = Math.max(firstSell[i - 1], firstBuy[i - 1] + prices[i]);
            secondBuy[i] = Math.max(secondBuy[i - 1], firstSell[i - 1] - prices[i]);
            secondSell[i] = Math.max(secondSell[i - 1], secondBuy[i - 1] + prices[i]);
        }
        return secondSell[len - 1];
    }

    //降维
    public int maxProfit(int[] prices) {
        int firstBuy = Integer.MIN_VALUE, firstSell = 0;
        int secondBuy = Integer.MIN_VALUE, secondSell = 0;
        for (int price : prices) {
            firstBuy = Math.max(firstBuy, -price);
            firstSell = Math.max(firstSell, firstBuy + price);

            secondBuy = Math.max(secondBuy, firstSell - price);
            secondSell = Math.max(secondSell, secondBuy + price);
        }
        return secondSell;
    }
}

/**
 最多买卖K次
 */
class _188_VI {
    /*
    给你一个整数数组 prices 和一个整数 k ，其中 prices[i] 是某支给定的股票在第 i 天的价格。
    设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
    也就是说，你最多可以买 k 次，卖 k 次。
    注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     */

    public int maxProfit(int k, int[] prices) {
        int[] Buy = new int[k];
        int[] Sell = new int[k];
        Arrays.fill(Buy, Integer.MIN_VALUE);
        Arrays.fill(Sell, 0);
        for (int price : prices) {
            for (int i = 0; i < k; i++) {
                Buy[i] = Math.max(Buy[i], (i == 0 ? 0 : Sell[i - 1]) - price);
                Sell[i] = Math.max(Sell[i], Buy[i] + price);
            }
        }
        return Sell[k - 1];
    }

}
