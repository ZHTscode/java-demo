package solution.labuladong.preSum;

public class PivotIndex {
    public int pivotIndex(int[] nums) {
        /* 724. 寻找数组的中心索引 */
        int n = nums.length;
        int[] preSum = new int[n+1];
        // preSum[i]：前i个元素的和（包含i）
        for(int i=1; i<=n; i++){
            preSum[i] = preSum[i-1] + nums[i-1];
        }
        for(int i=1; i<=n; i++){
            int leftSum = preSum[i-1];
            int rightSum = preSum[n] - preSum[i];
            if(leftSum == rightSum) return i-1;
        }
        return -1;
    }

    public static void main(String[] args){
        PivotIndex p = new PivotIndex();
        int[] nums = {2,1,-1};
        System.out.println(p.pivotIndex(nums));
    }

}
