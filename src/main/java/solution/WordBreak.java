package solution;

import java.util.List;

public class WordBreak {
    // 方法一：动态规划
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1]; // dp[i] 表示 s 的前 i 个字符是否可以拆分
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    // 如果 dp[j] 为 true 且 s 的子串 s[j..i-1] 在字典中，则 dp[i] 为 true
                    dp[i] = true;
                    break; // 提前退出内层循环
                }
            }
        }
        return dp[s.length()];
    }

    // 方法二：备忘录递归
    private static Boolean[] memo;
    // 使用 Boolean 数组做备忘录，memo[i] 表示从索引 i 开始的子串是否可以拆分
    public boolean wordBreak2(String s, List<String> wordDict) {
        memo = new Boolean[s.length()];
        return existPrefix(s, 0, wordDict);
    }
    private boolean existPrefix(String s, int start, List<String> wordDict) { // 存在前缀
        // 终止条件：起始位置达到末尾
        if (start == s.length()) return true;
        // 检查备忘录，如果已经计算过则直接返回结果
        if (memo[start] != null) return memo[start];
        for (String word : wordDict) {
            // 使用 startsWith 的重载方法，避免 substring 产生大量新字符串对象
            if (s.startsWith(word, start)) { //  s 从 start 位置开始，是否以 word 开头
                if (existPrefix(s, start + word.length(), wordDict)) {
                    // 如果从 start + word.length() 位置开始的子串还可以拆分，则返回 true
                    return memo[start] = true;
                }
            }
        }
        return memo[start] = false;
    }

    public static void main(String[] args) {
        WordBreak s = new WordBreak();
//        System.out.println(s.wordBreak2("leetcode", List.of("leet", "code")));
        System.out.println(s.wordBreak2("catsanddog", List.of("cats", "dog", "sand", "and", "cat")));
        for (int i = 0; i < memo.length; i++) {
            System.out.print(memo[i] + " ");
        }
    }
}
