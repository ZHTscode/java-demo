package solution;

import java.util.Arrays;

public class Rob {
    /* 198.打家劫舍 */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int[] dp = new int[nums.length + 1]; // dp[i] 表示前 i 个房子能抢到的最大金额总和
        dp[1] = nums[0]; // 前 1 个房子只能抢 1 个
        System.out.println("dp[1] = " + dp[1]);
        dp[2] = Math.max(nums[0], nums[1]); // 前 2 个房子抢最多的 1 个
        System.out.println("dp[2] = " + dp[2]);
        for (int i = 3; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]); // 状态转移方程：抢当前房子或不抢当前房子
            System.out.println("dp[" + i + "] = " + dp[i]);
        }
        return dp[nums.length];
    }

    public static void main(String[] args) {
        Rob rob = new Rob();
        int[] nums = {100, 7, 9, 20, 1};
        System.out.println(rob.rob(nums));
    }
}
