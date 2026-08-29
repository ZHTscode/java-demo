package solution.labuladong.array.slideWindow;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubstring {
    /* 3. 无重复字符的最长子串 */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> window = new HashMap<>(); // 窗口内字符个数统计
        int left = 0, right = 0; // 窗口左右边界
        int res = 0; // 最长无重复子串长度
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            window.put(c, window.getOrDefault(c, 0) + 1);
            while (window.get(c) > 1) { // 当窗口内字符c的个数大于1时，收缩左边界
                char d = s.charAt(left); // 左边界字符d
                left++; // 收缩左边界
                window.put(d, window.getOrDefault(d, 0) - 1); // 更新窗口内字符d的个数
            }
            res = Math.max(res, right - left);
        }
        return res;
    }

    public static void main(String[] args) {
        LengthOfLongestSubstring l = new LengthOfLongestSubstring();
        System.out.println(l.lengthOfLongestSubstring("abcabcbb"));
    }
}
