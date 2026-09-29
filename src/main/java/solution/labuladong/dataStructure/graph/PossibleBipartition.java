package solution.labuladong.dataStructure.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PossibleBipartition {
    /* 886. 可能的二分法 */
    private boolean ok;
    private boolean[] color;
    private boolean[] visited;
    public boolean possibleBipartition(int n, int[][] dislikes) {
        // 图节点编号从 1 开始
        ok = true;
        color = new boolean[n + 1];
        visited = new boolean[n + 1];
        // 转化成邻接表表示图结构
        List<List<Integer>> graph = buildGraph(n, dislikes);
        for (int v = 1; v <= n; v++) {
            if (!visited[v]) traverse(graph, v);
        }
        return ok;
    }
    // 建图，方便获取邻居节点
    private List<List<Integer>> buildGraph(int n, int[][] dislikes) {
        // 图节点编号为 1...n
        // 多占一个位置（下标 0 不用），使节点编号 v 直接对应下标 v
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : dislikes) {
            int v = edge[1];
            int w = edge[0];
            // 无向图，需要添加双向边
            // v -> w
            graph.get(v).add(w);
            // w -> v
            graph.get(w).add(v);
        }
        return graph;
    }

    // 和之前判定二分图的 traverse 函数完全相同
    private void traverse(List<List<Integer>> graph, int v) {
        if (!ok) return;
        visited[v] = true;
        for (int w : graph.get(v)) {
            if (!visited[w]) {
                color[w] = !color[v];
                traverse(graph, w);
            } else {
                if (color[w] == color[v]) {
                    ok = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] dislikes = {{1,2},{3,4},{2,4}}; // 若有向图则不通过
        PossibleBipartition solution = new PossibleBipartition();
        boolean res = solution.possibleBipartition(n, dislikes);
        System.out.println(res);
    }

}