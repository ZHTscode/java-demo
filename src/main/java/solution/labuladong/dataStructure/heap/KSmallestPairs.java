package solution.labuladong.dataStructure.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class KSmallestPairs {
    /* 373. 查找和最小的 K 对数字 */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        Queue<int[]> pq = new PriorityQueue<>((a,b) -> a[0]+a[1]-b[0]-b[1]);
        for(int i=0; i<nums1.length; i++){
            // 将 nums2 的第一个元素和 nums1 的每个元素组合加入优先级队列
            pq.offer(new int[]{nums1[i], nums2[0], 0}); // 0 表示 nums2 的索引
        }
        List<List<Integer>> res = new ArrayList<>();
        while(!pq.isEmpty() && k>0){
            // 当前最小值加入结果列表
            int[] cur = pq.poll();
            res.add(List.of(cur[0], cur[1]));
            k--;
            // 将当前最小值的下一个值加入优先级队列
            int nextIdx = cur[2] + 1; // nextIdx 表示 nums2 的索引
            if(nextIdx < nums2.length){
                // nums1 固定为最小，nums2 变大
                pq.offer(new int[]{cur[0], nums2[nextIdx], nextIdx});
            }
        }
        return res;
    }

    public static void main(String[] args) {
        KSmallestPairs solution = new KSmallestPairs();
        int[] nums1 = {1,7,11};
        int[] nums2 = {2,4,6};
        int k = 3;
        List<List<Integer>> result = solution.kSmallestPairs(nums1, nums2, k);
        System.out.println(result);
    }
}
