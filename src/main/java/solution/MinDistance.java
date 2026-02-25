package solution;

public class MinDistance {
    /* 72. 编辑距离
       核心：动态规划 */
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1]; // 定义(i,j)：w1 前 i 个字符和 w2 前 j 个字符的编辑距离
        for (int i = 1; i <= m; i++) dp[i][0] = i; // 初始化(i,0)：w1 前 i 个字符和 w2 空串的编辑距离为 i
        for (int j = 1; j <= n; j++) dp[0][j] = j; // 初始化(0,j)：w1 空串和 w2 前 j 个字符的编辑距离为 j
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) { // 当前字符相等
                    dp[i][j] = dp[i - 1][j - 1]; // 直接继承之前结果
                }
                else { // 当前字符不相等
                    dp[i][j] = Math.min(
                            Math.min(dp[i - 1][j], // 删除：删掉 w1 的第 i 个字符，剩下 i-1 去匹配 j
                                    dp[i][j - 1]), // 插入：在 w1 末尾插入 w2 的第 j 个字符，用 i 去匹配 j-1
                            dp[i - 1][j - 1]       // 替换：把 w1 的第 i 个字符替换成 w2 的第 j 个，用 i-1 匹配 j-1
                    ) + 1; // 从三种操作中选代价最小的 + 1
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        MinDistance m = new MinDistance();
        System.out.println(m.minDistance("horse", "ros"));
    }
}
