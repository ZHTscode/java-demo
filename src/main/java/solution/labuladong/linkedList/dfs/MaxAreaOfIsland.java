package solution.labuladong.linkedList.dfs;

public class MaxAreaOfIsland {
    /* 695. 岛屿的最大面积 */
    int area; // 当前岛屿面积
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int max = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    area = 0; // 每次新岛屿，重置面积
                    dfs(grid, i, j);
                    if(area > max){
                        max = area; // 更新最大面积
                    }
                }
            }
        }
        return max;
    }
    private void dfs(int[][] grid, int i, int j){
        int m = grid.length;
        int n = grid[0].length;
        if(i < 0 || i >= m || j <0 || j >=n || grid[i][j] == 0){
            return; // 越界或不是陆地，直接return
        }
        grid[i][j] = 0; // 标记已访问，淹掉
        area++;
        // 四个方向继续遍历
        dfs(grid, i-1, j);
        dfs(grid, i+1, j);
        dfs(grid, i, j-1);
        dfs(grid, i, j+1);
    }

    public static void main(String[] args) {
        MaxAreaOfIsland solution = new MaxAreaOfIsland();
        int[][] grid = {
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,1,0,1,1},
                {0,0,0,1,1}
        };
        System.out.println(solution.maxAreaOfIsland(grid));
    }

}
