package solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Merge {
    /* 56. 合并区间
       解法一：排序 + 贪心（通用）
       按起点从小到大排序后，重叠的区间一定会挨在一起
       只需线性扫描一次，比较「当前区间」和「下一区间」即可
       设当前已合并的区间是 current，下一个待处理的区间是 next：
        情况 A：重叠 (next.start <= current.end)
         操作：更新 current 的终点为 max(current.end, next.end)
         起点不变：已排序，current.start 一定更小
        情况 B：不重叠 (next.start > current.end)
         说明：中间断开了，current 已经彻底结束
         操作：把 current 加入结果集，然后把 next 设为新的 current */
    public int[][] merge(int[][] intervals) {
            if (intervals == null || intervals.length == 0)
                return new int[0][0];
            // 1. 按区间的起始位置（各行的第一个元素）升序排序
            Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
            // a，b 代表 intervals 的元素，即某一行，类型为int[]；返回值为正，后面元素优先级更高
            List<int[]> result = new ArrayList<>(); // 存储合并后的区间
            // 2. 初始化第一个子区间
            int[] currentInterval = intervals[0]; // 当前子区间
            result.add(currentInterval); // 将当前子区间加入结果集
            // 3. 遍历剩余子区间
            for (int[] interval : intervals) {
                int currentEnd = currentInterval[1];
                int nextStart = interval[0]; // 下一子区间的起始位置
                int nextEnd = interval[1];   // 下一子区间的终点位置
                if (nextStart <= currentEnd) { // 重叠 → 合并（更新当前区间的终点）
                    currentInterval[1] = Math.max(currentEnd, nextEnd); // 取两者的最大终点
                }
                else { // 不重叠 → 将当前区间加入结果集，并开始新区间
                    currentInterval = interval; // 将 next 设为新的 current
                    result.add(currentInterval); // 将当前区间加入结果集
                }
            }
        return result.toArray(new int[result.size()][]); // 转换为二维数组
    }
    /* 解法二：差分 或 扫描线编码（最优）
       核心：把区间变成“计数”
       把数轴上的每个点放大 2 倍（为了区分起点和终点）
        遇到起点，计数器 +1；遇到终点，计数器 -1
        遍历数轴，只要计数器 > 0，说明还在某个区间内部
        当计数器归零时，说明一个完整的合并区间结束 */
    public int[][] merge2(int[][] intervals) {
        int max = 0;
        List<int[]> res = new ArrayList<int[]>();
        for (int[] interval: intervals) {
            max = Math.max(max, interval[1]); // 记录最大的右边界值
        }
        int[] cnt = new int[2 * max + 2]; // 构建差分编码数组
        for (int[] interval: intervals) {
            cnt[interval[0] * 2] ++; // 起点为偶数，值为 +1
            cnt[interval[1] * 2 + 1] --; // 终点为奇数，值为 -1
        }
        System.out.println(Arrays.toString(cnt));
        int sum = 0; // 记录当前是否还在一个区间内
        int len = 0; // 记录当前区间编码后的长度
        for (int i = 0; i < max * 2 + 2; i ++ ) {
            sum += cnt[i];
            if (sum > 0) len ++; // 当前位置在区间内
            else if (len > 0) { // sum = 0 且 len > 0：区间结束
                int start = (i - len) / 2;
                int end = (i - 1) / 2;
                res.add(new int[]{start, end});
                len = 0;
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    public static void main(String[] args) {
        Merge m = new Merge();
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        int[][] merge = m.merge2(intervals);
        System.out.println(Arrays.deepToString(merge));
    }
}
