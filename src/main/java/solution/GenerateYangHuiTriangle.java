package solution;

import java.util.ArrayList;
import java.util.List;

public class GenerateYangHuiTriangle {
    /* 118. 杨辉三角 */
    /*       1
     *      1 1
     *     1 2 1
     *    1 3 3 1
     *   1 4 6 4 1 */
    public List<List<Integer>> generateYangHuiTriangle(int numRows) {
        List<List<Integer>> result = new ArrayList<>(numRows);
        // 1. 遍历每一行
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>(i+1);
            // 2. 遍历当前行的每一列 (第 i 行有 i+1 个元素)
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) row.add(1); // 情况 A: 第一个或最后一个元素 -> 直接填 1
                else { // 情况 B: 中间元素 -> 等于上一行两数之和
                    List<Integer> prevRow = result.get(i - 1); // 获取上一行
                    int val = prevRow.get(j - 1) + prevRow.get(j); // 计算：左上 (j-1) + 正上 (j)
                    row.add(val);
                }
            }
            // 3. 将构建好的当前行加入结果集
            result.add(row);
        }
        return result;
    }

    public static void main(String[] args) {
        GenerateYangHuiTriangle g = new GenerateYangHuiTriangle();
        System.out.println(g.generateYangHuiTriangle(5));
    }
}
