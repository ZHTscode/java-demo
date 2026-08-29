package solution.labuladong.array.binarySearch;

public class SearchMatrix {
    /* 74. 搜索二维矩阵 */
    public boolean searchMatrix(int[][] matrix, int target){
        int m = matrix.length, n = matrix[0].length;
        int left = 0, right = m * n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (matrix[mid / n][mid % n] == target) {
                return true;
            } else if (matrix[mid / n][mid % n] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SearchMatrix solution = new SearchMatrix();
        int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        int target = 4;
        System.out.println(solution.searchMatrix(matrix, target));
    }
}
