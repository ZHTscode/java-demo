package solution;

public class NumIslands {
    /* 200. 岛屿数量 */
    public int numIslands(char[][] grid) {
        int count = 0;
        System.out.println(grid.length);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++; // 增加岛屿计数
                    dfs2(i, j, grid); // 深度优先搜索，将岛屿标记为已访问
                }
            }
        }
        return count;
    }
    private void dfs(int i, int j, char[][] grid) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '2'; // 标记为已访问
        dfs(i + 1, j, grid);
        dfs(i - 1, j, grid);
        dfs(i, j + 1, grid);
        dfs(i, j - 1, grid);
    }
    public void dfs2(int i, int j, char[][] grid) {
        grid[i][j] = '0'; // 递归核心
        if (i > 0 && grid[i - 1][j] == '1') {
            dfs2(i - 1, j, grid);
        }
        if (j < grid[0].length - 1 && grid[i][j + 1] == '1') {
            dfs2(i, j + 1, grid);
        }
        if (i < grid.length - 1 && grid[i + 1][j] == '1') {
            dfs2(i + 1, j, grid);
        }
        if (j > 0 && grid[i][j - 1] == '1') {
            dfs2(i, j - 1, grid);
        }
    }

    public static void main(String[] args) {
        NumIslands numIslands = new NumIslands();
        char[][] grid = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','1'},
                {'0','0','0','1','0'}};
        System.out.println(numIslands.numIslands(grid));
        // 打印grid
        for (char[] row : grid) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
