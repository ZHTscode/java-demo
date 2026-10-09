package solution.labuladong.linkedList.dp;

import java.util.Arrays;

public class MinPathSum {
    /* 64. 最小路径和 */
    private int[][] memo;
    public int minPathSum(int[][] grid){
        int m = grid.length, n = grid[0].length;
        memo = new int[m][n];
        for(int[] row : memo){
            Arrays.fill(row, -1);
        }
        return dp(grid, m-1, n-1);
    }
    // 定义：grid[0][0] 到 grid[i][j] 的最小路径和为 dp(grid, i, j)
    private int dp(int[][] grid, int i, int j){
        if(i == 0 && j == 0) return grid[0][0];
        if(i < 0 || j < 0) return Integer.MAX_VALUE;
        if(memo[i][j] != -1) return memo[i][j];
        memo[i][j] = grid[i][j] + Math.min(dp(grid, i-1, j), dp(grid, i, j-1));
        return memo[i][j];
    }

    // 迭代法
    public int minPathSum2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) // 只能从上边过来
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        for (int j = 1; j < n; j++) // 只能从左边过来
            dp[0][j] = dp[0][j - 1] + grid[0][j];

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    public int minPathSum3(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // dp[i][j] 表示从 grid[i][j] 到右下角的最小路径和
        int[][] dp = new int[m][n];

        dp[m-1][n-1] = grid[m-1][n-1];
        for (int i = m-2; i >= 0; i--) // 填充最后一列
            dp[i][n-1] = dp[i+1][n-1] + grid[i][n-1];
        for (int j = n-2; j >= 0; j--) // 填充最后一行
            dp[m-1][j] = dp[m-1][j+1] + grid[m-1][j];

        for (int i = m-2; i >= 0; i--) {
            for (int j = n-2; j >= 0; j--) {
                dp[i][j] = Math.min(dp[i+1][j], dp[i][j+1]) + grid[i][j];
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };
        MinPathSum solution = new MinPathSum();
        int res = solution.minPathSum(grid);
        System.out.println(res);
    }
}
