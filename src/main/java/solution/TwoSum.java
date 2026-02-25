package solution;

import utils.ArrayUtil;

import java.util.HashMap;

public class TwoSum {
    /* 1. 两数之和 */
    public int[] twoSum(int[] nums, int target) {
        // 哈希表：key = 数组元素值，value = 该元素对应的数组下标
        HashMap<Integer, Integer> map = new HashMap<>();
        // 一次遍历数组，边存边查
        for (int i = 0; i < nums.length; i++) {
            // 计算当前元素的「补数」：target - 当前值
            int complement = target - nums[i];
            // 核心判断：如果补数已经在哈希表里，说明找到了答案
            if (map.containsKey(complement)) {
                // 补数的下标 + 当前下标，就是结果
                return new int[]{map.get(complement), i};
            }
            // 补数不在哈希表，把当前元素和下标存入哈希表
            map.put(nums[i], i);
        }
        // 题目保证必有解，这里只是兜底返回
        return new int[0];
    }
    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        int[] result = twoSum.twoSum(new int[]{-1, 1, 2, 6, 7, 9}, 8);
        ArrayUtil.printIntArray(result);

    }
}
