package solution.labuladong.linkedList.backTrack;

public class UniquePathsIII {
    /* 980. 不同路径 III */
    int res;
    boolean[][] visited;
    int visitedCount;
    int totalCount;
    int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int uniquePathsIII(int[][] grid) {
        res = 0;
        int m = grid.length, n = grid[0].length;
        visited = new boolean[m][n];
        visitedCount = 0; // 已访问的格子数
        totalCount = 0; // 总格子数
        int startI = 0, startJ = 0; // 起点的行下标、列下标
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) { // 找到起点
                    startI = i;
                    startJ = j;
                }
                if (grid[i][j] == 1 || grid[i][j] == 0) { // 跳过障碍物和终点
                    totalCount++;
                }
            }
        }
        dfs(grid, startI, startJ);
        return res;
    }
    private void dfs(int[][] grid, int i, int j) {
        int m = grid.length, n = grid[0].length;
        // 剪枝，索引越界
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return;
        }
        // 剪枝，跳过起点、障碍物、已访问的格子
        if (grid[i][j] == -1 || visited[i][j]) {
            return;
        }
        // 到达终点
        if (grid[i][j] == 2) {
            if (visitedCount == totalCount) { // 到达终点且所有格子都访问过了
                res++;
            }
            return;
        }
        visited[i][j] = true;
        visitedCount++;
        for (int[] dir : dirs) { // 枚举下一步往哪个邻居走
            dfs(grid, i + dir[0], j + dir[1]); // 遍历所有可能的下一个起点
        }
        visited[i][j] = false;
        visitedCount--;
    }

    public static void main(String[] args) {
        UniquePathsIII s = new UniquePathsIII();
        int[][] grid = new int[][]{
                {1, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, -1}
        };
        int res = s.uniquePathsIII(grid);
        System.out.println(res);
    }
}