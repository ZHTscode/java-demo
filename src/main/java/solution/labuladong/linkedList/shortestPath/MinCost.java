package solution.labuladong.linkedList.shortestPath;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class MinCost {
    /* 1368. 使网格图至少有一条有效路径的最小代价 */
    public int minCost(int[][] grid) {
        return dijkstra(grid);
    }

    int dijkstra(int[][] graph) {
        int m = graph.length;
        int n = graph[0].length;
        int[][] distTo = new int[m][n];
        for (int[] row : distTo) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        Queue<State> pq = new PriorityQueue<>((a, b) -> {
            return a.costFromStart - b.costFromStart;
        });

        pq.offer(new State(0, 0, 0));
        distTo[0][0] = 0;

        while (!pq.isEmpty()) {
            State state = pq.poll();
            int curX = state.x;
            int curY = state.y;
            int curCostFromStart = state.costFromStart;

            // 已经存在更优路径，则跳过
            if (curCostFromStart > distTo[curX][curY]) continue;
            // 判断是否已经到达目标点
            if (curX == m - 1 && curY == n - 1) return distTo[curX][curY];

            for (int directionId = 1; directionId <= 4; directionId++) {
                // 计算相邻节点的坐标
                int[] delta = getDelta(directionId);
                int nextX = curX + delta[0];
                int nextY = curY + delta[1];
                // 判断相邻节点是否越界
                if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) {
                    continue;
                }
                // 计算相邻节点的代价
                int nextCostFromStart = curCostFromStart;
                // 如果当前方向和目标方向不一致，则需要花费 1 的代价
                if (directionId != graph[curX][curY]) nextCostFromStart += 1;
                // 已经存在更优路径，则跳过
                if (nextCostFromStart >= distTo[nextX][nextY]) continue;
                pq.offer(new State(nextX, nextY, nextCostFromStart));
                distTo[nextX][nextY] = nextCostFromStart;
            }
        }
        return -1;
    }

    int[] getDelta(int directionId) {
        // 1 -> right, 2 -> left, 3 -> down, 4 -> up
        if (directionId == 1) return new int[]{0, 1};
        if (directionId == 2) return new int[]{0, -1};
        if (directionId == 3) return new int[]{1, 0};
        return new int[]{-1, 0};
    }

    static class State {
        int x, y;
        int costFromStart;
        public State(int x, int y, int costFromStart) {
            this.x = x;
            this.y = y;
            this.costFromStart = costFromStart;
        }
    }

    public static void main(String[] args){
        MinCost mc = new MinCost();
        int[][] grid = {{1,1,1,1},{2,2,2,2},{1,1,1,1},{2,2,2,2}};
        System.out.println(mc.minCost(grid));
    }
}