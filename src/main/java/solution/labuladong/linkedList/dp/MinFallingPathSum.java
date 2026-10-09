package solution.labuladong.linkedList.dp;

import java.util.Arrays;

public class MinFallingPathSum {
    /* 931. 下降路径最小和 */
    private int[][] memo;
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length; // 矩阵的行数
        int res = Integer.MAX_VALUE;
        memo = new int[n][n];
        for(int i = 0; i < n; i++){
            Arrays.fill(memo[i], 66666);
        }
        for(int i = 0; i < n; i++){
            // 最后一行的最小值
            res = Math.min(res, dp(matrix, n - 1, i));
        }
        return res;
    }
    private int dp (int[][] matrix, int i, int j){
        if(i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length) return 99999;
        if(i == 0) return matrix[0][j];
        if(memo[i][j] != 66666) return memo[i][j];
        memo[i][j] = matrix[i][j] + Math.min(
                dp(matrix, i - 1, j - 1),
                Math.min(dp(matrix, i - 1, j), dp(matrix, i - 1, j + 1))
        );
        return memo[i][j];
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {2,1,3},
            {6,5,4},
            {7,8,9}
        };
        MinFallingPathSum minFallingPathSum = new MinFallingPathSum();
        System.out.println(minFallingPathSum.minFallingPathSum(matrix));
    }
}
