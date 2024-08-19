public class Solution {
    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        int[] difficulty = {2, 4, 6, 8, 10};
        int[] profit = {10, 20, 30, 40, 50};
        int[] worker = {4, 5, 6, 7};
        int ans = solution.maxProfitAssignment(difficulty, profit, worker);
        System.out.println(ans);
    }

    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int res = 0;
        // 数组存储每一个难度的最大收益
        int[] maxPro = new int[100001];
        for (int i = 0; i < difficulty.length; i++) {
            maxPro[difficulty[i]] = Math.max(maxPro[difficulty[i]], profit[i]);
        }
        // 前缀最大值
        for (int i = 1; i < maxPro.length; i++) {
            maxPro[i] = Math.max(maxPro[i - 1], maxPro[i]);
        }
        // 每个工人做可以做的最高收益
        for (int w : worker) {
            res += maxPro[w];
        }
        return res;

    }
}
