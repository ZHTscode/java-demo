package solution.labuladong.linkedList.backTrack;

import java.util.LinkedList;
import java.util.List;

public class Subsets {
    /* 78. 子集 */
    List<List<Integer>> res;
    List<Integer> track;
    public List<List<Integer>> subsets(int[] nums) {
        res = new LinkedList<>();
        track = new LinkedList<>();
        backtrack(nums, 0);
        return res;
    }
    private void backtrack(int[] nums, int start){
        res.add(new LinkedList<>(track)); // 不能直接 add 共享的 track，必须复制一份
        System.out.println("track: " + track);
        for(int i=start; i<nums.length; i++){
            track.add(nums[i]);
            backtrack(nums, i+1);
            track.remove(track.size() - 1);
        }
    }

    public static void main(String[] args) {
        Subsets s = new Subsets();
        int[] nums = {1,2,3};
        System.out.println(s.subsets(nums));
    }
}
