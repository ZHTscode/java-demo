package solution.labuladong.array.twoPtrArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    /* 15. 三数之和 */
    public List<List<Integer>> threeSum(int[] nums) {
        return threeSumTarget(nums, 0);
    }

    public List<List<Integer>> threeSumTarget(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int i=0; i<nums.length; i++){
            List<List<Integer>> twoSumRes = twoSumTarget(nums, i+1, target - nums[i]);
            for(List<Integer> pair : twoSumRes){
                pair.add(nums[i]);
                res.add(pair);
            }
            while(i < nums.length-1 && nums[i] == nums[i+1]) i++;
        }
        return res;
    }

    public List<List<Integer>> twoSumTarget(int[] nums, int start, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int lp = start, rp = nums.length -1;
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
        ThreeSum ts = new ThreeSum();
        int[] nums = {-1, 0, 1, 2, -1, -4, 4};
        List<List<Integer>> res = ts.threeSum(nums);
        System.out.println(res);
    }
}
