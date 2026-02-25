package solution;

public class NumSquares {
    /* 279. 完全平方数 */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) { // 枚举所有 ≤ i 的完全平方数
            // 初始化：最坏情况是用 i 个 1 相加
            dp[i] = i;
            for (int j = 1; j * j <= i; j++) { // 从前往后
                // dp[i - j * j] ：和为 i - j * j 所需的最少个数
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        NumSquares ns = new NumSquares();
        System.out.println(ns.numSquares(12));
    }
}
