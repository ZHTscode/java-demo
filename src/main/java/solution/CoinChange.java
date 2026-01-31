package solution;

import java.util.Arrays;

public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        // 步骤1：定义DP状态
        int[] dp = new int[amount + 1]; // 存储凑出每个金额所需的最少硬币数量
        // 步骤2：初始条件（边界）
        int max = amount + 1; // 定义一个大于 amount 的值作为初始最大值
        Arrays.fill(dp, max); // 将 dp 数组所有元素都填充为 max 值
        dp[0] = 0; // 凑出金额为 0 所需的硬币数量为 0
        // 步骤4：计算子问题→原问题
        for (int i = 1; i <= amount; i++) { // 遍历每个金额
            // 步骤3：状态转移方程
            for (int coin : coins) { // 遍历每个硬币面值
                if (coin <= i)
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1); // 状态转移方程
                    // 凑出金额 (i - coin) 所需的最少硬币数再加上当前使用的这枚硬币
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
        // 如果 dp[amount] 仍为 max 值，则表示无法凑出该金额，返回 -1
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        CoinChange cc = new CoinChange();
        int result = cc.coinChange(coins, amount);
        System.out.println(result);
    }
}
