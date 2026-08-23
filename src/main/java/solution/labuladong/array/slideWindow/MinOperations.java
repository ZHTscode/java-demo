package solution.labuladong.array.slideWindow;

public class MinOperations {
    /* 1658. 将 x 减到 0 的最小操作数 */
    // 等价于寻找 nums 中元素和为 sum(nums) - x 的最长子数组
    public int minOperations(int[] nums, int x) {
        int sum = 0;
        for(int num : nums) sum += num;
        int target = sum - x;
        int windowSum = 0, maxLen = -1;
        int left = 0, right = 0;

        while(right < nums.length){
            windowSum += nums[right];
            right++;
            while(windowSum > target && left < right){
                windowSum -= nums[left];
                left++;
            }
            if(windowSum == target){
                maxLen = Math.max(maxLen, right - left);
            }
        }
        return maxLen == -1? -1:nums.length-maxLen;
    }

    public static void main(String[] args) {
        MinOperations minOperations = new MinOperations();
        int[] nums = {1,1,4,2,3};
        int x = 5;
        System.out.println(minOperations.minOperations(nums, x));
    }
}
