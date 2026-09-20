package solution.labuladong.linkedList.shortestPath.graphUtil;

// BFS 队列中的元素：当前节点 + 从起点 s 到该节点的遍历步数
public class State {
    // 当前节点 ID
    public final int node;
    // 从起点 s 到当前节点的遍历步数（经过的边的条数）
    public final int distFromStart;

    public State(int node, int distFromStart) {
        this.node = node;
        this.distFromStart = distFromStart;
    }
}
