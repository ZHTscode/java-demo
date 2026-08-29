package solution.labuladong.array.preSum;

import java.util.HashMap;
import java.util.Map;

public class FindMaxLength {
    /* 525. 连续数组 */
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n+1]; // preSum[i]：前i个元素的和（包含第i个元素）
        for(int i=1; i<=n; i++){
            preSum[i] = preSum[i-1] + (nums[i-1] == 0 ? -1 : 1);
        }
        Map<Integer, Integer> valToIndex = new HashMap<>();
        int res = 0;
        for(int i=0; i<=n; i++){
            if(!valToIndex.containsKey(preSum[i])){
                valToIndex.put(preSum[i], i);
            } else{ // 这个前缀和之前出现过，说明从valToIndex.get(preSum[i])到i的子数组和为0
                res = Math.max(res, i - valToIndex.get(preSum[i]));
                // 不更新valToIndex，只留最左边的索引
            }
        }
        return res;
    }

    public static void main(String[] args) {
        FindMaxLength solution = new FindMaxLength();
        int[] nums = {0,1,0};
        System.out.println(solution.findMaxLength(nums));
    }
}
