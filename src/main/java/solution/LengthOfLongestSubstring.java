package solution;

import java.util.HashSet;
import java.util.Set;

public class LengthOfLongestSubstring {
    /* 3. 无重复字符的最长子串 */
    public int lengthOfLongestSubstring(String s) {
        // 集合存储当前滑动窗口内的字符，快速判断重复
        Set<Character> charSet = new HashSet<>();
        int maxLength = 0; // 记录最长无重复子串长度
        int left = 0; // 左指针，窗口起始位置
        // 右指针遍历，扩展窗口右边界
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            // 当前字符已在窗口中，右移左指针，直到移除重复字符
            while (charSet.contains(currentChar)) {
                charSet.remove(s.charAt(left));
                left++;
            }
            // 将当前字符加入窗口
            charSet.add(currentChar);
            // 更新最长长度（当前窗口长度 = right - left + 1）
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.isEmpty()){
            return 0;
        }
        int ans = 0;
        int left = 0;
        boolean[] has = new boolean[128];
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            while(has[c]) {
                has[s.charAt(left)] = false;
                left++;
            }
            has[c] = true;
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        LengthOfLongestSubstring lengthOfLongestSubstring = new LengthOfLongestSubstring();
        String s1 = "abcabcbb";
        int result = lengthOfLongestSubstring.lengthOfLongestSubstring2(s1);
        System.out.println(result);
        }
}