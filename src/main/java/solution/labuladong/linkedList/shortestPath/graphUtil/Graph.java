package solution.labuladong.linkedList.shortestPath.graphUtil;

import java.util.List;

// 图的抽象：节点用 0..size()-1 的整数 ID 表示，具体存储结构由实现决定
public interface Graph {
    // 添加一条边（带权重）
    void addEdge(int from, int to, int weight);

    // 添加一条无权边（权重默认为 1）
    default void addEdge(int from, int to) {
        addEdge(from, to, 1);
    }

    // 删除一条边
    void removeEdge(int from, int to);

    // 判断两个节点是否相邻
    boolean hasEdge(int from, int to);

    // 返回一条边的权重
    int weight(int from, int to);

    // 返回某个节点的所有邻居节点和对应权重
    List<Edge> neighbors(int v);

    // 返回节点总数
    int size();
}
