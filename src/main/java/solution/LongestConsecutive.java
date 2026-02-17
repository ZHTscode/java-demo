package solution;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutive {
    public int longestConsecutive(int[] nums) {
        // 1. 把数组存入哈希集合，去重+O(1)查找
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        int maxLength = 0; // 记录最长连续序列长度
        // 2. 遍历每个数，找以当前数为起点的连续序列
        for (int num : numSet) {
            // 核心优化：只有当num-1不存在时，才是连续序列的「起点」，否则跳过（避免重复计算）
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentLength = 1; // 当前连续序列长度，初始为1（自身）
                // 3. 找连续的下一个数：currentNum+1、currentNum+2...
                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentLength++;
                }
                // 4. 更新最长长度
                maxLength = Math.max(maxLength, currentLength);
            }
        }
        return maxLength;
    }
    public static void main(String[] args) {
        LongestConsecutive solution = new LongestConsecutive();
        int[] nums = {100, 4, 200, 1, 3, 2};
        int result = solution.longestConsecutive(nums);
        System.out.println(result); // 输出 4，因为最长连续序列是 [1, 2, 3, 4]
    }
}