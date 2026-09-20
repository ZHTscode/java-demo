package solution.labuladong.linkedList.shortestPath;

import java.util.*;

public class MaxProbability {
    /* 1514. 概率最大路径 */
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        List<List<double[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new LinkedList<>());
        }
        // 构造无向图
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            double weight = succProb[i];

            // 无向图是双向图
            graph.get(from).add(new double[] { (double) to, weight });
            graph.get(to).add(new double[] { (double) from, weight });
        }
        double res = dijkstra(graph, start, end);
        return res == -1 ? 0 : res;
    }

    double dijkstra(List<List<double[]>> graph, int src, int dst) {
        double[] probTo = new double[graph.size()];
        // 初始化为最小值
        Arrays.fill(probTo, 0.0);

        Queue<State> pq = new PriorityQueue<>((a, b) -> {
            // 概率大的排前面
            return Double.compare(b.probFromStart, a.probFromStart);
        });

        pq.offer(new State(src, 1.0));
        probTo[src] = 1.0;

        while (!pq.isEmpty()) {
            State state = pq.poll();
            int curNode = state.node;
            double curProbFromStart = state.probFromStart;

            // 已经存在更优路径，则跳过
            if (curProbFromStart < probTo[curNode]) continue;
            // 判断是否已经到达目标点
            if (curNode == dst) return probTo[curNode];

            for (double[] e : graph.get(curNode)) {
                int nextNode = (int) e[0];
                double nextProbFromStart = curProbFromStart * e[1];
                // 不能使概率变大，则跳过
                if (nextProbFromStart <= probTo[nextNode]) continue;
                pq.offer(new State(nextNode, nextProbFromStart));
                probTo[nextNode] = nextProbFromStart;
            }
        }
        return -1;
    }

    static class State {
        int node;
        double probFromStart;
        public State(int node, double probFromStart) {
            this.node = node;
            this.probFromStart = probFromStart;
        }
    }

    public static void main(String[] args) {
        MaxProbability mp = new MaxProbability();
        int n = 3;
        int[][] edges = {{0,1},{1,2},{0,2}};
        double[] succProb = {0.5,0.5,0.2};
        System.out.println(mp.maxProbability(n, edges, succProb, 0, 2));
    }
}
