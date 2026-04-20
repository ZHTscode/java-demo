package solution;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaximalRectangle {
    /* 85. 最大矩形 */
    /* 解法一：使用柱状图的优化暴力解法 */
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] left = new int[row][col]; // left[i][j]：(i,j)左边连续 1 的个数
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1')
                    left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
            }
        }
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') {
                    int width = left[i][j]; // 此行宽度
                    for (int k = i; k >= 0; k--) { // 从此行往上，找最小宽度
                        width = Math.min(width, left[k][j]);
                        res = Math.max(res, width * (i - k + 1));
                    }
                }
            }
        }
        return res;
    }
    /* 解法二：单调栈 */
    public int maximalRectangle2(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        int[][] left = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1')
                    left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
            }
        }
        int res = 0;
        for (int j = 0; j < n; j++) { // 对于每一列，使用基于柱状图的方法
            int[] up = new int[m];
            int[] down = new int[m];
            Deque<Integer> stack = new ArrayDeque<Integer>();
            for (int i = 0; i < m; i++) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    stack.pop();
                }
                up[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            }
            stack.clear(); // 清空栈，为计算down数组做准备
            for (int i = m - 1; i >= 0; i--) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    stack.pop();
                }
                down[i] = stack.isEmpty() ? m : stack.peek();
                stack.push(i);
            }
            for (int i = 0; i < m; i++) {
                int height = down[i] - up[i] - 1;
                int area = height * left[i][j];
                res = Math.max(res, area);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MaximalRectangle m = new MaximalRectangle();
        char[][] matrix = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
        System.out.println(m.maximalRectangle(matrix));
    }
}
