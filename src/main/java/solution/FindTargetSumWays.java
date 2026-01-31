package solution;

public class FindTargetSumWays {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        } // 得到 nums 数组总和（元素非负）
//        System.out.println("sum = " + sum);
        if (sum < S || (sum + S) % 2 == 1 || sum + S < 0) {
            /* sum(P) - sum(N) = S
               sum(P) + sum(N) = sum
               2 * sum(P) = sum + S*/
            return 0; // 如果 sum 小于 S 或者 (sum + S) 为奇数 或 (sum + S) 为负数，则不存在满足条件的子集
        }
        // 转化为 0-1 背包问题，求解 nums 中子集，使得子集和为 (sum + S) / 2
        // 找到 nums 中子集，使和为 (sum + S) / 2
        return subsetSum(nums, (sum + S) / 2);
    }
    private int subsetSum(int[] nums, int sum) {
        int[] dp = new int[sum + 1]; // dp 数组，dp[i] 表示 nums 子集和为 i 的「个数」
        dp[0] = 1; // 初始化 dp 数组，只有子集为空集一种情况
//        System.out.println("sum = " + sum);
        for (int num : nums) { // 遍历 nums 数组元素（均非负）
            // 考虑子集中加入元素 num 的情况
            for (int j = sum; j >= num; j--) {
                dp[j] += dp[j - num]; // 状态转移方程
                // 和为 j - num 的子集个数，加到和为 j 的子集个数上，因为 num 被加入到子集中
            }
        }
        return dp[sum]; // 返回 dp 数组最后一个元素
    }

    public static void main(String[] args) {
        FindTargetSumWays f = new FindTargetSumWays();
        int[] nums = {1, 1, 1, 1};
        System.out.println(f.findTargetSumWays(nums, -1000));
    }
}