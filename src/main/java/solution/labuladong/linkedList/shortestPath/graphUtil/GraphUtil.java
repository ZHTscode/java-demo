package solution.labuladong.linkedList.shortestPath.graphUtil;

// 图的构造与打印工具：把「建图」和「看结构」从算法代码里剥离出来，避免在每个 main 中重复写一长串 addEdge
// 所有方法只依赖 Graph 抽象，不关心底层是邻接表还是其他存储
public class GraphUtil {
    /**
     * 1. 构造图
     */
    // 由边列表构图，指定节点总数 n；edges 每行形如 {from, to} 或 {from, to, weight}，两列时权重默认 1
    public static Graph build(int n, int[][] edges) {
        Graph graph = new ListGraph(n);
        if (edges == null) {
            return graph;
        }
        for (int[] e : edges) {
            if (e == null || e.length < 2 || e.length > 3) {
                throw new IllegalArgumentException("edge must be {from, to} or {from, to, weight}: " + describe(e));
            }
            int weight = e.length == 3 ? e[2] : 1;
            graph.addEdge(e[0], e[1], weight);
        }
        return graph;
    }

    // 由边列表构图，节点总数自动取最大节点 ID + 1（要求节点 ID 连续覆盖 0..max）
    public static Graph build(int[][] edges) {
        return build(maxNodeId(edges) + 1, edges);
    }

    /**
     * 2. 打印图结构
     */
    // 打印邻接表：每行一个节点及其所有出边，格式「0 -> 1(5) 2(1)」，无出边的节点只打印自身
    public static void print(Graph graph) {
        System.out.println(toShapeString(graph));
    }

    // 生成图结构的文本表示，方便单测断言或拼接输出
    public static String toShapeString(Graph graph) {
        int n = graph.size();
        int edgeCount = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("Graph(size=").append(n).append(", directed)");
        for (int v = 0; v < n; v++) {
            sb.append("\n").append(v).append(" ->");
            for (Edge e : graph.neighbors(v)) {
                sb.append(" ").append(e.to).append("(").append(e.weight).append(")");
                edgeCount++;
            }
        }
        sb.append("\nedges=").append(edgeCount);
        return sb.toString();
    }

    /**
     * 3. 私有校验方法
     */
    // 找出边列表中的最大节点 ID，空边集返回 -1（即 0 个节点）
    private static int maxNodeId(int[][] edges) {
        int max = -1;
        if (edges == null) {
            return max;
        }
        for (int[] e : edges) {
            if (e == null || e.length < 2) {
                throw new IllegalArgumentException("edge must contain at least {from, to}: " + describe(e));
            }
            max = Math.max(max, Math.max(e[0], e[1]));
        }
        return max;
    }

    // 把一条非法边转成可读文本，用于异常信息
    private static String describe(int[] e) {
        if (e == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < e.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(e[i]);
        }
        return sb.append("}").toString();
    }
}
