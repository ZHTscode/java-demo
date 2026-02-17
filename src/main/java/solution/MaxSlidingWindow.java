package solution;
import java.util.*;

public class MaxSlidingWindow {
    /*
    解法一：优先队列（最大堆） 时间复杂度：O(nlogk)  空间复杂度：O(k)
    懒删除：不主动清理堆，只在堆顶过期时删除
    时间复杂度 O(n log k)：每个元素入堆一次，最多出堆一次
    */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        // pair1 和 pair2 都是长度为2的数组，pair[0] 存储数值，pair[1] 存储索引
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] pair1, int[] pair2) { // 返回值为正，后面元素优先级更高
                return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
                // 元素值不同，按值降序排列；值相同，按索引降序排列（索引小的容易出界）
            }
        });
        // 初始化前 k 个元素
        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{nums[i], i}); // 将元素加入优先队列（数值，索引）
        }
        int[] ans = new int[n - k + 1];
        ans[0] = pq.peek()[0]; // 第一个窗口的最大值
        // 每次滑动窗口时，将当前窗口的最大值加入答案
        for (int i = k; i < n; ++i) {
            pq.offer(new int[]{nums[i], i}); // 将元素加入优先队列（数值，索引）
            while (pq.peek()[1] <= i - k) { // i - k ：移动的步数（窗口左边界）
                pq.poll(); // 堆顶元素的索引（值最大） <= i - k ：不在当前窗口范围内
            }
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }
     /*
     解法二：双端队列 时间复杂度：O(n)  空间复杂度：O(k)
     核心：维护一个单调递减队列（存储下标），队头始终是最大值
     每个元素最多入队/出队一次 → 总操作 O(n)
     队头永远是最大值 → 获取最大值 O(1)
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        // 初始化前 k 个元素
        Deque<Integer> deque = new LinkedList<Integer>();
        for (int i = 0; i < k; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                // 「队列末尾元素」小于当前元素
                deque.pollLast(); // 移除所有比 nums[i] 小的元素
            }
            deque.offerLast(i); // 元素加入队列
            // 队头到队尾单调递减
        }
        int[] ans = new int[n - k + 1];
        ans[0] = nums[deque.peekFirst()];
        // 滑动窗口：每次滑动窗口时，将当前窗口的最大值加入答案
        for (int i = k; i < n; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast(); // 移除所有比 nums[i] 小的元素
            }
            deque.offerLast(i); // 元素加入队列
            while (deque.peekFirst() <= i - k) { // i - k ：移动的步数（窗口左边界）
                deque.pollFirst(); // 移除队头元素（不在当前窗口范围内）
            }
            ans[i - k + 1] = nums[deque.peekFirst()]; // 当前窗口的最大值
        }
        return ans;
    }
    /*
    解法三：分块 + 预处理 时间复杂度：O(n)  空间复杂度：O(n)
    核心思想：将数组分成若干块（每块大小 k），预处理：
    prefixMax[i]：从块首到 i 的最大值，suffixMax[i]：从 i 到块尾的最大值
    任意窗口 [i, i+k-1] 要么：
    完全在一个块内 → 直接取 prefixMax[i+k-1]
    跨越两个块 → max(suffixMax[i], prefixMax[i+k-1])
    */
    public int[] maxSlidingWindow3(int[] nums, int k) {
        int n = nums.length;
        int[] prefixMax = new int[n]; // 从块首到 i 的最大值
        int[] suffixMax = new int[n]; // 从 i 到块尾的最大值
        // 计算前缀最大值
        for (int i = 0; i < n; ++i) {
            if (i % k == 0) prefixMax[i] = nums[i]; // 块首元素 0,3,6...
            else prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
        }
        // 计算后缀最大值
        for (int i = n - 1; i >= 0; --i) {
            if (i == n - 1 || (i + 1) % k == 0) suffixMax[i] = nums[i]; // 块尾元素 2,5,8...
            else suffixMax[i] = Math.max(suffixMax[i + 1], nums[i]); // 当前元素
        }
        // 计算每个区间的最大值
        int[] ans = new int[n - k + 1];
        for (int i = 0; i <= n - k; ++i) {
            ans[i] = Math.max(suffixMax[i], prefixMax[i + k - 1]);
        }
        return ans;
    }

    public static void main(String[] args) {
        MaxSlidingWindow ms = new MaxSlidingWindow();
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(Arrays.toString(ms.maxSlidingWindow2(nums, k)));
    }
}
