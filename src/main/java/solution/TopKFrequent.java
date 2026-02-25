package solution;

import java.util.*;

public class TopKFrequent {
    /* 347. 前 K 个高频元素 */
    // 解法一：堆
    public int[] topKFrequent(int[] nums, int k) {
        // 1. 统计频率
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            // 键为 num，值为该数字出现的次数
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        System.out.println(freq);
        // 2. 创建小顶堆（按频率升序）
        PriorityQueue<Integer> heap = new PriorityQueue<>(
                // 按频率升序
                (a, b) -> freq.get(a) - freq.get(b) // 小的在队前，后续先出队
        );
        // 3. 遍历所有数字，维护大小为 k 的堆
        for (int num : freq.keySet()) {
            heap.offer(num); // 将数字加入堆中
            if (heap.size() > k) {
                heap.poll(); // 弹出频率最小的数字
            }
        }
        System.out.println(heap);
        // 4. 取出结果
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            if(heap.isEmpty()) break;
            result[i] = heap.poll(); // 弹出堆顶元素
        }
        return result;
    }
    // 力扣官方题解
    public int[] topKFrequent2(int[] nums, int k) {
        // 将数字出现的次数存入哈希表
        Map<Integer, Integer> occurrences = new HashMap<>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }
        // 创建一个小顶堆，存储长度为2的数组，第一个元素表示数字，第二个元素表示该值出现的次数
        PriorityQueue<int[]> queue = new PriorityQueue<>(
                (a, b) -> a[1] - b[1]
        );
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            if (queue.size() == k) {
                if (queue.peek()[1] < count) {
                    queue.poll();
                    queue.offer(new int[]{num, count});
                }
            } else {
                queue.offer(new int[]{num, count});
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll()[0];
        }
        return res;
    }
    // 解法二：桶排序
    public int[] topKFrequent3(int[] nums, int k) {
        // 1. 统计频率
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            // 键为 num，值为该数字出现的次数
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        System.out.println(freq);
        // 2. 创建桶：index = 频率，value = 数字
        List<Integer>[] buckets = new List[nums.length + 1];
        for (int num : freq.keySet()) {
            int count = freq.get(num); // 获取数字的频率
            if (buckets[count] == null) { // 如果桶为空
                buckets[count] = new ArrayList<>();
            }
            buckets[count].add(num); // 将数字加入桶中
        }
        System.out.println(Arrays.toString(buckets));
        // 3. 从高频率往低频率取，直到取满 k 个
        int[] result = new int[k];
        int idx = 0;
        for (int i = buckets.length - 1; i >= 0 && idx < k; i--) {
            if (buckets[i] != null) {
                for (int num : buckets[i]) {
                    result[idx++] = num;
                    if (idx == k) break;
                }
            }
        }
        return result;
    }

    // 解法四：力扣最优
    public int[] topKFrequent4(int[] nums, int k) {
        // 步骤1：找到nums中的最大值和最小值，确定数字范围（解决数组映射问题）
        int min = nums[0], max = nums[0];
        for (int num : nums) {
            if (num < min) min = num;
            if (num > max) max = num;
        }
        // 步骤2：创建频率数组，统计每个数字的出现次数（核心：num - min 做偏移量，映射到0开始的索引）
        int[] freq = new int[max - min + 1];
        for (int num : nums) {
            freq[num - min]++; // 每出现一次，对应索引的频率+1
        }
        System.out.println(Arrays.toString(freq));
        // 步骤3：按频率从高到低遍历，收集前k个高频数字（暴力核心：双层循环找最大频率）
        int[] res = new int[k];
        int index = 0; // 结果数组的下标
        // 优化：先找实际的最大频率，避免从nums.length开始无效循环
        int realMaxFreq = 0;
        for (int f : freq) {
            if (f > realMaxFreq) realMaxFreq = f;
        }
        int maxFreq = realMaxFreq; // 用实际最大频率替代nums.length
        while (index < k) { // 收集满k个就停止
            for (int i = 0; i < freq.length; i++) {
                if (freq[i] == maxFreq) { // 找到当前最大频率的数字
                    res[index++] = i + min; // 偏移量还原：索引i → 原数字i+min
                    if (index == k) break; // 提前退出，避免多余循环
                }
            }
            maxFreq--; // 当前最大频率的数字收集完，找下一个更小的频率
        }
        return res;
    }

    public static void main(String[] args) {
        TopKFrequent tkf = new TopKFrequent();
        int[] nums = {1,1,1,2,2,3};
        int k = 2;
        System.out.println(Arrays.toString(tkf.topKFrequent4(nums, k)));
    }

}
