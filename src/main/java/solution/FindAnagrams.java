package solution;

import java.util.ArrayList;
import java.util.List;

public class FindAnagrams {
    /* 438.找字符串中所有字母异位词 */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int n = s.length(), m = p.length();
        if (n < m) return res;
        int[] pCount = new int[26];
        int[] sCount = new int[26];
        int diff = 0; // 统计字符次数不同的数量，0表示完全一致
        // 初始化统计
        for (int i = 0; i < m; i++) { // m = p.length
            pCount[p.charAt(i)-'a']++;
            sCount[s.charAt(i)-'a']++;
        }
        // 计算初始diff
        for (int i = 0; i < 26; i++) {
            if (pCount[i] != sCount[i]) diff++;
        }
        if (diff == 0) res.add(0); // s与p中各字符出现的次数一致，返回索引0
        // 长度为 p.length 的固定滑动窗口，用diff替代数组比较
        for (int right = m; right < n; right++) {
            // 右边界字符 c1 移入 s [xxx]c1
            int c1 = s.charAt(right) - 'a';
            sCount[c1]++; // c1 出现次数 + 1
            if (sCount[c1] == pCount[c1])
                diff--; // 从不等变相等，diff--
            else if (sCount[c1] == pCount[c1] + 1)
                diff++; // 从相等变不等，diff++
            // 左边界字符 c2 移出 s c2[xxx]
            int c2 = s.charAt(right - m) - 'a';
            sCount[c2]--;
            if (sCount[c2] == pCount[c2])
                diff--;
            else if (sCount[c2] == pCount[c2] -1)
                diff++;
            // diff为0表示一致，记录左边界
            if (diff == 0)
                res.add(right - m + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        FindAnagrams f = new FindAnagrams();
        System.out.println(f.findAnagrams("cbaebabacd", "abc"));
    }
}