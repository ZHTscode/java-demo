package solution;

import java.util.HashMap;
import java.util.Map;

// 和为K的连续子数组个数

public class SubarraySum {
    /* 560. 和为 K 的子数组 */
    // 暴力解法
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j >= 0; j--) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }
    /* 前缀和 + 哈希表解法
       遍历数组，计算当前前缀和 prefixSum
       检查 prefixSum - k 是否出现过
       如果出现过，说明存在子数组和为 k
       出现的次数 = 以当前位置结尾的和为 k 的子数组个数
       把当前 prefixSum 存入哈希表 */
    public int subarraySum2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // 初始化，前缀和为0出现1次（处理从0开始的子数组）
        int prefixSum = 0; // 前缀和（0到当前下标元素的和，键）
        int count = 0; // 子数组个数
        for (int num : nums) {
            prefixSum += num;
            if (map.containsKey(prefixSum - k)) {
                count += map.get(prefixSum - k); // 累加出现的次数
            }
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        SubarraySum subarraySum = new SubarraySum();
        int[] nums = {1, 2, 3};
        int k = 3;
        int result = subarraySum.subarraySum(nums, k);
        System.out.println(result);
    }
}
