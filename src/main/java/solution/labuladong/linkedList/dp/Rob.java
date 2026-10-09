package solution.labuladong.linkedList.dp;

import java.util.Arrays;

public class Rob {
    /* 198. 打家劫舍 */
    private int[] memo;
    public int rob(int[] nums){
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return dp(nums, 0);
    }
    // 定义：返回 dp[start..] 能抢到的最大值
    private int dp(int[] nums, int start){
        if(start >= nums.length) return 0;
        if(memo[start] != -1) return memo[start];
        int res = Math.max(dp(nums, start+1), dp(nums, start+2) + nums[start]);
        memo[start] = res;
        return res;
    }

    // 迭代法
    public int rob2(int[] nums){
        int n = nums.length;
        // dp[i] = x 表示：
        // 从第 i 间房子开始抢劫，最多能抢到的钱为 x
        // base case: dp[n] = 0
        int[] dp = new int[n + 2];
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1], nums[i] + dp[i + 2]);
        }
        return dp[0];
    }

    public static void main(String[] args) {
        int[] nums = {2,7,9,3,1};
        Rob solution = new Rob();
        int res = solution.rob2(nums);
        System.out.println(res);
    }
}
