package solution;

public class MaxProfit2 {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int n = prices.length;
        // f[i][0]: i 天结束，手上持有股票，累计最大收益
        // f[i][1]: i 天结束，手上不持有股票，处于冷冻期，累计最大收益
        // f[i][2]: i 天结束，手上不持有股票，不在冷冻期，累计最大收益
        int[][] f = new int[n][3];
        f[0][0] = -prices[0];
        for (int i = 1; i < n; ++i) {
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][2] - prices[i]);
            f[i][1] = f[i - 1][0] + prices[i];
            f[i][2] = Math.max(f[i - 1][1], f[i - 1][2]);
        }
        return Math.max(f[n - 1][1], f[n - 1][2]);
    }
    // 空间优化版本
    public int maxProfit2(int[] prices) {
        if (prices.length == 0) return 0;
        int n = prices.length;
        int f0 = -prices[0]; // 持有
        int f1 = 0; // 不持有，冷冻期
        int f2 = 0; // 不持有，非冷冻期
        for (int i = 1; i < n; ++i) {
            int newf0 = Math.max(f0, f2 - prices[i]); // 后一天，持有
            int newf1 = f0 + prices[i]; // 后一天，不持有，冷冻期
            int newf2 = Math.max(f1, f2); // 后一天，不持有，非冷冻期
            f0 = newf0;
            f1 = newf1;
            f2 = newf2;
        }
        return Math.max(f1, f2); // 最后一天，不持有股票，处于冷冻期或非冷冻期，累计最大收益
    }

    public static void main(String[] args) {
        MaxProfit2 maxProfit2 = new MaxProfit2();
        int[] prices = {1,2,3,0,2};
        System.out.println(maxProfit2.maxProfit(prices));
    }

}
