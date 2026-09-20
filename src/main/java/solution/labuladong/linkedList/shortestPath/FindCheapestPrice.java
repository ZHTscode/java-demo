package solution.labuladong.linkedList.shortestPath;

import java.util.*;

public class FindCheapestPrice {
    /* 787. K 站中转内最便宜的航班 */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new LinkedList<>());
        }
        for (int[] edge : flights) {
            int from = edge[0];
            int to = edge[1];
            int price = edge[2];
            graph.get(from).add(new int[]{to, price});
        }
        // 最长路径为 K + 1
        return dijkstra(graph, src, dst, K + 1);
    }

    int dijkstra(List<List<int[]>> graph, int src, int dst, int k) {
        // distTo[i][j]：从 src 到达 i 节点，并且走了 j 条边的最短路径
        int[][] distTo = new int[graph.size()][k + 1];
        for (int i = 0; i < graph.size(); i++) {
            Arrays.fill(distTo[i], Integer.MAX_VALUE);
        }

        Queue<State> pq = new PriorityQueue<>((a, b) -> {
            return a.distFromStart - b.distFromStart;
        });

        // 从起点 src 到起点 src 的最短距离是 0
        pq.offer(new State(src, 0, 0));

        while (!pq.isEmpty()) {
            State state = pq.poll();
            int curNode = state.node;
            int curDistFromStart = state.distFromStart;
            int curEdgesFromStart = state.edgesFromStart;

            // 已经存在更优路径，则跳过
            if (curDistFromStart > distTo[curNode][curEdgesFromStart]) continue;
            // 判断是否已经到达目标点
            if (curNode == dst) return distTo[dst][curEdgesFromStart];

            for (int[] e : graph.get(curNode)) {
                int nextNode = e[0];
                int nextDistFromStart = curDistFromStart + e[1];
                int nextEdgesFromStart = curEdgesFromStart + 1;

                // 边数超过 k 或者路径长度大于此前已知的最短路径，则跳过
                if (nextEdgesFromStart > k || nextDistFromStart >= distTo[nextNode][nextEdgesFromStart]) {
                    continue;
                }

                distTo[nextNode][nextEdgesFromStart] = nextDistFromStart;
                pq.offer(new State(nextNode, nextDistFromStart, nextEdgesFromStart));
            }
        }
        return -1;
    }

    static class State {
        int node;
        int distFromStart;
        int edgesFromStart;
        public State(int node, int distFromStart, int edgesFromStart) {
            this.node = node;
            this.distFromStart = distFromStart;
            this.edgesFromStart = edgesFromStart;
        }
    }

    public static void main(String[] args) {
        FindCheapestPrice solution = new FindCheapestPrice();
        int n = 3;
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        int src = 0;
        int dst = 2;
        int K = 1;
        System.out.println(solution.findCheapestPrice(n, flights, src, dst, K));
    }
}
