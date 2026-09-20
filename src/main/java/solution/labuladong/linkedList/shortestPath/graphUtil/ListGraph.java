package solution.labuladong.linkedList.shortestPath.graphUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Graph 的邻接表实现（有向图），与遍历算法完全解耦
public class ListGraph implements Graph {
    // 节点总数
    private final int n;
    // adj.get(v) 存放节点 v 的所有出边
    private final List<List<Edge>> adj;

    public ListGraph(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("node count must be >= 0: " + n);
        }
        this.n = n;
        this.adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            this.adj.add(new ArrayList<>());
        }
    }

    @Override
    public void addEdge(int from, int to, int weight) {
        check(from);
        check(to);
        adj.get(from).add(new Edge(to, weight));
    }

    @Override
    public void removeEdge(int from, int to) {
        check(from);
        check(to);
        // 重复边会被一并删除
        adj.get(from).removeIf(e -> e.to == to);
    }

    @Override
    public boolean hasEdge(int from, int to) {
        return indexOf(from, to) >= 0;
    }

    @Override
    public int weight(int from, int to) {
        int i = indexOf(from, to);
        if (i < 0) {
            throw new IllegalArgumentException("no edge " + from + " -> " + to);
        }
        return adj.get(from).get(i).weight;
    }

    @Override
    public List<Edge> neighbors(int v) {
        check(v);
        // 返回只读视图，避免外部直接改动内部结构
        return Collections.unmodifiableList(adj.get(v));
    }

    @Override
    public int size() {
        return n;
    }

    // 返回 from 的出边中第一条指向 to 的下标，不存在返回 -1
    private int indexOf(int from, int to) {
        check(from);
        List<Edge> edges = adj.get(from);
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).to == to) {
                return i;
            }
        }
        return -1;
    }

    // 校验节点 ID 是否在 0..n-1 范围内
    private void check(int v) {
        if (v < 0 || v >= n) {
            throw new IllegalArgumentException("invalid node id: " + v);
        }
    }
}
