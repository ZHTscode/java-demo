package solution;

public class LongestPalindrome {
    // 记录最长回文子串的起始索引和长度
    private int start = 0;
    private int maxLen = 0;
    public String longestPalindrome(String s) {
        // 边界条件：空字符串或长度为1的字符串直接返回
        if (s == null || s.length() < 2) {
            return s;
        }
        int n = s.length();
        // 遍历每个字符，作为奇数长度回文的中心
        for (int i = 0; i < n; i++) {
            expandAroundCenter(s, i, i); // 奇数长度（中心为单个字符）
            expandAroundCenter(s, i, i + 1); // 偶数长度（中心为两个字符之间）
        }
        // 根据起始索引和最大长度截取结果
        return s.substring(start, start + maxLen);
    }

    /**
     * 中心扩展方法
     * @param s 原字符串
     * @param left 左指针（扩展起始左边界）
     * @param right 右指针（扩展起始右边界）
     */
    private void expandAroundCenter(String s, int left, int right) {
        // 当左右指针不越界且字符相等时，继续扩展
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // 退出循环时，有效回文的边界是 [left+1, right-1]，长度为 (right-1) - (left+1) + 1 = right - left - 1
        int currentLen = right - left - 1;
        // 更新最长回文子串的信息
        if (currentLen > maxLen) {
            maxLen = currentLen;
            start = left + 1; // 有效起始位置是 left+1
        }
    }

    public static void main(String[] args) {
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        String s1 = longestPalindrome.longestPalindrome("fasfsafdfassdfs");
//        String s2 = "< . >";
//        System.out.println(s2.length()+s2);
        System.out.println(s1);
    }
}