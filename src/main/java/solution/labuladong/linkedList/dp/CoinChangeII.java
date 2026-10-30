package solution.labuladong.linkedList.dp;

public class CoinChangeII {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        // dp[i][j] 表示使用前 i 个硬币，组成金额 j 的方法数
        int[][] dp = new int[n + 1][amount + 1];
        // base case
        for (int i = 0; i <= n; i++)
            dp[i][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++)
                if (j - coins[i-1] >= 0)
                    dp[i][j] = dp[i - 1][j] // 不使用 coins[i-1]
                            + dp[i][j - coins[i-1]]; // 使用 coins[i-1]
                else
                    dp[i][j] = dp[i - 1][j];
        }
        return dp[n][amount];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 5;
        CoinChangeII solution = new CoinChangeII();
        int res = solution.change(amount, coins);
        System.out.println(res);
    }
}