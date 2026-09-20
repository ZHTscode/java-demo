package solution.labuladong.linkedList.shortestPath;

import solution.labuladong.linkedList.shortestPath.graphUtil.*;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class GraphDijkstra {
    public int[] dijkstra(Graph graph, int src) {
        // distTo[i] 表示从起点 src 到节点 i 的最小路径权重和
        int[] distTo = new int[graph.size()];
        // 都初始化为正无穷，表示未计算
        Arrays.fill(distTo, Integer.MAX_VALUE);
        // 优先级队列，让距离起点最近的节点优先出队
        Queue<State> pq = new PriorityQueue<>((a, b) -> {
            return a.distFromStart - b.distFromStart;
        });
        // 从起点 src 开始进行 BFS
        pq.offer(new State(src, 0));
        distTo[src] = 0;
        while (!pq.isEmpty()) {
            // 队列为空：所有可能更新最短距离的候选路径处理完毕，没有待探索的状态
            State state = pq.poll();
            int curNode = state.node;
            int curDistFromStart = state.distFromStart;
            if (curDistFromStart > distTo[curNode]) {
                // 队列中可能存在重复的节点，在元素出队时进行判断，去除较差的重复节点
                continue;
            }
            for (Edge e : graph.neighbors(curNode)) {
                int nextNode = e.to;
                int nextDistFromStart = curDistFromStart + e.weight;
                if (nextDistFromStart >= distTo[nextNode]) continue;
                // 仅当节点能够让 distTo[node] 更小时，将该节点加入优先级队列
                pq.offer(new State(nextNode, nextDistFromStart));
                // 记录 nextNode 节点到起点的最小路径权重和
                distTo[nextNode] = nextDistFromStart;
            }
        }
        return distTo;
    }

    public static void main(String[] args) {
        Graph graph = GraphUtil.build(7, new int[][]{
                {0, 1}, {0, 2},
                {1, 3}, {2, 3},
                {3, 4},
                {4, 5}, {4, 6}
        });
        GraphDijkstra solution = new GraphDijkstra();
        int[] distTo = solution.dijkstra(graph, 0);
        System.out.println(Arrays.toString(distTo));
    }
}
