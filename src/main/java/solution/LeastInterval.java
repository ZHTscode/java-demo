package solution;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class LeastInterval {
    /* 621. 任务调度器
       解法一：贪心 + 数学公式
       出现次数最多的任务决定了最少时间
       最高频率 maxFreq = 3（如 A 出现 3 次）
       达到最高频率任务数 maxCount = 2（如 A、B 都出现 3 次）*/
    public int leastInterval(char[] tasks, int n) {
        // 1. 统计每种任务的频率
        int[] freq = new int[26];
        for (char task : tasks) {
            freq[task - 'A']++;
        }
        // 2. 找到最高频率
        int maxFreq = 0;
        for (int f : freq) {
            maxFreq = Math.max(maxFreq, f);
        }
        // 3. 统计有多少个任务达到最高频率
        int maxCount = 0;
        for (int f : freq) {
            if (f == maxFreq)   maxCount++;
        }
        // 4. 计算最少时间
        int result = (maxFreq - 1) * (n + 1) + maxCount;
        // 5. 返回较大值（任务充足 vs 任务过多）
        /* 时间：0  1  2  3  4  5  6  7
           任务：A  B  待  A  B  待  A  B
                ↑-----↑   ↑-----↑  ↑--↑
                  n=2       n=2     结束 */
        // 任务充足 其他任务能填满冷却空隙 (maxFreq-1)×(n+1) + maxCount
        // 任务过多 没有空闲时间，连续执行 tasks.length
        return Math.max(tasks.length, result);
    }
    // 解法二：优先队列（最大堆）
    public int leastInterval2(char[] tasks, int n) {
        // 统计频率
        int[] freq = new int[26];
        for (char task : tasks) {
            freq[task - 'A']++;
        }
        // 最大堆（按频率排序）
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a); // 返回值为正，b 在队前
        for (int f : freq) {
            if (f > 0) pq.offer(f); // 频率为正的元素入队
        }
        int time = 0;
        while (!pq.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            int cycle = n + 1;
            // 一个冷却周期内执行任务
            while (cycle > 0 && !pq.isEmpty()) {
                temp.add(pq.poll() - 1); // 出队并减少频率
                cycle--;
                time++;
            }
            // 恢复未完成的任务
            for (int f : temp) {
                if (f > 0) pq.offer(f); // 频率为正的元素入队
            }
            // 如果还有任务，需要等待冷却
            if (!pq.isEmpty()) {
                time += cycle;
            }
        }
        return time;
    }

    public static void main(String[] args) {
        LeastInterval leastInterval = new LeastInterval();
        char[] tasks = new char[]{'A','A','A','B','B','B'};
        int n = 2;
        int res = leastInterval.leastInterval2(tasks, n);
        System.out.println(res);
    }
}
