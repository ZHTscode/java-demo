package solution.labuladong.array.binarySearch;

import java.util.ArrayList;
import java.util.List;

public class NumMatchingSubseq {
    /* 792. 匹配子序列的单词数 */
    public int numMatchingSubseq(String s, String[] words) {
        List<Integer>[] charToIndexes = new ArrayList[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(charToIndexes[c - 'a'] == null){
                charToIndexes[c - 'a'] = new ArrayList<>();
            }
            charToIndexes[c - 'a'].add(i);
        }
        int res = 0;
        for (String word : words) {
            int i = 0, j = 0; // i为word的指针，j为s的指针
            while (i < word.length() && j < s.length()) {
                char c = word.charAt(i);
                List<Integer> indexes = charToIndexes[c - 'a'];
                if (indexes == null) {
                    break; // word 中包含 s 中不存在的字符
                }
                int index = leftBound(indexes, j); // 在 s[j..] 中搜索等于 word[i] 的最小索引
                if (index == -1) {
                    break;
                }
                i++;
                j = indexes.get(index) + 1; // 更新 j 为下一个可能的起始位置（保证顺序一致）
            }
            if (i == word.length()) { // word[i] 匹配完，是 s 的子序列
                res++;
            }
        }
        return res;
    }

    private int leftBound(List<Integer> indexes, int target) {
        int left = 0, right = indexes.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (indexes.get(mid) < target) {
                left = mid + 1;
            } else if (indexes.get(mid) > target) {
                right = mid - 1;
            } else if (indexes.get(mid) == target) {
                return mid; // 找到目标值，返回索引
            }
        }
        return -1; // 未找到目标值，返回 -1
    }

    public static void main(String[] args) {
        NumMatchingSubseq solution = new NumMatchingSubseq();
        String s = "abcde";
        String[] words = {"a", "bb", "acd", "ace"};
        System.out.println(solution.numMatchingSubseq(s, words));
    }
}
