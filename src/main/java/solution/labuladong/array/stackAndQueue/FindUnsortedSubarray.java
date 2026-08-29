package solution.labuladong.array.stackAndQueue;

public class FindUnsortedSubarray {
    /* 581. 最短无序连续子数组$$ */
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int left = 0, right = -1;
        for (int i = 0; i < n; ++i) {
            // 从左到右，找到第一个降序的元素
            if (nums[i] < max) {
                right = i;
            } else {
                max = nums[i];
            }
            // 从右到左，找到第一个升序的元素
            if (nums[n - i - 1] > min) {
                left = n - i - 1;
            } else {
                min = nums[n - i - 1];
            }
        }
        return right - left + 1;
    }

    public static void main(String[] args) {
        FindUnsortedSubarray f = new FindUnsortedSubarray();
        int[] nums = {2, 6, 4, 8, 10, 9, 15};
        int res = f.findUnsortedSubarray(nums);
        System.out.println(res);
    }
}
