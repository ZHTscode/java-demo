package solution.labuladong.math;

import java.util.ArrayList;
import java.util.List;

public class GenerateNextRow {
    /* 118. 杨辉三角 */
    // 迭代方式
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows < 1) {
            return res;
        }
        // 先把第一层装进去作为 base case
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        res.add(firstRow);
        // 开始一层一层生成，装入 res
        for (int i = 2; i <= numRows; i++) {
            List<Integer> prevRow = res.get(res.size() - 1);
            res.add(generateNextRow(prevRow));
        }
        return res;
    }
    // 输入上一层的元素，生成并返回下一层的元素
    List<Integer> generateNextRow(List<Integer> prevRow) {
        List<Integer> curRow = new ArrayList<>();
        curRow.add(1);
        for (int i = 0; i < prevRow.size() - 1; i++) {
            curRow.add(prevRow.get(i) + prevRow.get(i + 1));
        }
        curRow.add(1);
        return curRow;
    }
    // 递归方式，定义：输入 numRows，返回行数为 numRows 的杨辉三角
    public List<List<Integer>> generate2(int numRows) {
        // 递归的 base case
        if (numRows == 1) {
            List<List<Integer>> triangle = new ArrayList<>();
            // 先把第一层装进去作为 base case
            List<Integer> firstRow = new ArrayList<>();
            firstRow.add(1);
            triangle.add(firstRow);
            return triangle;
        }
        // 先递归生成高度为 numRows - 1 的杨辉三角
        List<List<Integer>> triangle = generate2(numRows - 1);
        // 根据最底层元素生成一行新元素
        List<Integer> bottomRow = triangle.get(triangle.size() - 1);
        List<Integer> nextRow = generateNextRow(bottomRow);
        triangle.add(nextRow);
        return triangle;
    }
}