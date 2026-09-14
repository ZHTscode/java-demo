package solution.labuladong.linkedList.backTrack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SubsetsWithDup {
    /* 90. 子集 II */
    List<List<Integer>> res;
    List<Integer> track;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        res = new LinkedList<>();
        track = new LinkedList<>();
        Arrays.sort(nums); // 先排序，让重复的元素相邻
        backtrack(nums, 0);
        return res;
    }
    private void backtrack(int[] nums, int start) {
        res.add(new LinkedList<>(track));
        for(int i=start; i<nums.length; i++){
            if(i > start && nums[i] == nums[i-1]) continue; // 跳过重复元素
            track.add(nums[i]);
            backtrack(nums, i+1);
            track.remove(track.size() - 1);
        }
    }

    public static void main(String[] args) {
        SubsetsWithDup s = new SubsetsWithDup();
        int[] nums = {1,2,3,2,2,1};
        System.out.println(s.subsetsWithDup(nums));
    }
}
