package solution.labuladong.array.preSum;

import java.util.HashMap;
import java.util.Map;

public class SubarraysDivByK {
    /* 974. 和可被 K 整除的子数组 */
    public int subarraysDivByK(int[] nums, int k) {
        int n = nums.length;
        int[] preSum = new int[n+1];
        Map<Integer, Integer> valToCount = new HashMap<>();
        valToCount.put(0, 1); // 除k余0的个数为1
        int res = 0;

        for(int i=1; i<=n; i++){
            preSum[i] = preSum[i-1] + nums[i-1];
            int remainder = preSum[i] % k;
            if(remainder < 0) remainder += k; // -2%3=-2, 1%3=1
            if(!valToCount.containsKey(remainder)){
                valToCount.put(remainder, 1);
            } else {
                int count = valToCount.get(remainder);
                res += count;
                valToCount.put(remainder, count + 1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        SubarraysDivByK solution = new SubarraysDivByK();
        int[] nums = {4,5,0,-2,-3,1};
        int k = 5;
        System.out.println(solution.subarraysDivByK(nums, k));
    }
}
