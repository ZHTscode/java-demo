package solution;

public class IsMatch {
    /* 10.正则表达式匹配 */
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        // (i,j): s 的前 i 个字符和 p 的前 j 个字符是否匹配
        // 对应 s[0...i-1] 和 p[0...j-1]
        boolean[][] dp = new boolean[m + 1][n + 1]; // 默认初始化为 false
        // 1. 初始化：空串匹配空串
        dp[0][0] = true;
        // 2. 初始化：s 为空，p 可能匹配（如 "a*", "a*b*" 等）
        for (int j = 2; j <= n; j += 2) {
            if (p.charAt(j - 1) == '*') // 如果偶数位是 *
                dp[0][j] = dp[0][j - 2]; // '*' 匹配 0 次前面的字符（直接跳过）
        }
        // 3. 填充 DP 表
        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) != '*') { // p 的第 j 个字符是普通字符 OR '.'
                    if (i > 0 && matches(s, p, i, j))
                        dp[i][j] = dp[i - 1][j - 1]; // 当前字符匹配成功，更新(i,j)
                }
                else { // p 的第 j 个字符是 *
                    // * 匹配零次
                    dp[i][j] = dp[i][j - 2]; // 忽略 p[j-2] 和 p[j-1]（直接跳过）
                    // * 匹配多次
                    if (i > 0 && matches(s, p, i, j - 1)) // s 的第 i 个字符匹配 p 的第 j-1 个字符
                        // 当前状态 = 匹配0次的结果 OR 匹配多次的结果
                        // s 少一个字符，模式不变（* 继续用，排除 aaa a* 的情况，否则最左边的 a 就无法匹配了）
                        dp[i][j] = dp[i][j] || dp[i - 1][j]; // 或运算，更新(i,j)
                }
            }
        }
        return dp[m][n];
    }
    // 判断 s[i-1] 和 p[j-1] 是否匹配
    private boolean matches(String s, String p, int i, int j) {
        if (i == 0) return false; // s 为空串
        char pc = p.charAt(j - 1);
        if (pc == '.') return true; // p 的第 j 个字符为'.'
        return pc == s.charAt(i - 1); // s 的第 i 个字符和 p 的第 j 个字符相同
    }

    public static void main(String[] args) {
        IsMatch im = new IsMatch();
        System.out.println(im.isMatch("abccc", ".*"));
        /*
        dp[5][2] ("abccc" vs ".*")  < c 匹配 . >
            ↓ 依赖 dp[4][2]
        dp[4][2] ("abcc" vs ".*")  < c 匹配 . >
            ↓ 依赖 dp[3][2]
        dp[3][2] ("abc" vs ".*")  < c 匹配 . >
            ↓ 依赖 dp[2][2]
        dp[2][2] ("ab" vs ".*")  < b 匹配 . >
            ↓ 依赖 dp[1][2]
        dp[1][2] ("a" vs ".*")  < a 匹配 . >
            ↓ 依赖 dp[0][2]
        dp[0][2] ("" vs ".*") = true  < 基础情况（'*' 匹配 0 次）>
        */
        System.out.println(im.isMatch("ccc", ".c."));
    }
}