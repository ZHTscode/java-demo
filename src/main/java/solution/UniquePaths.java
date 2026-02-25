package solution;

public class UniquePaths {
    /* 62. 不同路径
       类似 64. 最小路径和
       解法一：动态规划 */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n]; // (i,j)：从左上角到(i,j)的不同路径数
        /* 初始化 DP 表 */
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1; // 初始化第一列
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1; // 初始化第一行
        }
        /* 填充 DP 表 */
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 两种情况：从左或从上移动到 (i,j)
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
    /* 解法二：组合数学 */
    public int uniquePaths2(int m, int n) {
        long ans = 1;
        for (int x = n, y = 1; y < m; ++x, ++y) {
            ans = ans * x / y;
        }
        return (int) ans;
    }
    /* 解法三：递归 + 记忆化搜索 */
    public int uniquePaths3(int m, int n) {
        int[][] memo = new int[m][n];
        return dfs(memo, m-1, n-1);
    }
    public int dfs(int[][] memo, int m, int n) {
        if (m == 0 || n == 0) return 1;
        if (memo[m][n] != 0) return memo[m][n];
        memo[m][n] = dfs(memo, m-1, n) + dfs(memo, m, n-1);
        return memo[m][n];
    }

    public static void main(String[] args) {
        UniquePaths u = new UniquePaths();
        System.out.println(u.uniquePaths(3, 7));
    }
}
