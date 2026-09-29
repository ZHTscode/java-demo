package solution.labuladong.dataStructure.graph;

import java.util.Arrays;

public class MinimumCost {
    /* 1135. 最低成本联通所有城市 */
    public int minimumCost(int n, int[][] connections) {
        // 城市编号为 1...n，所以初始化大小为 n + 1
        UF uf = new UF(n + 1);
        // 对所有边按照权重从小到大排序
        Arrays.sort(connections, (a, b) -> (a[2] - b[2]));
        // 记录最小生成树的权重之和
        int mst = 0;
        for (int[] edge : connections) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];
            // 若这条边会产生环，则不能加入 mst
            if (uf.connected(u, v)) continue;
            // 若这条边不会产生环，则属于最小生成树
            mst += weight;
            uf.union(u, v);
        }
        // 保证所有节点都被连通（节点 0 没有被使用，额外占用一个连通分量）
        return uf.count() == 2 ? mst : -1;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] connections = {{1, 2, 5}, {1, 3, 6}, {2, 3, 1}, {2, 4, 2}, {3, 5, 4}};
        MinimumCost mc = new MinimumCost();
        System.out.println(mc.minimumCost(n, connections));
    }
}
