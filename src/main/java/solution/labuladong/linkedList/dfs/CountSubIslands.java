package solution.labuladong.linkedList.dfs;

public class CountSubIslands {
    /* 1905. 统计子岛屿 */
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length, n = grid1[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid1[i][j] == 0 && grid2[i][j] == 1) {
                    // 这个岛屿肯定不是子岛，淹掉
                    dfs(grid2, i, j);
                }
            }
        }
        // 现在 grid2 中剩下的岛屿都是子岛，计算岛屿数量
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == 1) {
                    res++;
                    dfs(grid2, i, j);
                }
            }
        }
        return res;
    }

    // 从 (i, j) 开始，将与之相邻的陆地都变成海水
    void dfs(int[][] grid, int i, int j) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) {
            return; // 越界或不是陆地，直接return
        }
        grid[i][j] = 0;
        dfs(grid, i + 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);
    }

    public static void main(String[] args) {
        CountSubIslands solution = new CountSubIslands();
        int[][] grid1 = {
                {1,0,1,0,0},
                {0,1,0,1,1},
                {0,0,1,0,1},
                {1,0,0,0,0},
                {1,1,0,1,1}
        };
        int[][] grid2 = {
                {1,0,0,0,0},
                {0,1,1,0,1},
                {0,0,0,1,1},
                {1,0,0,0,0},
                {1,1,0,1,1}
        };
        System.out.println(solution.countSubIslands(grid1, grid2));
    }
}
