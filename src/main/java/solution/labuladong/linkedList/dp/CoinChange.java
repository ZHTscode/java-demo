package solution.labuladong.linkedList.dp;

import java.util.Arrays;

public class CoinChange {
    /* 322. 零钱兑换 */
    private int[] memo;
    public int coinChange(int[] coins, int amount){
        memo = new int[amount + 1];
        Arrays.fill(memo, -123);
        return dp(coins, amount);
    }
    private int dp(int[] coins, int amount){
        if(amount == 0) return 0;
        if(amount < 0) return -1;
        if(memo[amount] != -123) return memo[amount];
        int res = Integer.MAX_VALUE;
        for(int coin : coins){
            int subProblem = dp(coins, amount - coin);
            if(subProblem == -1) continue; // 子问题无解，跳过
            res = Math.min(res, subProblem + 1);
        }
        memo[amount] = (res == Integer.MAX_VALUE) ? -1 : res;
        return memo[amount];
    }

    public int coinChange2(int[] coins, int amount){
        int[] dp = new int[amount+1]; // dp[i]：金额为 i 所需的最少硬币数
        Arrays.fill(dp, amount+1); // 初始化为一个较大的数
        dp[0] = 0; // 金额0，0个硬币
        for(int i=0; i<dp.length; i++){
            for(int coin : coins){
                if(i - coin < 0) continue;
                dp[i] = Math.min(dp[i], 1+dp[i-coin]);
            }
        }
        return (dp[amount] == amount+1) ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
        int[] coins = {1, 2, 5};
        int amount = 11;
        int result = coinChange.coinChange2(coins, amount);
        System.out.println(result); // 输出 3
    }
}
