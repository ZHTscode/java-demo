package solution;

import java.util.Arrays;

public class Rob {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length]; // dp数组，dp[i]表示前i+1个房子能抢到的最大金额总和
        dp[0] = nums[0]; // 前1个房子只能抢1个
        System.out.println("dp[0] = " + dp[0]);
        dp[1] = Math.max(nums[0], nums[1]); // 前2个房子抢最多的一个
        System.out.println("dp[1] = " + dp[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]); // 状态转移方程
            System.out.println("dp[" + i + "] = " + dp[i]);
        }
        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        Rob rob = new Rob();
        int[] nums = {100, 7, 9, 20, 1};
        System.out.println(rob.rob(nums));
    }
}
