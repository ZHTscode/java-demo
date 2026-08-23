package solution.labuladong.array.twoPtrArray;

import java.util.Arrays;
public class Transpose {
    /* 867. 转置矩阵 */
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] ans = new int[n][m];
        for (int i = 0; i < m; ++i  )
            for (int j = 0; j < n; ++j  ) {
                ans[j][i] = matrix[i][j];
            }
        return ans;
    }

    public static void main(String[] args) {
        Transpose s = new Transpose();
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] res = s.transpose(matrix);
        System.out.println(Arrays.deepToString(res));
    }
}
