package solution.labuladong.array.hash;

import java.util.*;

class GraphNode {
    public int val;
    public List<GraphNode> neighbors;
    public GraphNode() {
        val = 0;
        neighbors = new ArrayList<GraphNode>();
    }
    public GraphNode(int _val) {
        val = _val;
        neighbors = new ArrayList<GraphNode>();
    }
    public GraphNode(int _val, ArrayList<GraphNode> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class CloneGraph {
    /* 133. 克隆图 */
    public GraphNode cloneGraph(GraphNode node) {
        // DFS 遍历图，顺便构建克隆图
        traverse(node);
        // 从 map 里找到克隆图的对应节点
        return originToClone.get(node);
    }
    // 记录 DFS 遍历过的节点
    Set<GraphNode> visited = new HashSet<>();
    // 记录原节点到克隆节点的映射
    Map<GraphNode, GraphNode> originToClone = new HashMap<>();

    // DFS 图遍历
    void traverse(GraphNode node) {
        if (node == null) {
            return;
        }
        if (visited.contains(node)) {
            return;
        }
        // 前序位置，标记为已访问
        visited.add(node);
        // 前序位置，克隆节点
        if (!originToClone.containsKey(node)) {
            originToClone.put(node, new GraphNode(node.val));
        }
        GraphNode cloneNode = originToClone.get(node);
        // 递归遍历邻居节点，并构建克隆图
        for (GraphNode neighbor : node.neighbors) {
            traverse(neighbor);
            // 递归之后，邻居节点一定存在 originToClone 中
            GraphNode cloneNeighbor = originToClone.get(neighbor);
            cloneNode.neighbors.add(cloneNeighbor);
        }
    }
}
