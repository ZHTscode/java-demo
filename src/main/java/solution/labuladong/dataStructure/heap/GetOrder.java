package solution.labuladong.dataStructure.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class GetOrder {
    /* 1834. 单线程 CPU */
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        // 添加原始索引，方便后面排序
        List<int[]> triples = new ArrayList<>();
        for (int i = 0; i < tasks.length; i++) {
            triples.add(new int[]{tasks[i][0], tasks[i][1], i});
        }
        // 先按任务的开始时间排序
        triples.sort((a, b) -> a[0] - b[0]);
        // 按任务的处理时间排序，处理时间相同则按原始索引排序
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[1] != b[1]) {
                return a[1] - b[1]; // 处理时间短的在前
            }
            return a[2] - b[2]; // 原始索引小的在前
        });

        List<Integer> res = new ArrayList<>();
        int now = 0; // 完成任务的时间线
        int i = 0; // 任务索引
        while (res.size() < n) {
            if (!pq.isEmpty()) {
                // 完成队列中的一个任务
                int[] triple = pq.poll();
                res.add(triple[2]);
                now += triple[1]; // 推进时间线
            } else if (i < n && triples.get(i)[0] > now) {
                // 队列为空可能因为还没到开始时间，
                // 直接把时间线推进到最近任务的开始时间
                now = triples.get(i)[0];
            }
            // 由于时间线的推进，产生可以执行的任务
            for (; i < n && triples.get(i)[0] <= now; i++) {
                pq.offer(triples.get(i));
            }
        }
        // 将 List 转化成 int[]
        int[] arr = new int[n];
        for (int j = 0; j < n; j++) {
            arr[j] = res.get(j);
        }
        return arr;
    }

    public static void main(String[] args) {
        GetOrder solution = new GetOrder();
        int[][] tasks = {{1, 2}, {2, 4}, {3, 2}, {4, 1}};
        int[] order = solution.getOrder(tasks);
        for (int i : order) {
            System.out.print(i + " ");
        }
    }
}