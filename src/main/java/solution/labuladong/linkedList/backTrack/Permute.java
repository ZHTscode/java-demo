package solution.labuladong.linkedList.backTrack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Permute {
    /* 46. 全排列 */
    List<List<Integer>> res;
    List<Integer> track;
    boolean[] used;
    List<List<Integer>> permute(int[] nums) {
        res = new LinkedList<>();
        track = new LinkedList<>();
        used = new boolean[nums.length];
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
        Permute solution = new Permute();
        int[] nums = {1, 2, 3};
        List<List<Integer>> res = solution.permute(nums);
        System.out.println(res);
    }
}
