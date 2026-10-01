package solution.labuladong.dataStructure.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class FrequencySort {
    /* 451. 根据字符出现频率排序 */
    public String frequencySort(String s) {
        char[] chars = s.toCharArray();
        // 遍历字符串，统计每个字符出现频率
        Map<Character, Integer> charToFreq = new HashMap<>();
        for (char ch : chars) {
            charToFreq.put(ch, charToFreq.getOrDefault(ch, 0) + 1);
        }
        // 定义优先队列，频率大的在前，先出队
        Queue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(
                (entry1, entry2) ->
                        entry2.getValue() - entry1.getValue()
        );
        // 所有元素入队
        for (Map.Entry<Character, Integer> entry : charToFreq.entrySet()) {
            // entrySet()：返回 map 所有元素集合
            pq.offer(entry);
        }
        // 逐个出队并拼接字符串
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> entry = pq.poll();
            String part = String.valueOf(entry.getKey()).repeat(entry.getValue());
            sb.append(part);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        FrequencySort solution = new FrequencySort();
        String s = "tree";
        System.out.println(solution.frequencySort(s));
    }
}