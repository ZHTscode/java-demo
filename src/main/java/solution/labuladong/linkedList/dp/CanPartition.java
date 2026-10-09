package solution.labuladong.linkedList.dp;

public class CanPartition {
    /* 416. 分割等和子集 */
    public boolean canPartition(int[] nums){
        int sum = 0;
        for(int num : nums) sum += num;
        if(sum % 2 != 0) return false;
        int target = sum / 2;
        int n = nums.length;
        // dp[i][j] 表示 nums[0..i] 是否可以凑成 j
        boolean[][] dp = new boolean[n+1][target+1];
        for(int i=0; i<=n; i++){
            dp[i][0] = true;
        }
        for(int i=1; i<=n; i++){
            for(int j=1; j<=target; j++){
                if(j-nums[i-1] < 0){
                    // 背包装不下 nums[i-1]
                    dp[i][j] = dp[i-1][j];
                } else {
                    // 装入或不装入
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
                }
            }
        }
        return dp[n][target];
    }
    // 空间复杂度优化
    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        // 和为奇数时，不可能划分成两个和相等的集合
        if (sum % 2 != 0) return false;
        int n = nums.length;
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true; // base case
        for (int i = 0; i < n; i++) {
            for (int j = target; j >= 0; j--) {
                if (j - nums[i] >= 0) {
                    dp[j] = dp[j] || dp[j - nums[i]];
                }
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        CanPartition canPartition = new CanPartition();
        int[] nums = {1, 5, 11, 5};
        System.out.println(canPartition.canPartition2(nums));
    }
}
