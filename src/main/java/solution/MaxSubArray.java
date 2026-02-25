package solution;

public class MaxSubArray {
    /*  53. 最大子数组和
        解法一：动态规划 */
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length]; // dp[i] 表示以 nums[i] 结尾的最大子数组和
        dp[0] = nums[0]; // 初始化：单个元素时最大和最小都是它自己
        int max = dp[0]; // 最大子数组和
        for (int i = 1; i < nums.length; i++) {
            // 状态转移方程：要么接上前面的，要么自己重新开始
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }
    /* 解法二：Kadane 算法（贪心策略）最优
       核心：如果前面的累加和是负担（负数）就果断抛弃，从当前元素重新开始
       实质：DP 的空间优化版本
       dp[i] 只依赖于 dp[i-1] → 只需要一个变量 sum 来代表 dp[i-1]
       if (sum > 0) sum += num 等价于 Math.max(sum + num, num) 当 sum > 0 时
       else sum = num 等价于 Math.max(sum + num, num) 当 sum <= 0 时 */
    public int maxSubArray2(int[] nums) {
        int max = nums[0];
        int sum = 0;
        for(int num: nums) {
            if(sum > 0) sum += num;
            else        sum = num;
            System.out.println(sum);
            max = Math.max(max, sum);
        }
        return max;
    }

    public static void main(String[] args) {
        MaxSubArray ms = new MaxSubArray();
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int res = ms.maxSubArray(nums);
        System.out.println(res);
    }
}
