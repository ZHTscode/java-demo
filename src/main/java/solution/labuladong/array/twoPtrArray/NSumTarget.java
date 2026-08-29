package solution.labuladong.array.twoPtrArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NSumTarget {
    // 注意：调用这个函数之前一定要先给 nums 排序
    // n 填写想求的是几数之和，start 从哪个索引开始计算（一般填 0），target 填想凑出的目标和
    public List<List<Integer>> nSumTarget(int[] nums, int n, int start, long target) {
        int size = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        // 至少 2Sum，且数组大小不应该小于 n
        if (n < 2 || size < n) return res;
        // base case
        if (n == 2) {
            int lp = start, rp = size - 1;
            while (lp < rp) {
                int sum = nums[lp] + nums[rp];
                    int lv = nums[lp], rv = nums[rp];
                if (sum < target) {
                    while (lp < rp && nums[lp] == lv) lp++;
                } else if (sum > target) {
                    while (lp < rp && nums[rp] == rv) rp--;
                } else {
                    res.add(new ArrayList<>(Arrays.asList(lv, rv)));
                    while (lp < rp && nums[lp] == lv) lp++;
                    while (lp < rp && nums[rp] == rv) rp--;
                }
            }
        } else {
            // n > 2 时，递归计算 (n-1)Sum 的结果
            for (int i = start; i < size; i++) {
                List<List<Integer>> nSumRes = nSumTarget(nums, n - 1, i + 1, target - nums[i]);
                for (List<Integer> pair : nSumRes) {
                    // (n-1)Sum 加上 nums[i] 就是 nSum
                    pair.add(nums[i]);
                    res.add(pair);
                }
                while (i < size - 1 && nums[i] == nums[i + 1]) i++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        NSumTarget ns = new NSumTarget();
        int[] nums = {1, 2, 3, 4, 5};
        List<List<Integer>> res = ns.nSumTarget(nums, 3, 0, 9);
        System.out.println(res);
    }
}
