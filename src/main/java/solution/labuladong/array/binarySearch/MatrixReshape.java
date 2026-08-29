package solution.labuladong.array.binarySearch;

public class MatrixReshape {
    /* 566. 重塑矩阵 */
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length, n = mat[0].length;
        if (m * n != r * c) {
            return mat;
        }
        int[][] res = new int[r][c];
        for (int x = 0; x < m * n; x++) {
            res[x / c][x % c] = mat[x / n][x % n];
        }
        return res;
    }

    public static void main(String[] args) {
        MatrixReshape solution = new MatrixReshape();
        int[][] mat = {{1,2},{3,4}};
        int r = 1, c = 4;
        int[][] reshapedMat = solution.matrixReshape(mat, r, c);
        for (int[] row : reshapedMat) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
