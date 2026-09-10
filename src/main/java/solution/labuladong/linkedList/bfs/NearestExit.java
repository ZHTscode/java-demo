package solution.labuladong.linkedList.bfs;

import java.util.Deque;
import java.util.LinkedList;

public class NearestExit {
    /* 1926. 迷宫入口的最近出口 */
    public int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length, n = maze[0].length;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        Deque<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        q.offer(entrance);
        visited[entrance[0]][entrance[1]] = true;
        int step = 0;

        while(!q.isEmpty()) {
            int sz = q.size();
            step++;
            // 扩散当前队列中的所有节点
            for (int i = 0; i < sz; i++) {
                int[] cur = q.poll();
                // 每个节点都会尝试向上下左右四个方向扩展一步
                for (int[] dir : dirs) {
                    int x = cur[0] + dir[0];
                    int y = cur[1] + dir[1];
                    if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || maze[x][y] == '+') {
                        continue; // 跳过越界、访问过、是墙的格子
                    }
                    if (x == 0 || x == m - 1 || y == 0 || y == n - 1) {
                        return step; // 走到边界（出口）
                    }
                    visited[x][y] = true;
                    q.offer(new int[]{x, y});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        NearestExit ne = new NearestExit();
        char[][] maze = {
                {'+','+','+','+','+'},
                {'+',' ',' ',' ','+'},
                {'+',' ','+','+','+'},
                {'+',' ','+',' ','+'},
                {'+',' ','+',' ','+'}
        };
        int[] entrance = {0,1};
        System.out.println(ne.nearestExit(maze, entrance));
    }
}
