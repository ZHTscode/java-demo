package solution;

import java.util.Arrays;

public class ThreeSumClosest {
    // 核心解题方法
    public static int threeSumClosest(int[] nums, int target) {
        // 1. 对数组进行排序，是双指针解法的前提
        Arrays.sort(nums);
        int n = nums.length;
        // 初始化最接近的和为前三个数的和
        int closestSum = nums[0] + nums[1] + nums[2];

        // 2. 固定第一个数，遍历到倒数第三个即可，后面留两个数给左右指针
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;   // 左指针：当前固定数的下一位
            int right = n - 1;  // 右指针：数组最后一位

            // 3. 双指针夹逼查找
            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];

                // 核心逻辑：判断当前和是否比之前的更接近目标值
                if (Math.abs(currentSum - target) < Math.abs(closestSum - target)) {
                    closestSum = currentSum;
                }

                // 优化：找到完全相等的情况，直接返回，绝对最优解
                if (currentSum == target) {
                    return target;
                } else if (currentSum < target) {
                    // 当前和偏小，左指针右移，增大三数之和
                    left++;
                } else {
                    // 当前和偏大，右指针左移，减小三数之和
                    right--;
                }
            }
        }
        // 返回最接近的和
        return closestSum;
    }

    // 主函数：测试用例，可直接运行
    public static void main(String[] args) {
        // 测试用例1 力扣官方示例
        int[] nums1 = {-1, 2, 1, -4};
        int target1 = 1;
        int result1 = threeSumClosest(nums1, target1);
        System.out.println("测试用例1结果：" + result1); // 预期输出：2

        // 测试用例2 边界情况：数组元素全部相同
        int[] nums2 = {0, 0, 0};
        int target2 = 1;
        int result2 = threeSumClosest(nums2, target2);
        System.out.println("测试用例2结果：" + result2); // 预期输出：0

        // 测试用例3 正数数组
        int[] nums3 = {1, 2, 3, 4, 5};
        int target3 = 8;
        int result3 = threeSumClosest(nums3, target3);
        System.out.println("测试用例3结果：" + result3); // 预期输出：8

        // 测试用例4 负数数组
        int[] nums4 = {-5, -3, -2, -1};
        int target4 = -8;
        int result4 = threeSumClosest(nums4, target4);
        System.out.println("测试用例4结果：" + result4); // 预期输出：-8
    }
}