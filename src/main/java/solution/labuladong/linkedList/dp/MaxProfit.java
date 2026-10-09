package solution.labuladong.linkedList.dp;

public class MaxProfit {
    /* 121. 买卖股票的最佳时机 */
    // 原始版本
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // dp[i][0] 表示第 i 天不持股时，前 i 天的最大利润
        // dp[i][1] 表示第 i 天持股时，前 i 天的最大利润
        int[][] dp = new int[n + 1][2];
        dp[0][0] = 0;
        dp[0][1] = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            // 1. 昨天就不持股，今天不动：dp[i-1][0]
            // 2. 昨天持股，今天卖出：dp[i-1][1] + prices[i-1]
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i-1]);
            // 1. 昨天持股，今天不动：dp[i-1][1]
            // 2. 昨天不持股，今天买入：-prices[i-1]
            dp[i][1] = Math.max(dp[i-1][1], -prices[i-1]);
        }
        return dp[n][0]; // 最终结果在 dp[n][0]
    }

    // 空间复杂度优化版本
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        // base case: dp[-1][0] = 0, dp[-1][1] = -infinity
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            // dp[i][1] = max(dp[i-1][1], -prices[i])
            dp_i_1 = Math.max(dp_i_1, -prices[i]);
        }
        return dp_i_0;
    }

    public static void main(String[] args) {
        MaxProfit solution = new MaxProfit();
        int[] prices = {7,1,5,3,6,4};
        System.out.println(solution.maxProfit2(prices));
    }
}
