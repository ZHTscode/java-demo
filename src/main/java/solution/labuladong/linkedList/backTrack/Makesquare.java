package solution.labuladong.linkedList.backTrack;

import java.util.HashMap;
import java.util.Map;

public class Makesquare {
    /* 473. 火柴拼正方形 */
    Map<Integer, Boolean> memo;
    public boolean makesquare(int[] matchsticks) {
        memo = new HashMap<>();
        return canPartitionKSubsets(matchsticks, 4);
    }
    private boolean canPartitionKSubsets(int[] nums, int k) {
        // 排除一些基本情况
        if (k > nums.length) return false;
        int sum = 0;
        for (int v : nums) sum += v; // 计算 nums 的总和
        if (sum % k != 0) return false;
        int target = sum / k; // 每个桶的目标和
        int used = 0; // 记录 nums 中的元素是否被使用过
        // k 号桶初始什么都没装，从 nums[0] 开始做选择
        return backtrack(k, 0, nums, 0, used, target);
    }
    private boolean backtrack(int k, int bucket, int[] nums, int start, int used, int target) {
        if (k == 0) return true; // 所有桶都装满
        if (bucket == target) { // 当前桶装满，递归穷举下一个桶的选择
            // 让下一个桶从 nums[0] 开始选数字
            boolean res = backtrack(k - 1, 0, nums, 0, used, target);
            memo.put(used, res); // 缓存结果
            return res;
        }
        if (memo.containsKey(used)) return memo.get(used); // 避免冗余计算
        for (int i = start; i < nums.length; i++) {
            if (((used >> i) & 1) == 1) continue; // nums[i] 已经被装入别的桶中
            if (nums[i] + bucket > target) continue; // 装入当前桶会超出目标和
            // 做选择
            used |= 1 << i; // 将第 i 位置为 1
            bucket += nums[i];
            // 递归穷举下一个数字是否装入当前桶
            if (backtrack(k, bucket, nums, i + 1, used, target)) {
                return true;
            }
            // 撤销选择
            used ^= 1 << i; // 将第 i 位恢复 0
            bucket -= nums[i];
        }
        return false;
    }

    public static void main(String[] args) {
        Makesquare sol = new Makesquare();
        // 样例1: [1,1,2,2,2] true
        int[] arr1 = {1,1,2,2,2};
        System.out.println(sol.makesquare(arr1));
        // 样例2: [3,3,3,3,4] false
        int[] arr2 = {3,3,3,3,4};
        System.out.println(sol.makesquare(arr2));
    }
}