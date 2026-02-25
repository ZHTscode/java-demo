package solution;

public class MaximalSquare {
    /* 221. 最大正方形 */
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length; // 获取矩阵的行数
        int n = matrix[0].length; // 获取矩阵的列数
        int[][] dp = new int[m + 1][n + 1]; // 创建dp数组
        int maxSide = 0; // 记录最大边长
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    // 状态转移方程
                    // dp[i][j] 表示以 (i-1, j-1) 为右下角的正方形的最大边长。
                    // 只有当 matrix[i-1][j-1] 为 '1' 时，它能构成的正方形边长
                    // 取决于其上方、左方和左上方三个位置能构成的正方形边长的最小值 + 1（画图就懂了）
                    /*
                    (i-1, j-1) (i-1, j)
                     (i, j-1)   (i, j)
                    */
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        return maxSide * maxSide;
    }

    public static void main(String[] args) {
        MaximalSquare ms = new MaximalSquare();
        char[][] matrix = {
                {'1', '0', '1', '0', '0', '1', '1', '0'},
                {'1', '0', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0', '1', '1', '0'},
                {'1', '0', '1', '1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '0', '1', '1', '1', '1', '1', '1'},
                {'0', '0', '1', '1', '1', '1', '1', '1'}
        };
        int result = ms.maximalSquare(matrix);
        System.out.println(result);
    }
}
