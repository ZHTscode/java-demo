package solution.labuladong.linkedList.shortestPath;

import solution.labuladong.linkedList.shortestPath.graphUtil.State;

import java.util.*;

public class NetworkDelayTime {
    /* 743. 网络延迟时间 */
    public int networkDelayTime(int[][] times, int n, int k) {
        // 节点编号是从 1 开始的，所以要一个大小为 n + 1 的邻接表
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new LinkedList<>());
        }
        // 构造图
        for (int[] edge : times) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            // from -> List<(to, weight)>，邻接表存储图结构
            graph.get(from).add(new int[] { to, weight });
        }

        int[] distTo = dijkstra(graph, k);

        // 找到最长的那条最短路径
        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (distTo[i] == Integer.MAX_VALUE) {
                // 有节点不可达
                return -1;
            }
            res = Math.max(res, distTo[i]);
        }
        return res;
    }

    private int[] dijkstra(List<List<int[]>> graph, int src) {
        int n = graph.size();
        int[] distTo = new int[n];
        Arrays.fill(distTo, Integer.MAX_VALUE);

        Queue<State> pq = new PriorityQueue<>((a, b) -> {
            return a.distFromStart - b.distFromStart;
        });

        pq.offer(new State(src, 0));
        distTo[src] = 0;

        while (!pq.isEmpty()) {
            State state = pq.poll();
            int curNode = state.node;
            int curDistFromStart = state.distFromStart;
            if (curDistFromStart > distTo[curNode]) continue; // 跳过过时的路径
            for (int[] e : graph.get(curNode)) {
                int nextNode = e[0];
                int nextDistFromStart = curDistFromStart + e[1];
                if (nextDistFromStart >= distTo[nextNode]) continue; // 不能使路径更短
                pq.offer(new State(nextNode, nextDistFromStart));
                distTo[nextNode] = nextDistFromStart;
            }
        }
        return distTo;
    }

    public static void main(String[] args) {
        NetworkDelayTime solution = new NetworkDelayTime();
        int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int n = 4;
        int k = 2;
        int res = solution.networkDelayTime(times, n, k);
        System.out.println(res);
    }
}
