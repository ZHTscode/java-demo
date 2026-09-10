package solution.labuladong.linkedList.bfs;

import java.util.Deque;
import java.util.LinkedList;

public class ShortestPathBinaryMatrix {
    /* 1091. 二进制矩阵中的最短路径 */
    public int shortestPathBinaryMatrix(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (grid[0][0] == 1 || grid[m-1][n-1] == 1) return -1;
        Deque<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];

        q.offer(new int[]{0, 0});
        visited[0][0] = true;
        int step = 1;

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1}};

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                int x = cur[0], y = cur[1];
                if (x == m-1 && y == n-1) return step;
                for (int[] dir : dirs) {
                    int newX = x + dir[0];
                    int newY = y + dir[1];
                    if (newX >= 0 && newX < m && newY >= 0 && newY < n
                            && !visited[newX][newY] && grid[newX][newY] == 0) {
                        q.offer(new int[]{newX, newY});
                        visited[newX][newY] = true;
                    }
                }
            }
            step++;
        }
        return -1;
    }

    public static void main(String[] args) {
        ShortestPathBinaryMatrix sp = new ShortestPathBinaryMatrix();
        int[][] grid = {{0,1},{1,0}};
        System.out.println(sp.shortestPathBinaryMatrix(grid));
    }
}
