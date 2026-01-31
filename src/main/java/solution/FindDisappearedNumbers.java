package solution;

import java.util.ArrayList;
import java.util.List;

public class FindDisappearedNumbers {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        // 第一步：遍历数组，标记出现过的数字（对应下标置负）
        for (int num : nums) {
            int targetIndex = Math.abs(num) - 1; // 计算目标下标，取绝对值避免负数值影响
            if (nums[targetIndex] > 0) { // 仅当为正数时置负，避免重复标记
                nums[targetIndex] = -nums[targetIndex];
            }
        }
        // 第二步：二次遍历，收集未被标记的数字（下标i为正 → i+1消失）
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                res.add(i + 1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        FindDisappearedNumbers f = new FindDisappearedNumbers();
        int[] nums = {4,3,2,7,8,2,3,1};
        System.out.println(f.findDisappearedNumbers(nums));
    }
}
