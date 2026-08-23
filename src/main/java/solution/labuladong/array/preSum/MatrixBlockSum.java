package solution.labuladong.array.preSum;

import java.util.Arrays;

public class MatrixBlockSum {
    /* 1314. 矩阵区域和 */
    public int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        NumMatrix numMatrix = new NumMatrix(mat);
        int[][] res = new int[m][n];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                int x1 = Math.max(i-k, 0);
                int y1 = Math.max(j-k, 0);
                int x2 = Math.min(i+k, m-1);
                int y2 = Math.min(j+k, n-1);
                res[i][j] = numMatrix.sumRegion(x1,y1,x2,y2);
            }
        }
        return res;
    }

    public static void main(String[] args){
        MatrixBlockSum m = new MatrixBlockSum();
        int[][] mat = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(Arrays.deepToString(m.matrixBlockSum(mat, 1)));
    }
}
