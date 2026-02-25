package solution;

public class SearchMatrix {
    /* 74.搜索二维矩阵 */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        // 从右上角开始搜索
        int row = 0;
        int col = matrix[0].length - 1;
//        System.out.println(matrix[row][col]);
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) return true;
            else if (matrix[row][col] > target) col--; // target在左半边
            else row++; // target在下半边
        }
        return false;
    }
    public static void main(String[] args) {
        SearchMatrix sm = new SearchMatrix();
        int[][] matrix = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        int target = 5;
        System.out.println(sm.searchMatrix(matrix, target));
    }
}
