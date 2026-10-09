package solution.labuladong.linkedList.dp;

import java.util.ArrayDeque;

public class LongestValidParentheses {
    /* 32. 最长有效括号 */
    public int longestValidParentheses(String s) {
        java.util.Deque<Integer> stk = new ArrayDeque<>();
        // 定义：以第 i 个字符结尾的最长合法括号子串长度
        int[] dp = new int[s.length() + 1];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stk.push(i);
                // 左括号不可能是合法括号子串的结尾
                dp[i + 1] = 0;
            } else { // 遇到右括号
                if (!stk.isEmpty()) {
                    // 配对的左括号对应索引
                    int leftIndex = stk.pop();
                    // 以这个右括号结尾的最长子串长度
                    dp[i + 1] = (i - leftIndex + 1) + dp[leftIndex];
                } else {
                    // 没有配对的左括号
                    dp[i + 1] = 0;
                }
            }
        }
        // 计算最长子串的长度
        int res = 0;
        for (int len : dp) {
            res = Math.max(res, len);
        }
        return res;
    }

    public static void main(String[] args) {
        LongestValidParentheses solution = new LongestValidParentheses();
        String s = "(()";
        System.out.println(solution.longestValidParentheses(s));
    }
}
