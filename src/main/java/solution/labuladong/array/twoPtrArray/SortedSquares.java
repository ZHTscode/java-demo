package solution.labuladong.array.twoPtrArray;

import java.util.Arrays;

public class SortedSquares {
    /* 977. 有序数组的平方 */
    public int[] sortedSquares(int[] nums) {
        int lp = 0, rp = nums.length - 1;
        int[] res = new int[nums.length];
        int p = nums.length - 1;
        while(lp <= rp){
            if(Math.abs(nums[lp]) > Math.abs(nums[rp])){
                res[p] = nums[lp] * nums[lp];
                lp++;
            } else{
                res[p] = nums[rp] * nums[rp];
                rp--;
            }
            p--;
        }
        return res;
    }

    public static void main(String[] args) {
        SortedSquares s = new SortedSquares();
        int[] nums = {-4,-1,0,3,10};
        int[] res = s.sortedSquares(nums);
        System.out.println(Arrays.toString(res));
    }


}
