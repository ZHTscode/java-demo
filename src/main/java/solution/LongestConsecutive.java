package solution;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestConsecutive {
    /* 128. 最长连续序列 */
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>(); // 数组存入哈希集合
        for (int num : nums) {
            numSet.add(num);
        }
        int maxLength = 0;
        // 遍历每个数，找以当前数为起点的连续序列
        for (int num : numSet) {
            // 当num-1不存在时才是连续序列的起点，否则跳过（避免重复计算）
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentLength = 1; // 当前连续序列长度，初始自身为1
                // 找连续的下一个数：currentNum+1、currentNum+2...
                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentLength++;
                }
                maxLength = Math.max(maxLength, currentLength);
            }
        }
        return maxLength;
    }

    public int longestConsecutive2(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        Arrays.sort(nums);
        int count = 1; // 序列最后元素下标 + 1
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) {
                continue;
            }
            else if (nums[i] == (nums[i - 1] + 1))
                count++;
            else {
                max = Math.max(count, max);
                count = 1;
            }
        }
        max = Math.max(count, max); // 若数组最后元素在最长序列中，需要更新
        return max;
    }

    public static void main(String[] args) {
        LongestConsecutive solution = new LongestConsecutive();
        int[] nums = {100, 4, 200, 1, 3, 2};
        int result = solution.longestConsecutive(nums);
        System.out.println(result); // 输出 4，因为最长连续序列是 [1, 2, 3, 4]
    }
}