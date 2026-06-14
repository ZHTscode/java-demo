package solution;

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

    /* 解法二：单调栈（最优） */
    public int maximalRectangle2(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int cols = matrix[0].length;
        int[] heights = new int[cols];
        int maxArea = 0;

        // 逐行遍历，构建每一层柱状图
        for (char[] row : matrix) {
            for (int j = 0; j < cols; j++) {
                if (row[j] == '1') {
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }
            // 计算当前柱状图最大矩形
            maxArea = Math.max(maxArea, LargestRectangleArea.largestRectangleArea2(heights));
        }
        return maxArea;
    }

    public static void main(String[] args) {
        MaximalRectangle m = new MaximalRectangle();
        char[][] matrix = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
        System.out.println(m.maximalRectangle2(matrix));
    }
}
