package solution.labuladong.array.preSum;

import java.util.Arrays;

public class NumArray {
    /* 303. 区域和检索 - 数组不可变 */
    private int[] preSum;

    public NumArray(int[] nums){
        preSum = new int[nums.length+1];
        for(int i=1; i<=nums.length; i++){
            preSum[i] = preSum[i-1] + nums[i-1];
        }
    }
    public int sumRange(int left, int right){
        return preSum[right+1] - preSum[left];
    }

    public static void main(String[] args){
        NumArray numarray = new NumArray(new int[]{1,2,5,5,7,8});
        System.out.println(numarray.sumRange(1,4));
        System.out.println(Arrays.toString(numarray.preSum));
    }
}