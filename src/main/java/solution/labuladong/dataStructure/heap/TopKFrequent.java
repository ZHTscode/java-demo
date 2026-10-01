package solution.labuladong.dataStructure.heap;

import java.util.*;

public class TopKFrequent {
    /* 347. 前 K 个高频元素 */
    // 解法一：优先级队列
    public int[] topKFrequent(int[] nums, int k) {
        // 遍历数组，统计每个字符出现频率
        Map<Integer, Integer> valToFreq = new HashMap<>();
        for (int v : nums) {
            valToFreq.put(v, valToFreq.getOrDefault(v, 0) + 1);
        }
        // 定义优先队列，频率小的在前，先出队
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
                (entry1, entry2) ->
                        entry1.getValue() - entry2.getValue()
        );
        // 所有元素入队
        for (Map.Entry<Integer, Integer> entry : valToFreq.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) {
                // 弹出最小元素，维护队列内是 k 个频率最大的元素
                pq.poll();
            }
        }
        // 逐个出队加入结果
        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            // res 数组中存储前 k 个最大元素
            res[i] = pq.poll().getKey();
        }
        return res;
    }
    // 解法二：计数排序
    public int[] topKFrequent2(int[] nums, int k) {
        // 遍历字符串，统计每个字符出现频率
        HashMap<Integer, Integer> valToFreq = new HashMap<>();
        for (int v : nums) {
            valToFreq.put(v, valToFreq.getOrDefault(v, 0) + 1);
        }
        // 频率 -> 这个频率有哪些元素
        List<List<Integer>> freqToVals = new ArrayList<>(nums.length + 1);
        for(int i=0; i<=nums.length; i++){
            freqToVals.add(new ArrayList<>());
        }
        for (Map.Entry<Integer,Integer> entry : valToFreq.entrySet()) {
            int val = entry.getKey();
            int freq = entry.getValue();
            freqToVals.get(freq).add(val);
        }
        int[] res = new int[k];
        int p = 0;
        // 下标就是频率，从最大下标往小遍历，就能从高频到低频拿元素
        for (int i = freqToVals.size() - 1; i > 0; i--) {
            List<Integer> valList = freqToVals.get(i);
            if (valList == null) continue;
            for (Integer integer : valList) {
                // 最高频的 k 个元素装入 res
                res[p] = integer;
                p++;
                if (p == k) return res;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        TopKFrequent topKFrequent = new TopKFrequent();
        int[] nums = {1,1,1,2,2,3};
        int k = 2;
        int[] res = topKFrequent.topKFrequent(nums, k);
        for (int i : res) {
            System.out.println(i);
        }
    }
}
