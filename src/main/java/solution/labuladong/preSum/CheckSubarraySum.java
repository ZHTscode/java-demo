package solution.labuladong.preSum;

import java.util.HashMap;
import java.util.Map;

public class CheckSubarraySum {
    /* 523. 连续的子数组和 */
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] preSum = new int[n+1];
        for(int i=1; i<=n; i++){
            preSum[i] = preSum[i-1] + nums[i-1];
        }
        Map<Integer, Integer> valToIndex = new HashMap<>();
        for(int i=0; i<=n; i++){
            int val = preSum[i] % k; // 哈希表中记录余数
            if(!valToIndex.containsKey(val)){
                valToIndex.put(val, i);
            } else{
                if(i - valToIndex.get(val) >= 2)
                    return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CheckSubarraySum solution = new CheckSubarraySum();
        int[] nums = {1,2,4};
        int k = 2;
        System.out.println(solution.checkSubarraySum(nums, k));
    }
}
