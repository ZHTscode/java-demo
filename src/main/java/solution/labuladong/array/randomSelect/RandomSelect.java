package solution.labuladong.array.randomSelect;

import java.util.Random;

public class RandomSelect {
    /* 528. 按权重随机选择 */
    private int[] preSum; // 前缀和数组
    private Random rand = new Random();

    public RandomSelect(int[] w) { // 构造前缀和数组
        int n = w.length;
        preSum = new int[n + 1];
        for(int i = 1; i <= n; i++){
            preSum[i] = preSum[i - 1] + w[i - 1]; // 前i个元素的和
        }
    }

    public int pickIndex() { // 根据权重随机选择一个下标
        int target = rand.nextInt(preSum[preSum.length - 1]) + 1; // 随机选择一个目标值
        int left = 0, right = preSum.length - 1;
        while (left <= right) { // 二分查找目标值
            int mid = left + (right - left) / 2;
            if (preSum[mid] < target) {
                left = mid + 1; // 目标值在右半边
            } else {
                right = mid - 1; // 目标值在左半边
            }
        }
        return left - 1;
    }

    public static void main(String[] args) {
        int[] w = {1, 2, 3, 4, 5};
        RandomSelect solution = new RandomSelect(w);
        System.out.println(solution.pickIndex());
    }
}
