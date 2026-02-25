package solution;

import java.util.Arrays;

public class LengthOfLIS {
    /* 300. 最长递增子序列 */
    // 解法一：动态规划
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = 1; // 以 nums[0] 结尾的最长递增子序列的长度为 1
        int max = 1; // 最长递增子序列的长度
        for (int i = 1; i < nums.length; i++) {
            int maxJ = 0; // 以 nums[i] 结尾的最长递增子序列的长度
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j])
                    maxJ = Math.max(maxJ, dp[j]); // 更新 maxJ
            }
            dp[i] = maxJ + 1; // 以 nums[i] 结尾的最长递增子序列的长度为 maxJ + 1
            max = Math.max(max, dp[i]); // 更新最长递增子序列的长度
        }
        return max;
    }
    // 解法二：贪心 + 二分查找
    public int lengthOfLIS2(int[] nums) {
        int[] tails = new int[nums.length];
        /*
        tails[i] 表示长度为 i+1 的递增子序列的最小末尾值
        tails[0...res-1] 必严格递增（执果索因） → 可以用二分查找
        二分查找的目标：找到最小的索引 i，使得 tails[i] >= num
        */
        int res = 0; // 当前 tails 中最长递增子序列的长度
        for (int num : nums) { // 遍历 nums 数组
            int i = 0; // 当前二分查找的左边界
            int j = res; // 当前二分查找的右边界
            System.out.println("==========================");
            System.out.println("num = " + num);
            System.out.println("i = " + i + ", j = " + j + ", res = " + res);
            System.out.println(Arrays.toString(tails));
            while (i < j) {
                // 对 tails 数组进行二分查找
                int m = i + (j - i) / 2; // 计算中间位置 m
                if (tails[m] < num)   i = m + 1; // num 更大，去右半部分
                else j = m; // num 更小，去左半部分（包括 mid）
            }
            // 替换 tails[i] 为更小的 num（贪心：让相同长度的序列末尾更小）
            tails[i] = num;
            System.out.println(Arrays.toString(tails));
            /*
            如果 i == res ，说明 num 比所有末尾都大，扩展 LIS 长度
            循环开始时 j = res
            如果 num 足够大，二分查找会一直向右走，最终 i = j = res
            所以 res == j 等价于 num 比所有现有末尾都大
            */
            System.out.println("i = " + i + ", j = " + j + ", res = " + res);
            if (res == j)   res++; // 二分查找到位置 j : 找到了更长的递增子序列
        }
        System.out.println("==========================");
        return res;
    }

    public static void main(String[] args) {
        LengthOfLIS lengthOfLIS = new LengthOfLIS();
        int[] nums = {8,1,2,5,0,3,4,6,1};
        System.out.println(Arrays.toString(nums));
        System.out.println(lengthOfLIS.lengthOfLIS2(nums));
    }
}
