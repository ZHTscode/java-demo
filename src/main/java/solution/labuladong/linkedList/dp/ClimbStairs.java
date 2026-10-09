package solution.labuladong.linkedList.dp;

public class ClimbStairs {
    /* 70. 爬楼梯 */
    // dp[n] = dp[n-2] + dp[n-1]
    public int climbStairs(int n) {
        if (n <= 2) return n;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // 递归
    public int climbStairs2(int n) {
        if (n <= 2) return n;
        return climbStairs2(n - 1) + climbStairs2(n - 2);
    }

    public static void main(String[] args) {
        ClimbStairs climbStairs = new ClimbStairs();
        int res = climbStairs.climbStairs(5);
        System.out.println(res);
    }
}
