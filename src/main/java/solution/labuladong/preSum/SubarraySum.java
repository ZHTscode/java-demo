package solution.labuladong.preSum;

import java.util.HashMap;
import java.util.Map;

public class SubarraySum {
    /* 560. 和为K的子数组 */
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] preSum = new int[n+1];
        Map<Integer, Integer> valToCount = new HashMap<>();
        valToCount.put(0, 1); // 前缀和为0的个数为1
        int res = 0;
        for(int i=1; i<=n; i++){
            preSum[i] = preSum[i-1] + nums[i-1];
            //System.out.println(preSum[i]);

            // 放在填入map之前
            int need = preSum[i] - k;
            if(valToCount.containsKey(need)){
                res += valToCount.get(need);
            }

            if(!valToCount.containsKey(preSum[i])){
                valToCount.put(preSum[i], 1);
            } else{
                valToCount.put(preSum[i], valToCount.get(preSum[i]) + 1);
            }
            //System.out.println(valToCount);
        }
        return res;
    }

    public static void main(String[] args) {
        SubarraySum solution = new SubarraySum();
        int[] nums = {1,-1,1};
        int k = 1;
        System.out.println(solution.subarraySum(nums, k));
    }
}
