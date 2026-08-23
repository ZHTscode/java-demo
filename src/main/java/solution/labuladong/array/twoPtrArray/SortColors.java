package solution.labuladong.array.twoPtrArray;

import java.util.Arrays;

public class SortColors {
    /* 75. 颜色分类 */
    public void sortColors(int[] nums) {
        int zero = 0, two = nums.length - 1; // zero左侧为0，two右侧为2
        for (int i = 0; i <= two; i++) {
            while (nums[i] == 2 && i < two){
                swap(nums, i, two);
                two--;
            }
            while (nums[i] == 0 && zero < i){
                swap(nums, i, zero);
                zero++;
            }
        }
    }
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        SortColors s = new SortColors();
        int[] nums = {2,0,2,1,1,0};
        s.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
