package solution.labuladong.linkedList.dp;

public class MaxSubArray {
    /* 53. 最大子数组和 */
    // 滑动窗口思路
    // 在窗口元素和为正时才不断扩大窗口，其实就是穷举所有正数开头的子数组，寻找和最大的那个
    public int maxSubArray(int[] nums){
        int left = 0, right = 0;
        int sum = 0, maxSum = Integer.MIN_VALUE;
        while(right < nums.length){
            sum += nums[right];
            right++;
            maxSum = Math.max(maxSum, sum);
            while(sum < 0){ // 窗口和为负，左边界右移
                sum -= nums[left];
                left++;
            }
        }
        return maxSum;
    }
    // 动态规划思路
    public int maxSubArray2(int[] nums){
        int n = nums.length;
        if(n == 0) return 0;
        int[] dp = new int[n];
        dp[0] = nums[0];
        for(int i=1; i<n; i++){
            dp[i] = Math.max(nums[i], dp[i-1] + nums[i]);
        }
        int res = Integer.MIN_VALUE;
        for(int i=0; i<n; i++){
            res = Math.max(res, dp[i]);
        }
        return res;
    }
    // 空间复杂度优化
    public int maxSubArray3(int[] nums){
        int n = nums.length;
        if(n == 0) return 0;
        int dp_i = nums[0];
        int res = dp_i;
        for(int i=1; i<n; i++){
            dp_i = Math.max(nums[i], dp_i + nums[i]);
        }
        res = Math.max(res, dp_i);
        return res;
    }

    // 前缀和思路
    public int maxSubArray4(int[] nums){
        int n = nums.length;
        int[] preSum = new int[n+1];
        preSum[0] = 0;
        // 构造前缀和数组
        for(int i=1; i<=n; i++){
            preSum[i] = preSum[i-1] + nums[i-1];
        }
        int res = Integer.MIN_VALUE;
        int minVal = Integer.MAX_VALUE;
        for(int i=0; i<n; i++){
            // 维护 minVal 是 preSum[0..i] 的最小值
            minVal = Math.min(minVal, preSum[i]);
            // preSum[i] - minVal 就是 preSum[0..i] 中以 i 结尾的子数组的最大子数组和
            res = Math.max(res, preSum[i] - minVal);
        }
        return res;
    }

    public static void main(String[] args) {
        MaxSubArray maxSubArray = new MaxSubArray();
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray.maxSubArray(nums));
    }
}