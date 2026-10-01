package solution.labuladong.dataStructure.heap;

import java.util.*;

public class TopKFrequentII {
    /* 692. 前 K 个高频单词$$ */
    public List<String> topKFrequent(String[] words, int k) {
        // 遍历字符数组，统计每个字符出现频率
        Map<String, Integer> wordToFreq = new HashMap<>();
        for (String word : words) {
            wordToFreq.put(word, wordToFreq.getOrDefault(word, 0) + 1);
        }
        // 定义优先队列
        Queue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                (entry1, entry2) -> {
                    if (entry1.getValue().equals(entry2.getValue())) {
                        // 出现频率相同，按字典序排序
                        return entry2.getKey().compareTo(entry1.getKey());
                    }
                    // 队列按照字符串出现频率从小到大排序
                    return entry1.getValue().compareTo(entry2.getValue());
                });
        // 大小固定的小顶堆
        for (Map.Entry<String, Integer> entry : wordToFreq.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) pq.poll();
        }
        // 倒序输出结果
        List<String> res = new LinkedList<>();
        while (!pq.isEmpty()) {
            res.add(0, pq.poll().getKey());
        }
        return res;
    }

    public static void main(String[] args) {
        TopKFrequentII topKFrequentII = new TopKFrequentII();
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        int k = 2;
        List<String> res = topKFrequentII.topKFrequent(words, k);
        System.out.println(res);
    }
}
