package solution.labuladong.array.twoPtrArray;

import java.util.Arrays;

public class RemoveDuplicatesII {
    /* 80. 删除排序数组中的重复项 II */
    public int removeDuplicates(int[] nums) {
        if(nums.length <= 2) return nums.length;
        int slow = 0, fast = 1; // 保证slow和fast不重叠
        int count = 1; // 当前nums[slow]的计数
        while(fast < nums.length){
           if(nums[fast] == nums[slow] && count < 2){
               slow++;
               nums[slow] = nums[fast];
               fast++;
               count++;
           } else if(nums[fast] == nums[slow] && count >= 2){
               fast++;
               count++;
           } else {
               slow++;
               nums[slow] = nums[fast];
               count = 1;
               fast++;
           }
        }
        return slow + 1;
    }

    public int removeDuplicates2(int[] nums) {
        if(nums.length <= 2) return nums.length;
        int slow = 2;
        for(int fast = 2; fast < nums.length; fast++){
            // 看有效区间[0, slow-1]，如果 fast 和 slow-2 相等，说明该数字已经存够 2 个，跳过
            if(nums[fast] != nums[slow-2]){
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }

    public static void main(String[] args) {
        RemoveDuplicatesII removeDuplicatesII = new RemoveDuplicatesII();
        int[] nums = {0,0,1,1,1,1,2,3,3};
        int i = removeDuplicatesII.removeDuplicates(nums);
        System.out.println(i);
        System.out.println(Arrays.toString(nums));
    }
}
