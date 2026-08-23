package solution.labuladong.array.twoDimArray;

import java.util.LinkedList;
import java.util.List;

public class SpiralOrder {
    /* 54. 螺旋矩阵 */
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int up = 0, down = m - 1;
        int left = 0, right = n - 1;
        List<Integer> res = new LinkedList<>();

        while (true) {
            for (int i = left; i <= right; i++) {
                res.add(matrix[up][i]);
            }
            for (int i = up + 1; i <= down; i++) {
                res.add(matrix[i][right]);
            }
            if (left == right) break;
            for (int i = right - 1; i >= left; i--) {
                res.add(matrix[down][i]);
            }
            if (up == down) break;
            for (int i = down - 1; i > up; i--) {
                res.add(matrix[i][left]);
            }
            left++;
            right--;
            up++;
            down--;
        }
        return res;
    }

    public static void main(String[] args) {
        SpiralOrder spiralOrder = new SpiralOrder();
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(spiralOrder.spiralOrder(matrix));
    }
}
