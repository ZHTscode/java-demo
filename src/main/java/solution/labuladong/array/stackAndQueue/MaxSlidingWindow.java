package solution.labuladong.array.stackAndQueue;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaxSlidingWindow {
    /* 239. 滑动窗口最大值 */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        int idx = 0;
        for(int right = 0; right < n; right++){
            // 1. 右边界：新元素进来，把队尾所有 <=nums[right] 的全部弹出（维护单调递减）
            while(!deque.isEmpty() && nums[right] >= nums[deque.peekLast()]){
                deque.pollLast();
            }
            deque.offerLast(right);
            // 2. 左边界：移除已经滑出窗口左边界的元素
            if(right - deque.peekFirst() >= k){
                deque.pollFirst();
            }
            // 3. 更新结果：窗口大小达到k，队首元素是当前窗口的最大值
            if(right + 1 >= k){
                res[idx++] = nums[deque.peekFirst()];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MaxSlidingWindow solution = new MaxSlidingWindow();
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] res = solution.maxSlidingWindow(nums, k);
        for(int num : res){
            System.out.print(num + " ");
        }
    }
}
