package solution.labuladong.linkedList.backTrack;

import java.util.*;

public class SolveSudoku {
    /* 37. 解数独$$ */
    // 标记是否已经找到可行解
    private boolean found;
    // 记录每行已经出现的数字，如 rows[0] = {1, 2, 3} 表示第 0 行已经出现了数字 1, 2, 3
    private List<Set<Character>> rows;
    // 记录每列已经出现的数字
    private List<Set<Character>> cols;
    // 记录每个九宫格已经出现的数字
    private List<Set<Character>> boxes;

    public void solveSudoku(char[][] board) {
        found = false;
        rows = new ArrayList<>(9);
        cols = new ArrayList<>(9);
        boxes = new ArrayList<>(9);
        for (int i = 0; i < 9; i++) {
            rows.add(new HashSet<>());
            cols.add(new HashSet<>());
            boxes.add(new HashSet<>());
        }
        // 将预设数字加入集合
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    rows.get(i).add(board[i][j]);
                    cols.get(j).add(board[i][j]);
                    boxes.get(getBoxIndex(i, j)).add(board[i][j]);
                }
            }
        }
        backtrack(board, 0);
    }

    // 路径：board 中小于 index 的位置所填的数字
    // 选择列表：数字 1~9
    // 结束条件：整个 board 都填满数字
    private void backtrack(char[][] board, int index) {
        if (found) return; // 找到一个可行解，立即结束
        int m = 9, n = 9;
        int i = index / n;
        int j = index % n;
        if (index == m * n) {
            found = true; // 找到一个可行解
            return;
        }
        if (board[i][j] != '.') {
            // 有预设数字，不用穷举
            backtrack(board, index + 1);
            return;
        }
        for (char ch = '1'; ch <= '9'; ch++) {
            // 剪枝：遇到不合法的数字，跳过
            if (!isValid(board, i, j, ch)) continue;
            // 做选择，把 ch 填入 board[i][j]
            board[i][j] = ch;
            rows.get(i).add(ch);
            cols.get(j).add(ch);
            boxes.get(getBoxIndex(i, j)).add(ch);
            backtrack(board, index + 1);
            if (found) {
                // 找到一个可行解，立即结束，不要撤销选择，否则 board[i][j] 会被重置
                return;
            }
            // 撤销选择，把 board[i][j] 重置为 '.'
            board[i][j] = '.';
            rows.get(i).remove(ch);
            cols.get(j).remove(ch);
            boxes.get(getBoxIndex(i, j)).remove(ch);
        }
    }

    // 获取 (r, c) 所在的九宫格编号
    private int getBoxIndex(int r, int c) {
        return (r / 3) * 3 + (c / 3);
    }

    // 判断是否可以在 (r, c) 放置数字 num
    private boolean isValid(char[][] board, int r, int c, char num) {
        // 只需查询三次哈希表即可
        if (rows.get(r).contains(num)) return false;
        if (cols.get(c).contains(num)) return false;
        if (boxes.get(getBoxIndex(r, c)).contains(num)) return false;
        return true;
    }

    public static void main(String[] args) {
        SolveSudoku solution = new SolveSudoku();
        char[][] board = {
                {'.', '.', '9', '7', '4', '8', '.', '.', '.'},
                {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '2', '.', '1', '.', '9', '.', '.', '.'},
                {'.', '.', '7', '.', '.', '.', '2', '4', '.'},
                {'.', '6', '4', '.', '1', '.', '5', '9', '.'},
                {'.', '9', '8', '.', '.', '.', '3', '.', '.'},
                {'.', '.', '.', '8', '.', '3', '.', '2', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '6'},
                {'.', '.', '.', '2', '7', '5', '9', '.', '.'}
        };
        solution.solveSudoku(board);
        for (char[] row : board) {
            for (char c : row) System.out.print(c + " ");
            System.out.println();
        }
    }
}