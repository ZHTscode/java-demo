package solution;

import java.util.Arrays;

public class Rotate {
    /*
    位置	     坐标	                说明
    A	    (i, j)	            当前处理的元素
    B	    (n-1-j, i)	        A 旋转 90° 后的位置
    C	    (n-1-i, n-1-j)	    B 旋转 90° 后的位置
    D	    (j, n-1-i)	        C 旋转 90° 后的位置
    构成循环：A → D → C → B → A
    */
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            // 处理每一层（从外到内按圈处理）
            for (int j = i; j < n - 1 - i; j++) { // 四个角情况特殊：(n-i)-1
                // 处理当前层的每个四元组
                int temp = matrix[i][j]; // 保存 A
                matrix[i][j] = matrix[n - 1 - j][i]; // B → A
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j]; // C → B
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i]; // D → C
                matrix[j][n - 1 - i] = temp; // A → D
                System.out.println(Arrays.deepToString(matrix));
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        rotate(matrix);
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
