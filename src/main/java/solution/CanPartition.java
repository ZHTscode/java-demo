package solution;

import java.util.Arrays;

public class CanPartition {
    /* 416. 分割等和子集
       解法一：动态规划 */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) return false; // 如果 sum 为奇数，则无法平分
        int target = sum / 2; // 目标和
        boolean[] dp = new boolean[target + 1]; // dp[i] : nums 子集和为 i (0 - target) 的个数
        dp[0] = true; // 只有子集为空集一种情况
        int target1 = 0;
        for (int num : nums) { // 遍历 nums 数组元素（均非负）
            target1 = Math.min(target1 + num, target); // target1 为当前子集总和
            for (int j = target1; j >= num; j--) { // 从后向前遍历，避免重复使用 nums 中元素
                // 状态转移方程：两种情况成立一个即为 true
                dp[j] = dp[j] // 不使用 nums 中当前 num 元素
                        || dp[j - num]; // 使用 nums 中当前 num 元素
            }
            if (dp[target]) return true; // 提前返回
        }
        return false;
    }
    /* 解法二：另类搜索解法 */
    public boolean canPartition2(int[] nums) {
        if(nums.length == 1) return false;
        int sum = 0;
        for(int num: nums) {
            sum += num;
        }
        if (sum % 2 != 0) return false;
        Arrays.sort(nums); // 排序
        return search(nums, sum / 2, nums.length - 1);
    }
    public boolean search(int[] nums, int target, int fromIndex) {
        for (int i = fromIndex; i >= 0; i--) {
            if (nums[i] > target) continue;
            if (nums[i] == target) return true;
            if (i != fromIndex && nums[i] == nums[i+1])
                // 从后往前搜索，当前元素与之前元素相同：之前元素已搜索过，直接返回 false
                return false;
            if (search(nums, target-nums[i], i-1)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        CanPartition canPartition = new CanPartition();
        int[] nums = {3, 1, 1, 2, 2, 1};
        System.out.println(canPartition.canPartition2(nums));
    }
}
