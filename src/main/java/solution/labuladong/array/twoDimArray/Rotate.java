package solution.labuladong.array.twoDimArray;

public class Rotate {
    /* 48. 旋转图像 */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for(int i=0; i<n; i++){
            for(int j=i; j<n; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for(int[] row : matrix){
            reverse(row);
        }
    }
    private void reverse(int[] arr){
        int i=0, j=arr.length-1;
        while(i<j){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        Rotate rotate = new Rotate();
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        rotate.rotate(matrix);
        for(int[] row : matrix){
            for(int num : row){
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
