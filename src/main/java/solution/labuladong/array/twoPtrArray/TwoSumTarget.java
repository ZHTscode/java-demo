package solution.labuladong.array.twoPtrArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoSumTarget {
    /* 两数之和返回多对并去重 */
    public List<List<Integer>> twoSumTarget(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int lp = 0, rp = nums.length -1;
        while(lp < rp){
            int lv = nums[lp], rv = nums[rp];
            int sum = lv + rv;
            if(sum < target){
                while(lp < rp && nums[lp] == lv) lp++;
            } else if(sum > target){
                while(lp < rp && nums[rp] == rv) rp--;
            } else{
                while(lp < rp && nums[lp] == lv) lp++;
                while(lp < rp && nums[rp] == rv) rp--;
                res.add(new ArrayList<>(Arrays.asList(lv, rv))) ;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TwoSumTarget solution = new TwoSumTarget();
        int[] nums = {1,1,1,2,2,3,3};
        List<List<Integer>> res = solution.twoSumTarget(nums, 4);
        System.out.println(res);
    }
}
