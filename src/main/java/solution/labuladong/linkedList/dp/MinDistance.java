package solution.labuladong.linkedList.dp;

import java.util.Arrays;

public class MinDistance {
    /* 72. 编辑距离$$ */
    private int[][] memo;
    public int minDistance(String word1, String word2){
        int m = word1.length(), n = word2.length();
        memo = new int[m][n];
        for(int[] row : memo){
            Arrays.fill(row, -1);
        }
        return dp(word1, m-1, word2, n-1);
    }
    // 定义：返回 s1[0..i] 和 s2[0..j] 的最小编辑距离
    private int dp(String word1, int i, String word2, int j){
        if(i == -1) return j+1;
        if(j == -1) return i+1;
        if(memo[i][j] != -1) return memo[i][j];
        if(word1.charAt(i) == word2.charAt(j)){
            memo[i][j] = dp(word1, i-1, word2, j-1);
        } else{ // 把 word1 变成 word2，共有三种方法，取最小值：
            memo[i][j] = min(
                    dp(word1, i, word2, j-1) + 1, // 插入
                    dp(word1, i-1, word2, j) + 1, // 删除
                    dp(word1, i-1, word2, j-1) + 1 // 替换
            );
        }
        return memo[i][j];
    }

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    public int minDistance2(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        // base case
        for (int i = 1; i <= m; i++)
            dp[i][0] = i;
        for (int j = 1; j <= n; j++)
            dp[0][j] = j;
        // 自底向上求解
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = min(
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1,
                            dp[i - 1][j - 1] + 1
                    );
        // 储存着整个 s1 和 s2 的最小编辑距离
        return dp[m][n];
    }

    public static void main(String[] args) {
        MinDistance minDistance = new MinDistance();
        int res = minDistance.minDistance("horse", "ros");
        System.out.println(res);
    }
}
