package 数据结构与算法.算法.动态规划_贪心.动态规划;

public class 最大正方形 {


}

class _221最大正方形 {
    /*
   在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
    */
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];//dp[i][j]:以[i,j]为右下角的最大正方形边长
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    //取左边,上边,左上的最小边长(正方形不能包含0)
                    dp[i + 1][j + 1] = 1 + Math.min(dp[i][j + 1], Math.min(dp[i + 1][j], dp[i][j]));
                    max = Math.max(max, dp[i + 1][j + 1]);
                }
            }
        }
        return max * max;
    }
}

class _1139最大的以1为边界的正方形 {
    /*
    给你一个由若干 0 和 1 组成的二维网格 grid，
    请你找出边界全部由 1 组成的最大 正方形 子网格，并返回该子网格中的元素数量。
    如果不存在，则返回 0。
     */

    /**
     <h1>动态规划</h1>
     <ul>
     <li>思路:<br>
     枚举右下角 (i,j) 和 最大边长 l <br>
     left[i][j] 表示 (i,j) 左边连续 1 的个数, up[i][j] 表示上方连续 1 的个数<br>
     合法正方形要满足: 右下的left, 右下的up, 左下的up, 右上的left 都大于l
     </li>
     <li>递推关系:<br>
     如果 (i,j) 为 0, 则 left[i][j] 为 0 <br>
     如果 (i,j) 为 1, 则 left[i][j] 为 left[i][j-1] + 1 <br>
     up同理
     </li>
     <li>优化:<br>
     要找最大的正方形, 那么枚举边长 l 时可以直接从最大开始枚举
     </li>
     </ul>
     */
    public int largest1BorderedSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] left = new int[m + 1][n + 1];
        int[][] up = new int[m + 1][n + 1];
        int maxBorder = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (grid[i - 1][j - 1] == 1) {
                    left[i][j] = left[i][j - 1] + 1;
                    up[i][j] = up[i - 1][j] + 1;
                    int border = Math.min(left[i][j], up[i][j]);
                    while (left[i - border + 1][j] < border || up[i][j - border + 1] < border) {
                        border--;
                    }
                    maxBorder = Math.max(maxBorder, border);
                }
            }
        }
        return maxBorder * maxBorder;
    }
}
