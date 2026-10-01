package solution.labuladong.dataStructure.heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class FindKthLargest {
    /* 215. 数组中的第K个最大元素 */
    public int findKthLargest(int[] nums, int k) {
        // 小顶堆，堆顶是最小元素
        Queue<Integer> pq = new PriorityQueue<>();
        for (int e : nums) {
            // 每个元素都要过一遍二叉堆
            pq.offer(e);
            // 堆中元素多于 k 个时，删除堆顶元素
            if (pq.size() > k) {
                pq.poll();
            }
        }
        // pq 中剩下的是 nums 中 k 个最大元素，
        // 堆顶是最小的那个，即第 k 个最大元素
        return pq.peek();
    }

    public static void main(String[] args) {
        FindKthLargest fk = new FindKthLargest();
        int[] nums = {3,2,1,5,6,4};
        int k = 2;
        System.out.println(fk.findKthLargest(nums, k));
    }
}
