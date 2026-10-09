package solution.labuladong.linkedList.dp;

import java.util.Arrays;

public class LongestCommonSubsequence {
    /* 1143. 最长公共子序列 */
    private int[][] memo;
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        memo = new int[m][n];
        for(int[] row : memo){
            Arrays.fill(row, -1);
        }
        return dp(text1, 0, text2, 0);
    }
    // 定义：返回 s1[i..] 和 s2[j..] 的最长公共子序列的长度
    private int dp(String s1, int i, String s2, int j){
        if(i == s1.length() || j == s2.length()) return 0;
        if(memo[i][j] != -1) return memo[i][j];
        if(s1.charAt(i) == s2.charAt(j)){
            return memo[i][j] = 1 + dp(s1, i+1, s2, j+1);
        } else {
            return memo[i][j] = Math.max(dp(s1, i+1, s2, j), dp(s1, i, s2, j+1));
        }
    }

    public int longestCommonSubsequence2(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        // 定义：s1[0..i-1] 和 s2[0..j-1] 的 lcs 长度为 dp[i][j]
        int[][] dp = new int[m + 1][n + 1];
        // 目标：s1[0..m-1] 和 s2[0..n-1] 的 lcs 长度，即 dp[m][n]
        // base case: dp[0][..] = dp[..][0] = 0
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 现在 i 和 j 从 1 开始，所以要减一
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    // s1[i-1] 和 s2[j-1] 必然在 lcs 中
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    // s1[i-1] 和 s2[j-1] 至少有一个不在 lcs 中
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        String text1 = "abcde", text2 = "ace";
        System.out.println(lcs.longestCommonSubsequence(text1, text2));
    }
}
