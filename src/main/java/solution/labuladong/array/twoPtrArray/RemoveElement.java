package solution.labuladong.array.twoPtrArray;

import java.util.Arrays;

public class RemoveElement {
    /* 27. 移除元素 */
    public int removeElement(int[] nums, int val) {
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    public static void main(String[] args) {
        RemoveElement removeElement = new RemoveElement();
        int[] nums = {3,2,2,3};
        int val = 3;
        int res = removeElement.removeElement(nums, val);
        System.out.println(res);
        System.out.println(Arrays.toString(nums));
    }
}
