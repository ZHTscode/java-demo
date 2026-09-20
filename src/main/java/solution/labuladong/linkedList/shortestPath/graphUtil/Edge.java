package solution.labuladong.linkedList.shortestPath.graphUtil;

// 图中的一条有向边：from -> to（from 由所在邻接表隐含），带权重
public class Edge {
    // 边的终点节点 ID
    public final int to;
    // 边的权重（无权图统一为 1）
    public final int weight;

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "->" + to + "(" + weight + ")";
    }
}
