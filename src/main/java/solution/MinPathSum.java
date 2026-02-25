package solution;

import java.util.Arrays;

public class MinPathSum {
    /* 64. 最小路径和
       解法一：动态规划 */
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int rows = grid.length, columns = grid[0].length;
        int[][] dp = new int[rows][columns]; // (i,j)：从左上角到(i,j)的最小路径和
        /* 初始化 DP 表 */
        dp[0][0] = grid[0][0]; // 初始化左上角
        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0]; // 初始化第一列
        }
        for (int j = 1; j < columns; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j]; // 初始化第一行
        }
        System.out.println(Arrays.deepToString(dp));
        /* 填充 DP 表 */
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                // 两种情况：从左或从上移动到 (i,j)
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[rows - 1][columns - 1];
    }
    /* 解法二：递归（更优）*/
    public int minPathSum2(int[][] grid) {
        int[][] cache = new int[grid.length][grid[0].length]; // 缓存访问历史
        for (int[] row : cache) {
            Arrays.fill(row, -1); // 所有元素初始化为 -1：未访问
        }
        return dfs(0, 0, grid, cache); // 从左上角开始递归
    }
    private int dfs(int i, int j, int[][] grid, int[][] cache) {
        if (i == grid.length - 1 && j == grid[0].length - 1) // 到达右下角
            return grid[i][j]; // 返回该位置的值
        if (i == grid.length || j == grid[0].length) // 越界
            return Integer.MAX_VALUE;
        if (cache[i][j] != -1) // 如果缓存中已存在值
            return cache[i][j]; // 返回缓存中的值
        return cache[i][j] = grid[i][j] + Math.min(
                dfs(i + 1, j, grid, cache), // 向下移动
                dfs(i, j + 1, grid, cache)); // 向右移动
    }

    public static void main(String[] args) {
        MinPathSum m = new MinPathSum();
        int[][] grid = {{1, 3, 1},
                        {1, 5, 1},
                        {4, 2, 1}}; // 只能向下或向右移动
        System.out.println(m.minPathSum(grid));
    }
}
