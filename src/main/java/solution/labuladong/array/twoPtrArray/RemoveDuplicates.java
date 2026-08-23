package solution.labuladong.array.twoPtrArray;

import java.util.Arrays;

public class RemoveDuplicates {
    /* 26. 删除排序数组中的重复项 */
    public int removeDuplicates(int[] nums) {
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != nums[slow]) {
                slow++;
                // 维护 nums[0..slow] 无重复
                nums[slow] = nums[fast];
                // System.out.println(Arrays.toString(nums));
            }
            fast++;
        }
        return slow + 1;
    }

    public static void main(String[] args) {
        RemoveDuplicates solution = new RemoveDuplicates();
        int[] nums = {1, 1, 2, 2, 3, 4, 5, 5, 6};
        int len = solution.removeDuplicates(nums);
        System.out.println(len);
        System.out.println(Arrays.toString(nums));
    }
}
