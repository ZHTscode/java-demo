package solution.labuladong.array.twoPtrArray;

import java.util.*;

public class DiagonalSort {
    /* 1329. 将矩阵按对角线排序 */
    public int[][] findDiagonalOrder(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        Map<Integer, List<Integer>> diagonals = new HashMap<>();
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                // lambda 表达式：k->new ArrayList<>() 表示如果 k 不在哈希表中，则返回一个新创建的 ArrayList
                diagonals.computeIfAbsent(i-j, k->new ArrayList<>()).add(mat[i][j]);
            }
        }
        for(List<Integer> diagonal : diagonals.values()){
            // 对对角线元素按升序排列
            diagonal.sort(Integer::compareTo);
            // 或者使用 lambda 表达式
            // diagonal.sort((a, b) -> a - b);
            // diagonal.sort((a, b) -> a.compareTo(b));
        }
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                // 按顺序取出对角线第一个元素，然后删掉
                mat[i][j] = diagonals.get(i-j).get(0);
                diagonals.get(i-j).remove(0);
            }
        }
        return mat;
    }

    public static void main(String[] args) {
        DiagonalSort s = new DiagonalSort();
        int[][] mat = {{3,3,1,1},{2,2,1,2},{1,1,1,2}};
        int[][] res = s.findDiagonalOrder(mat);
        System.out.println(Arrays.deepToString(res));
    }
}
