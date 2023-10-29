package 数据结构与算法.算法.动态规划;

public class 股票买卖 {
    /*
    dp[i][0]表示第i天持有现金,未购入股票
    do[i][1]表示第i天持有股票,未卖出

    初始化:dp[0][0]=0 , dp[0][1]=-prices[0]
    状态转移方程:
    dp[i][0]来源于 第i-1天未持有股票仍不购买股票,或第i-1天持有股票将其卖出
    dp[i][1]来源于 第i-1天已经持有股票,或第i-1天未持有股票购入股票
     dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
     dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);

    最后一天一定持有现金,手中没有股票,所以返回dp[最后一天][0]
     */
    public int maxProfit(int[] prices) {
        int len=prices.length;
        // int[][]dp=new int[len][2];
        // dp[0][0]=0;
        // dp[0][1]=-prices[0];
        // for(int i=1;i<len;i++){
        //     dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
        //     dp[i][1]=Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);
        // }
        // return dp[len-1][0];

        //滚动变量
        if(len<2){
            return 0;
        }
        int cash=0;
        int hold=-prices[0];
        for(int i=1;i<len;i++){
            int preCash=cash;
            int preHold=hold;
            cash=Math.max(preCash,preHold+prices[i]);
            hold=Math.max(preHold,preCash-prices[i]);

        }
        return cash;
    }
}
