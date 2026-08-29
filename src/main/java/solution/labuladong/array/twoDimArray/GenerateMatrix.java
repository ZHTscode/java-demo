package solution.labuladong.array.twoDimArray;

public class GenerateMatrix {
    /* 59. 螺旋矩阵II */
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int up = 0, down = n-1;
        int left = 0, right = n-1;
        int num = 1;
        while(true){
            for(int i=left; i<=right; i++){
                res[up][i] = num;
                num++;
            }
            up++;
            if(up > down) break;

            for(int i=up; i<=down; i++){
                res[i][right] = num;
                num++;
            }
            right--;
            if(right < left) break;

            for(int i=right; i>=left; i--){
                res[down][i] = num;
                num++;
            }
            down--;
            if(down < up) break;

            for(int i=down; i>=up; i--){
                res[i][left] = num;
                num++;
            }
            left++;
            if(left > right) break;
        }
        return res;
    }

    public static void main(String[] args) {
        GenerateMatrix generateMatrix = new GenerateMatrix();
        int[][] res = generateMatrix.generateMatrix(4);
        for(int[] row: res){
            for(int num: row){
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
