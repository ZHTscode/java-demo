package solution;

import java.util.HashSet;
import java.util.Set;

public class LengthOfLongestSubstring {
    /* 3. 无重复字符的最长子串 */
    public int lengthOfLongestSubstring(String s) {
        // 存储当前滑动窗口内的字符，快速判断重复
        Set<Character> charSet = new HashSet<>();
        //Set<Character> charSet 声明一个泛型集合变量，泛型限定字符类型
        int maxLength = 0; // 记录最长无重复子串长度
        int left = 0; // 滑动窗口左指针（窗口起始位置）
        // 右指针遍历字符串，逐个扩展窗口右边界
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            // 如果当前字符已在窗口中，不断右移左指针，直到移除重复字符
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

    public static void main(String[] args) {
        LengthOfLongestSubstring lengthOfLongestSubstring = new LengthOfLongestSubstring();
        // 测试案例1：常规重复字符串
        String s1 = "abcabcbb";
        int result1 = lengthOfLongestSubstring.lengthOfLongestSubstring(s1);
        System.out.println("输入: \"" + s1 + "\" → 最长无重复子串长度: " + result1); // 预期输出 3
        // 测试案例2：全重复字符
        String s2 = "bbbbb";
        int result2 = lengthOfLongestSubstring.lengthOfLongestSubstring(s2);
        System.out.println("输入: \"" + s2 + "\" → 最长无重复子串长度: " + result2); // 预期输出 1
        // 测试案例3：部分重复字符
        String s3 = "pwwkew";
        int result3 = lengthOfLongestSubstring.lengthOfLongestSubstring(s3);
        System.out.println("输入: \"" + s3 + "\" → 最长无重复子串长度: " + result3); // 预期输出 3
        // 测试案例4：空字符串
        String s4 = "";
        int result4 = lengthOfLongestSubstring.lengthOfLongestSubstring(s4);
        System.out.println("输入: \"" + s4 + "\" → 最长无重复子串长度: " + result4); // 预期输出 0
        // 测试案例5：无重复字符
        String s5 = "abcdefg";
        int result5 = lengthOfLongestSubstring.lengthOfLongestSubstring(s5);
        System.out.println("输入: \"" + s5 + "\" → 最长无重复子串长度: " + result5); // 预期输出
        // 测试案例6：单个字符
        String s6 = "a";
        int result6 = lengthOfLongestSubstring.lengthOfLongestSubstring(s6);
        System.out.println("输入: \"" + s6 + "\" → 最长无重复子串长度: " + result6); // 预期输出 1
        // 测试案例7：含特殊字符/数字
        String s7 = "ab123ab456";
        int result7 = lengthOfLongestSubstring.lengthOfLongestSubstring(s7);
        System.out.println("输入: \"" + s7 + "\" → 最长无重复子串长度: " + result7); // 预期输出 6（"123ab4" 或 "b456" 等，最长为6）
    }
}