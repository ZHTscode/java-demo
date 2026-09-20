package solution.labuladong.linkedList.dfs;

import java.util.HashSet;
import java.util.Set;

public class ClosedIsland {
    /* 1254. 统计封闭岛屿的数目 */
    public int closedIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int j = 0; j < n; j++) {
            dfs(grid, 0, j); // 把靠上边的岛屿淹掉
            dfs(grid, m - 1, j); // 把靠下边的岛屿淹掉
        }
        for (int i = 0; i < m; i++) {
            dfs(grid, i, 0); // 把靠左边的岛屿淹掉
            dfs(grid, i, n - 1); // 把靠右边的岛屿淹掉
        }
        // 遍历 grid，剩下的岛屿都是封闭岛屿
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    res++;
                    dfs(grid, i, j);
                }
            }
        }
        return res;
    }
    // 从 (i, j) 开始，将与之相邻的陆地都变成海水
    private void dfs(int[][] grid, int i, int j) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == 1) {
            return; // 越界或不是陆地，直接return
        }
        // 淹没 (i, j)
        grid[i][j] = 1;
        // 淹没上下左右的陆地
        dfs(grid, i + 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);
    }

    public static void main(String[] args) {
        ClosedIsland closedIsland = new ClosedIsland();
        int[][] grid = {
                {1,1,1,1,1,1,1,0},
                {1,0,0,0,0,1,1,0},
                {1,0,1,0,1,1,1,0},
                {1,0,0,0,0,1,0,1},
                {1,1,1,1,1,1,1,0}
        };
        System.out.println(closedIsland.closedIsland(grid));
    }
}
