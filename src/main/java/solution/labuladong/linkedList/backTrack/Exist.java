package solution.labuladong.linkedList.backTrack;

public class Exist {
    /* 79. 单词搜索 */
    boolean found;
    boolean[][] used;
    public boolean exist(char[][] board, String word) {
        found = false;
        int m = board.length, n = board[0].length;
        used = new boolean[m][n];
        // 遍历 board 的每个位置，尝试从 (i, j) 开始匹配 word
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, word, 0);
                if (found) return true;
            }
        }
        return false;
    }
    // 从 (i, j) 开始向四周搜索，试图匹配 word[p..]
    private void dfs(char[][] board, int i, int j, String word, int p) {
        if (p == word.length()) { // 整个 word 已经被匹配完，找到了一个答案
            found = true;
            return;
        }
        if (found) return; // 已找到一个答案，停止搜索
        int m = board.length, n = board[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n) { // 越界
            return;
        }
        if (board[i][j] != word.charAt(p)) { // 当前字符不匹配
            return;
        }
        // 已经匹配过的字符，添一个负号作为标记，避免走回头路
        used[i][j] = true;
        // word[p] 被 board[i][j] 匹配，开始向四周搜索 word[p+1..]
        dfs(board, i + 1, j, word, p + 1);
        dfs(board, i, j + 1, word, p + 1);
        dfs(board, i - 1, j, word, p + 1);
        dfs(board, i, j - 1, word, p + 1);
        used[i][j] = false;
    }

    public static void main(String[] args) {
        char[][] board = {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };
        String word = "ABCCED";
        Exist solution = new Exist();
        boolean result = solution.exist(board, word);
        System.out.println(result); // 输出: true
    }
}
