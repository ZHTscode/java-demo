package solution;

import java.util.ArrayDeque;
import java.util.Deque;

public class LongestValidParentheses {
    /* 32. 最长有效括号 */
    /* 解法一：栈
    核心：用栈存储下标（不是字符）
    栈底始终保持最后一个无法匹配的右括号下标（作为"断点"）
    每次遇到有效括号时，当前下标 - 栈顶下标 = 有效长度
    初始压入 -1：作为基准，方便计算从索引 0 开始的有效长度
    */
    public int longestValidParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1); // 初始化基准值
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(')               stack.push(i); // 左括号：直接入栈
            else {
                stack.pop();// 右括号：弹出栈顶
                if (stack.isEmpty())    stack.push(i); // 栈空：当前右括号无法匹配，成为新基准
                else                    maxLen = Math.max(maxLen, i - stack.peek()); // 栈不空：计算有效长度
                }
            }
        return maxLen;
    }
    /* 解法二：动态规划（最优）
    dp[i] = 以 s[i] 结尾的最长有效括号长度
    */
    public int longestValidParentheses2(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        int[] dp = new int[n];
        int maxLen = 0;
        for (int i = 1; i < n; i++) { // i 从 1 开始：dp[0] = 0
            if (s.charAt(i) == ')') {
                // 情况1: "...()"
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                }
                // 情况2: "...))" i-1, i
                else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + 2;
                    // 加上更前面的有效长度
                    if (i - dp[i - 1] - 2 >= 0) {
                        dp[i] += dp[i - dp[i - 1] - 2];
                    }
                }
                maxLen = Math.max(maxLen, dp[i]);
            }
        }
        return maxLen;
    }
    /* 解法三：双指针
    从左到右扫描：统计 ( 和 ) 数量
    从右到左扫描：处理左括号更多的情况
    当 left == right 时，更新最大长度
    */
    public int longestValidParentheses3(String s) {
        int left = 0, right = 0, maxLen = 0;
        // 从左到右
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')     left++;
            else                        right++;
            if (left == right)          maxLen = Math.max(maxLen, 2 * right);
            else if (right > left)      left = right = 0; // 重置
        }
        // 从右到左
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(')     left++;
            else                        right++;
            if (left == right)          maxLen = Math.max(maxLen, 2 * left);
            else if (left > right)      left = right = 0; // 重置
        }
        return maxLen;
    }

    public static void main(String[] args) {
        LongestValidParentheses l = new LongestValidParentheses();
        System.out.println(l.longestValidParentheses2("(()"));
    }
}
