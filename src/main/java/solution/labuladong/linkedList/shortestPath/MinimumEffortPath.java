package solution.labuladong.linkedList.shortestPath;

import java.util.*;

public class MinimumEffortPath {
    /* 1631. 最小体力消耗路径 */
    public int minimumEffortPath(int[][] heights) {
        // 计算 (0, 0) 到 (m - 1, n - 1) 的最小体力消耗
        return dijkstra(heights);
    }
    int dijkstra(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        // 记录从起点 (0, 0) 到每个节点的最小体力消耗
        int[][] distTo = new int[m][n];
        for (int[] row : distTo) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        Queue<State> pq = new PriorityQueue<>((a, b) -> {
            return a.effortFromStart - b.effortFromStart;
        });

        // 从起点 (0, 0) 开始进行 dijkstra 算法
        pq.offer(new State(0, 0, 0));
        distTo[0][0] = 0;

        while (!pq.isEmpty()) {
            State state = pq.poll();
            int curRow = state.row;
            int curCol = state.col;
            int curEffortFromStart = state.effortFromStart;

            // 已经存在更优路径，则跳过
            if (curEffortFromStart > distTo[curRow][curCol]) continue;
            // 判断是否已经到达目标点
            if (curRow == m - 1 && curCol == n - 1) return distTo[curRow][curCol];

            for (int[] neighbor : adj(matrix, curRow, curCol)) {
                // 获得相邻节点的行列值
                int nextRow = neighbor[0];
                int nextCol = neighbor[1];
                // 计算从起点 (0, 0) 到相邻节点的体力消耗
                int nextEffortFromStart = Math.max(
                        curEffortFromStart, Math.abs(matrix[nextRow][nextCol] - matrix[curRow][curCol])
                );
                if (nextEffortFromStart >= distTo[nextRow][nextCol]) continue; // 不能使路径更短
                pq.offer(new State(nextRow, nextCol, nextEffortFromStart));
                distTo[nextRow][nextCol] = nextEffortFromStart;
            }
        }
        return -1;
    }

    // 返回上下左右相邻坐标
    List<int[]> adj(int[][] matrix, int x, int y) {
        // 方向数组，上下左右的坐标偏移量
        int[][] dirs = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        int m = matrix.length, n = matrix[0].length;
        // 存储相邻节点
        List<int[]> neighbors = new ArrayList<>();
        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= m || nx < 0 || ny >= n || ny < 0) {
                // 索引越界
                continue;
            }
            neighbors.add(new int[] { nx, ny });
        }
        return neighbors;
    }

    // 记录当前位置和从起点到当前位置的最小体力消耗
    static class State {
        int row;
        int col;
        int effortFromStart;
        public State(int row, int col, int effortFromStart) {
            this.row = row;
            this.col = col;
            this.effortFromStart = effortFromStart;
        }
    }

    public static void main(String[] args) {
        MinimumEffortPath solution = new MinimumEffortPath();
        int[][] heights = {
                {1, 2, 2},
                {3, 8, 2},
                {5, 3, 5}
        };
        System.out.println(solution.minimumEffortPath(heights));
    }
}
