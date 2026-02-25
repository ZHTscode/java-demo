package solution;

public class Exist {
    /* 79.单词搜索 */
    private static final int[][] DIRS = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}}; // 上下左右
    public boolean exist(char[][] board, String word) {
        /* 字符频率预检查（快速剪枝）：
           统计 board 中每个字符的出现次数
           word 中某个字符的需求量超过 board 中的数量 → 直接返回 false */
        int[] cnt = new int[128]; // ASCII字符集大小，覆盖所有可能的字符
        for(char[] row : board){ // 行
            for(char c : row){ // 列
                cnt[c]++; // 统计字符出现次数
            }
        }
        char[] w = word.toCharArray(); // 字符串数组
        int[] wordCnt = new int[128];
        for (char c : w) {
            if (++wordCnt[c] > cnt[c])  return false; // word 中某个字符的需求量超过 board 中的数量
        }
        /* 反向搜索优化（减少分支）
           比较 word 首尾字符在 board 中的出现次数
           从出现次数少的那端开始搜索 → 搜索树更小 */
        if (cnt[w[w.length - 1]] < cnt[w[0]]) {
            w = new StringBuilder(word).reverse().toString().toCharArray(); // 反转字符串
        }
        // 搜索
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(dfs(i,j,0,board,w))  return true;
            }
        }
        return false;
    }
    private boolean dfs(int i, int j, int k, char[][] board, char[] word){
        /* 给定一个坐标，搜索四个方向
           &&  中的 false → 短路，不执行后面的 dfs
           dfs 返回 false → 继续 for 循环的下一个方向
           dfs 返回  true → 立即 return true，所有上层都返回 */
        if(board[i][j] != word[k]) return false; // 1. 字符不匹配 → 剪枝
        if(k == word.length -1) return true; // 2. 匹配完整个单词 → 成功
        board[i][j] = 0; // 3. 标记已访问（char 类型默认为 0，字母的 ASCII 码都 ≥ 65（'A'））
        // 4. 搜索四个方向
        for (int[] d : DIRS) { // 遍历 DIRS 的每一行
            int x = i + d[0]; // 第一列（左右）
            int y = j + d[1]; // 第二列（上下）
            if (0 <= x && x < board.length && 0 <= y && y < board[x].length // 1. 在矩阵范围内
                    && dfs(x, y, k + 1, board, word)) // 2. 搜索相邻格子
                return true; // 搜到，立即返回（题目只需判断存在）
        }
        board[i][j] = word[k]; // 恢复现场
        return false;
    }

    public static void main(String[] args) {
        Exist exist = new Exist();
        char[][] board = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        String word = "ABCCED";
        System.out.println(exist.exist(board, word));
    }
}
