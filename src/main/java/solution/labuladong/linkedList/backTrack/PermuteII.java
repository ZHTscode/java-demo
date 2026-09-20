package solution.labuladong.linkedList.backTrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PermuteII {
    /* 46. 全排列 */
    List<List<Integer>> res;
    public List<List<Integer>> permute(int[] nums) {
        res = new LinkedList<>();
        backtrack(nums, 0);
        return res;
    }
    private void backtrack(int[] nums, int start) {
        if (start == nums.length) {
            // 找到一个全排列，Java 需要转化成 List 类型
            List<Integer> list = new LinkedList<>();
            for (int num : nums) {
                list.add(num);
            }
            res.add(list);
            return;
        }
        for (int i = start; i < nums.length; i++) {
            // 做选择
            swap(nums, start, i);
            // 递归调用，传入 start + 1
            backtrack(nums, start + 1);
            // 撤销选择
            swap(nums, start, i);
        }
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        PermuteII solution = new PermuteII();
        int[] nums = {1, 2, 3};
        List<List<Integer>> res = solution.permute(nums);
        System.out.println(res);
    }
}
