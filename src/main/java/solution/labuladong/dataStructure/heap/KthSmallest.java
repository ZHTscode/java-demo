package solution.labuladong.dataStructure.heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class KthSmallest {
    public int kthSmallest(int[][] matrix, int k) {
        // 存储三元组 (matrix[i][j], i, j)
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        // 第一列元素加入优先级队列
        for (int i = 0; i < matrix.length; i++) {
            pq.offer(new int[]{matrix[i][0], i, 0});
        }
        int res = -1;
        // 执行合并多个有序链表的逻辑，找到第 k 小的元素
        while (!pq.isEmpty() && k > 0) {
            int[] cur = pq.poll();
            res = cur[0];
            k--;
            // 下一个节点加入优先级队列
            int i = cur[1], j = cur[2];
            if (matrix[i].length > j + 1) {
                pq.offer(new int[]{matrix[i][j + 1], i, j + 1});
            }
        }
        return res;
    }

    public static void main(String[] args) {
        KthSmallest solution = new KthSmallest();
        int[][] matrix = {{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
        int k = 8;
        int result = solution.kthSmallest(matrix, k);
        System.out.println(result);
    }
}