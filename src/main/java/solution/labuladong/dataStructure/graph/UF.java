package solution.labuladong.dataStructure.graph;

public class UF {
    // 成员变量
    private int count; // 当前连通分量数量
    private int[] parent; // parent[i] = i 的父节点
    private int[] size; // 只对根节点有效，表示这棵树的节点总数

    // 初始构造：n 个节点，n 个连通分量，每个集合大小 = 1
    public UF(int n) {
        this.count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) { // 从 0 到 n-1
            parent[i] = i; // 初始每个节点自己是一棵树，自成一个连通分量
            size[i] = 1; // 用来防止树退化成链表，控制树高
        }
    }

    // 查找 + 路径压缩
    public int find(int x) {
        if (parent[x] != x) {
            // 递归把路径上所有节点直接指向根，降低后续查询时间
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // 返回图中的连通分量个数
    public int count() {
        return count;
    }

    // 返回节点 x 所在连通分量的节点总数
    public int size(int x) {
        int root = find(x); // 找到 x 的根节点
        return size[root];
    }

    // 将节点 p 和节点 q 连通
    public void union(int p, int q) {
        int rootP = find(p); // 找到 p 的根节点
        int rootQ = find(q); // 找到 q 的根节点
        if (rootP == rootQ) return;

        // 把小树接到大树下面，更平衡
        if (size[rootP] > size[rootQ]) {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ]; // 更新树的节点总数
        } else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP]; // 更新树的节点总数
        }
        // 两个连通分量合并成一个连通分量，连通分量数量减一
        count--;
    }

    // 判断节点 p 和节点 q 是否连通
    public boolean connected(int p, int q) {
        int rootP = find(p); // 找到 p 的根节点
        int rootQ = find(q); // 找到 q 的根节点
        return rootP == rootQ; // 根节点相同则连通
    }
}