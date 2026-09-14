package solution.labuladong.linkedList.backTrack;

import java.util.LinkedList;
import java.util.List;

public class CombinationSum {
    /* 39. 组合总和 */
    List<List<Integer>> res;
    List<Integer> track;
    int trackSum; // 路径和
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        res = new LinkedList<>();
        track = new LinkedList<>();
        trackSum = 0;
        if (candidates.length == 0) return res;
        backtrack(candidates, 0, target);
        return res;
    }
    void backtrack(int[] nums, int start, int target) {
        // base case，找到目标和，记录结果
        if (trackSum == target) {
            res.add(new LinkedList<>(track));
            return;
        }
        // base case，超过目标和，停止向下遍历
        if (trackSum > target) return;
        // 回溯算法标准框架
        for (int i = start; i < nums.length; i++) {
            // 选择 nums[i]
            trackSum += nums[i];
            track.add(nums[i]);
            // 递归遍历下一层回溯树
            backtrack(nums, i, target); // i不变，可以继续选nums[i]
            // 撤销选择
            trackSum -= nums[i];
            track.remove(track.size() - 1);
        }
    }

    public static void main(String[] args) {
        CombinationSum c = new CombinationSum();
        int[] nums = {2, 3, 6, 7};
        int target = 7;
        System.out.println(c.combinationSum(nums, target));
    }
}
