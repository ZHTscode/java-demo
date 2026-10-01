package solution.labuladong.dataStructure.heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class AdvantageCount {
    /* 870.优势洗牌$$ */
    public int[] advantageCount(int[] nums1, int[] nums2) {
        // 给 nums2 降序排序
        Queue<int[]> maxpq = new PriorityQueue<>(
                (pair1, pair2) -> pair2[1] - pair1[1]
        );
        for (int i = 0; i < nums2.length; i++) {
            maxpq.offer(new int[]{i, nums2[i]});
        }
        // 给 nums1 升序排序
        Arrays.sort(nums1);
        // nums1[left] 最小，nums1[right] 最大
        int n = nums1.length;
        int left = 0, right = n - 1;
        int[] res = new int[n];

        while (!maxpq.isEmpty()) {
            int[] pair = maxpq.poll();
            int i = pair[0]; // nums2 中的最大值的索引
            int maxVal = pair[1]; // nums2 中的最大值
            if (nums1[right] > maxVal) {
                // 如果 nums1[right] 能胜过 maxVal，那就自己上
                res[i] = nums1[right];
                right--;
            } else {
                // 否则用最小值混一下，养精蓄锐
                res[i] = nums1[left];
                left++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 7, 11, 15};
        int[] nums2 = {1, 10, 4, 11};
        int[] res = new AdvantageCount().advantageCount(nums1, nums2);
        System.out.println(Arrays.toString(res));
    }
}
