package solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    /* 15. 三数之和 */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // 1. 排序，为去重和双指针做准备
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {
            // 跳过重复的基准数
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int target = -nums[i];
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    // 找到符合条件的三元组
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 跳过左指针重复元素
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // 跳过右指针重复元素
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    // 移动指针寻找下一组
                    left++;
                    right--;
                } else if (sum < target) {
                    // 和太小，左指针右移增大数值
                    left++;
                } else {
                    // 和太大，右指针左移减小数值
                    right--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ThreeSum solution = new ThreeSum();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> res = solution.threeSum(nums);
        System.out.println(res); // 输出: [[-1, -1, 2], [-1, 0, 1]]
    }
}