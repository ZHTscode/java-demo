package solution.labuladong.greedy;

import java.util.Arrays;

public class EraseOverlapInterval {
    /* 435. 无重叠区间 */
    /*  假设最优解里第一个区间是 y₁
        x₁.end ≤ y₁.end（x₁是全局结束最早）
        把y₁直接替换成x₁，不会减少答案数量
        替换后，最优解依然合法（区间互不相交
        归纳：每一步都能保证存在最优解以当前贪心选择开头，最终贪心结果 = 最优数量 */
    public int eraseOverlapIntervals(int[][] intvs) {
        int n = intvs.length;
        return n - intervalSchedule(intvs);
    }
    public int intervalSchedule(int[][] intvs) {
        if (intvs.length == 0) return 0;
        // 按 end 升序排序
        Arrays.sort(intvs, (a, b) -> Integer.compare(a[1], b[1]));
        // 至少有一个区间不相交
        int count = 1;
        // 排序后，第一个区间就是 x
        int x_end = intvs[0][1];
        for (int[] interval : intvs) {
            int start = interval[0];
            if (start >= x_end) {
                // 找到下一个选择的区间了
                count++;
                x_end = interval[1];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        EraseOverlapInterval solution = new EraseOverlapInterval();
        int[][] intvs = {{1,2},{2,3},{3,4},{1,3}};
        System.out.println(solution.eraseOverlapIntervals(intvs));
    }
}
