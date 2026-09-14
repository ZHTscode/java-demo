package solution.labuladong.linkedList.backTrack;

import java.util.LinkedList;
import java.util.List;

public class PermuteRepeat {
    /* 原创 排列（元素无重可复选） */
    List<List<Integer>> res;
    List<Integer> track;
    public List<List<Integer>> permuteRepeat(int[] nums) {
        res = new LinkedList<>();
        track = new LinkedList<>();
        backtrack(nums);
        return res;
    }

    void backtrack(int[] nums) {
        // base case，到达叶子节点
        if (track.size() == nums.length) {
            // 收集叶子节点上的值
            res.add(new LinkedList<>(track));
            return;
        }
        for (int num : nums) {
            // 做选择
            track.add(num);
            // 进入下一层回溯树
            backtrack(nums);
            // 取消选择
            track.remove(track.size() - 1);
        }
    }

    public static void main(String[] args) {
        PermuteRepeat c = new PermuteRepeat();
        int[] nums = {1, 2, 3};
        System.out.println(c.permuteRepeat(nums));
    }
}
