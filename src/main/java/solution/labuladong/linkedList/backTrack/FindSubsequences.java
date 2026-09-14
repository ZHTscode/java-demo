package solution.labuladong.linkedList.backTrack;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class FindSubsequences {
    /* 491. 非递减子序列 */
    List<List<Integer>> res;
    List<Integer> track;
    public List<List<Integer>> findSubsequences(int[] nums) {
        res = new LinkedList<>();
        track = new LinkedList<>();
        if (nums.length == 0) return res;
        backtrack(nums, 0);
        return res;
    }
    void backtrack(int[] nums, int start) {
        if (track.size() >= 2) res.add(new LinkedList<>(track)); // 找到合法答案
        Set<Integer> used = new HashSet<>(); // 非全局变量，只负责当前层for循环去重（以start开头的）
        for (int i = start; i < nums.length; i++) {
            // 保证集合中元素都是递增顺序
            if (!track.isEmpty() && track.get(track.size() - 1) > nums[i]) {
                continue;
            }
            if (used.contains(nums[i])) continue; // 不重复使用相同的元素
            // 选择 nums[i]
            used.add(nums[i]);
            track.add(nums[i]);
            // 递归遍历下一层回溯树
            backtrack(nums, i + 1);
            // 撤销选择 nums[i]
            track.remove(track.size() - 1);
        }
    }

    public static void main(String[] args){
        FindSubsequences s = new FindSubsequences();
        int[] nums = {4, 6, 7, 7};
        List<List<Integer>> res = s.findSubsequences(nums);
        for (List<Integer> r : res) {
            System.out.println(r);
        }
    }
}
