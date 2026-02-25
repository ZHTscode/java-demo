package solution;

import java.util.Arrays;

public class SortColors {
    /* 75. 颜色分类
       排序三个元素：最小的在前，最大的在后
       核心：双指针 */
    public void sortColors(int[] nums) {
        int zero = 0, two = nums.length - 1;
        for (int i = 0; i <= two; i++) {
            while (nums[i] == 2 && i < two)
                swap(nums, i, two--);
            while (nums[i] == 0 && zero < i)
                swap(nums, i, zero++);
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
