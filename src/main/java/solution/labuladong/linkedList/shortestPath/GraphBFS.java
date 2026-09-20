package solution.labuladong.linkedList.shortestPath;

import solution.labuladong.linkedList.shortestPath.graphUtil.*;

import java.util.Deque;
import java.util.LinkedList;

// 图结构的 BFS 遍历，从节点 s 开始进行 BFS，且记录遍历步数（从起点 s 到当前节点的边的条数）
// 每个节点自行维护 State 类，记录从 s 走来的遍历步数
// 算法只依赖 Graph 抽象，不关心图用邻接表还是邻接矩阵存储；BFS 按无权图处理，忽略边权重
public class GraphBFS {
    public void bfs(Graph graph, int s) {
        boolean[] visited = new boolean[graph.size()];
        Deque<State> q = new LinkedList<>();

        q.offer(new State(s, 0));
        visited[s] = true;

        while (!q.isEmpty()) {
            State state = q.poll();
            int cur = state.node;
            int step = state.distFromStart;
            System.out.println("visit " + cur + " with step " + step);
            for (Edge e : graph.neighbors(cur)) {
                if (visited[e.to]) continue;
                q.offer(new State(e.to, step + 1));
                visited[e.to] = true;
            }
        }
    }

    public static void main(String[] args) {
        // 由边列表建图（两列表示无权边，默认权重 1）
        Graph graph = GraphUtil.build(7, new int[][]{
                {0, 1}, {0, 2},
                {1, 3}, {2, 3},
                {3, 4},
                {4, 5}, {4, 6}
        });
        // 先打印图结构，方便对照 BFS 的输出顺序
        GraphUtil.print(graph);

        new GraphBFS().bfs(graph, 0);
    }
}
