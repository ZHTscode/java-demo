package solution.labuladong.dataStructure.heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class KthLargest {
    /* 703. 数据流中的第 K 大整数 */
    private int k;
    private Queue<Integer> pq;

    public KthLargest(int k, int[] nums) { // 构造方法
        pq = new PriorityQueue<>(); // 默认小顶堆
        // 将 nums 装入小顶堆，保留下前 k 大的元素
        for (int e : nums) {
            pq.offer(e);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        this.k = k;
    }

    public int add(int val) {
        // 维护小顶堆只保留前 k 大的元素
        pq.offer(val);
        if (pq.size() > k) {
            pq.poll();
        }
        // 堆顶就是第 k 大元素（即倒数第 k 小的元素）
        return pq.peek();
    }
}