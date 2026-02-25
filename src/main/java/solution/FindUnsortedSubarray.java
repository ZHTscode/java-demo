package solution;

public class FindUnsortedSubarray {
    /* 581. 最短无序连续子数组
       核心：O(n) 排序 */
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int left = 0, right = -1; // 有意使 right - left + 1 == 0
        for (int i = 0; i < n; ++i) {
            /* max(nums[0, ..., i-1]) <= nums[i]：i 位于右端递增序列
               不满足该条件：i 左边需要重排序
               从左到右不断更新，直到找到需要排序的最右端 */
            if (max > nums[i]) right = i; // 当前数比其左侧所有数的最大值小，反常
            else max = nums[i]; // 更新最大值
            /* min(nums[i-1, ..., n-1]) >= nums[i]：i 位于左端递增序列
               不满足该条件：i 右边需要重排序
               从右到左不断更新，直到找到需要排序的最左端 */
            if (min < nums[n - i - 1]) left = n - i - 1; // 当前数比其右侧所有数的最小值大，反常
            else min = nums[n - i - 1]; // 更新最小值
        }
        System.out.println(left + " " + right);
        return right - left + 1;
    }

    public static void main(String[] args) {
        FindUnsortedSubarray f = new FindUnsortedSubarray();
        int[] nums = {2, 6, 4, 8, 10, 9, 15};
        System.out.println(f.findUnsortedSubarray(nums));
    }
}
