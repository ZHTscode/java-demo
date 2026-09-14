package solution.labuladong.linkedList.backTrack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PermuteUnique {
    /* 47. 全排列 II$$ */
    List<List<Integer>> res;
    List<Integer> track;
    boolean[] used;
    List<List<Integer>> permuteUnique(int[] nums) {
        res = new LinkedList<>();
        track = new LinkedList<>();
        used = new boolean[nums.length];
        Arrays.sort(nums); // 先排序，让重复的元素相邻
        backtrack(nums);
        return res;
    }

    private void backtrack(int[] nums) {
        // 结束条件：nums 中的元素全都在 track 中出现
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 跳过不合法的选择
            if (used[i]) continue;
            // 固定相同元素形成的序列顺序
            // 若前一个元素没被用过，后一个元素不能使用
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            // 做选择
            track.add(nums[i]);
            used[i] = true;
            // 进入下一层决策树
            backtrack(nums);
            // 取消选择
            track.remove(track.size() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        PermuteUnique p = new PermuteUnique();
        int[] nums = {1, 2, 3, 2, 1};
        System.out.println(p.permuteUnique(nums));
    }
}
