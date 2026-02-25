package solution;

import java.util.Arrays;

/*
* 反向思考：
* 假设最后一个被戳破的气球是 i，那么它左右邻居就是区间边界 left 和 right（其他都戳完了）
* 考虑开区间 (left, right)
* 枚举这个区间中最后一个被戳的气球 i
* 得分 = val[left] * val[i] * val[right]（此时 left 和 right 是唯一剩下的邻居）
* 加上左右两个子区间的最大得分：solve(left, i) + solve(i, right)
*/

public class MaxCoins {
    /* 312.戳球 */
    // 解法一：记忆化搜索（递归 + 缓存）
    public int[][] rec; // 记录爆破区间[i, j]能获得的最大硬币数
    public int[] val; // val[i]表示第i个气球上的数字
    public int maxCoins(int[] nums) {
        int n = nums.length;
        // val数组的长度为n+2，val[0]和val[n+1]都等于1
        val = new int[n + 2];
        System.arraycopy(nums, 0, val, 1, n);
        val[0] = val[n + 1] = 1;
        // rec 数组的长度为n+2，rec[i][j]表示爆破区间[i, j]能获得的最大硬币数
        rec = new int[n + 2][n + 2];
        // 将 rec 数组所有元素初始化为-1
        for (int i = 0; i <= n + 1; i++) {
            Arrays.fill(rec[i], -1);
        }
        return solve(0, n + 1);
    }
    public int solve(int left, int right) {
        if (left >= right - 1) return 0; // 递归终止条件
        if (rec[left][right] != -1) return rec[left][right]; // 如果已经算过，直接返回
        // 核心循环：枚举最后一个被戳的气球
        for (int i = left + 1; i < right; i++) {
            int sum = val[left] * val[i] * val[right];
            sum += solve(left, i) + solve(i, right);
            rec[left][right] = Math.max(rec[left][right], sum);
        }
        // 返回在开区间 (left, right) 内戳破所有气球能获得的最大硬币数
        return rec[left][right];
    }
    // 解法二：动态规划
    public int maxCoins2(int[] nums) {
        int n = nums.length;
        int[][] rec = new int[n + 2][n + 2];
        int[] val = new int[n + 2];
        val[0] = val[n + 1] = 1;
        System.arraycopy(nums, 0, val, 1, n);
        // 核心循环：枚举区间长度
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 2; j <= n + 1; j++) { // 遍历所有可能的区间
                for (int k = i + 1; k < j; k++) { // k在区间(i, j)内
                    int sum = val[i] * val[k] * val[j];
                    sum += rec[i][k] + rec[k][j];
                    rec[i][j] = Math.max(rec[i][j], sum); // 遍历所有可能的k，取最大值
                }
            }
        }
        return rec[0][n + 1];
    }

    public static void main(String[] args) {
        MaxCoins maxCoins = new MaxCoins();
        int[] nums = {3,1,5,8};
        System.out.println(maxCoins.maxCoins2(nums));
    }
}
