package solution.labuladong.linkedList.bfs;

import java.util.*;

public class FindMinHeightTrees {
    /* 310. 最小高度树 */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            // base case，只有一个节点 0，无法形成边，所以直接返回节点 0
            return new ArrayList<>(0);
        }
        // 1、构建邻接表
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for (int[] edge : edges) {
            // 无向图，等同于双向图
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        // 2、找到所有的叶子节点
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (graph.get(i).size() == 1) {
                q.offer(i);
            }
        }

        // 3、不断删除叶子节点，直到剩下的节点数小于等于 2 个
        int nodeCount = n;
        while (nodeCount > 2) {
            int sz = q.size();
            nodeCount -= sz;
            for (int i = 0; i < sz; i++) {
                // 删除当前叶子节点
                int cur = q.poll();
                // 找到与当前叶子节点相连的节点
                for (int neighbor : graph.get(cur)) {
                    // 将被删除的叶子节点的邻接节点的度减 1
                    graph.get(neighbor).remove(Integer.valueOf(cur));
                    // 如果删除后，相连节点的度为 1，说明它也变成了叶子节点
                    if (graph.get(neighbor).size() == 1) {
                        q.offer(neighbor);
                    }
                }
            }
        }
        // 4、最后剩下的节点就是根节点
        return new ArrayList<>(q);
    }
}
