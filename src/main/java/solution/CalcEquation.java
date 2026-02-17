package solution;

import java.util.*;

public class CalcEquation {
    // 解法一：图 + 深度优先搜索
    // 把每个变量看成图中的一个节点，题目给出的方程组看成图的边，边的权重为除法的结果
    /*
    输入：equations = [["a","b"],["b","c"]],
         values = [2.0,3.0],
         queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
    输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
    */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 1. 构建图
        // 内层 Map 的键为字符串(如 "b")，值为双精度浮点数(如 a/b = 2.0)
        // 外层 Map 的键为字符串(如 "a")，值为内层 Map
        /* {
        a={b=2.0},
        b={a=0.5, c=3.0},
        c={b=0.33}
        }*/
        Map<String, Map<String, Double>> graph = new HashMap<>(); // 构建邻接表
        for (int i = 0; i < equations.size(); i++) {
            String u = equations.get(i).get(0); // "a"
            String v = equations.get(i).get(1); // "b"
            double val = values[i]; // 2.0
            /*
            .computeIfAbsent：
                拿到键 u 对应的邻居值 map；如果没有，就新建一个空 map 并存进去，然后返回它
            .put(v, val)：
                向键 u 对应的邻居值 map 中添加键值对 v -> val
             */
            System.out.println("第" + i + "次");
            graph.computeIfAbsent(u, k -> new HashMap<>()).put(v, val); // 添加边 u -> v
            System.out.println(graph);
            graph.computeIfAbsent(v, k -> new HashMap<>()).put(u, 1.0 / val); // 添加边 v -> u
            System.out.println(graph);
        }
        System.out.println("图的结构为：");
        System.out.println(graph);
        // 2. 处理每个查询
        double[] result = new double[queries.size()]; // 查询的个数：行数
        for (int i = 0; i < queries.size(); i++) {
            String start = queries.get(i).get(0); // 除数 "a"
            String end = queries.get(i).get(1); // 被除数 "c"
            if (!graph.containsKey(start) || !graph.containsKey(end))
                result[i] = -1.0; // start 或 end 不在图中，返回 -1
            else if (start.equals(end)) // 除以自己
                result[i] = 1.0;
            else { // start 不等于 end
                Set<String> visited = new HashSet<>(); // visited 集合记录访问过的节点
                result[i] = dfs(graph, start, end, visited); // 深度优先搜索
            }
        }
        return result;
    }
    // 用于在图中查找从当前节点 curr 到目标节点 target 的路径，并计算路径上所有边权重的乘积（即除法结果）
    private double dfs(Map<String, Map<String, Double>> graph, String curr, String target, Set<String> visited) {
        // 递归边界：当前节点就是目标节点
        if (curr.equals(target)) return 1.0;
        // 添加访问过的节点 curr
        visited.add(curr);
        // 获取键 curr 对应的邻居值 map
        Map<String, Double> neighbors = graph.get(curr);
        // 遍历 curr 的所有邻居节点，next 为邻居节点的键
        // 其中.keySet()：返回一个包含所有键的集合
        for (String next : neighbors.keySet()) {
            // 如果 next 已访问就跳过
            // 图中可能有环（如 a→b→a），不加会无限递归至栈溢出，是 DFS 的标准防环操作
            if (visited.contains(next)) continue;
            double res = dfs(graph, next, target, visited);
            if (res != -1.0){
                System.out.println(neighbors.get(next) * res);
                return neighbors.get(next) * res;
            }
        }
        visited.remove(curr); // 可选（因为每次查询新建 visited）
        return -1.0;
    }
    // 解法二：Floyd-Warshall 算法
    /*
    输入：equations = [["a","b"],["b","c"]],
         values = [2.0,3.0],
         queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
    输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
    */
    public double[] calcEquation2(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 收集所有变量
        Set<String> vars = new HashSet<>(); // vars 为集合类型，变量为字符串
        for (List<String> eq : equations) {
            vars.add(eq.get(0)); // 获取第一个变量
            vars.add(eq.get(1)); // 获取第二个变量
            System.out.println(vars);
        }
        // 映射到索引
        Map<String, Integer> idxMap = new HashMap<>(); // idxMap，键为字符串，值为整数
        int n = 0; // n 为变量的个数
        for (String v : vars) idxMap.put(v, n++); // 键为集合 vars 中的元素，值为索引
        // idxMap = {"a" → 0, "b" → 1, "c" → 2}
        // 初始化 dist 矩阵（方阵）
        double[][] dist = new double[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], -1.0); // 矩阵所有元素初始化为 -1.0，表示未知或不可达
            dist[i][i] = 1.0; // 对角线元素为 1.0
        }
        // 填入已知边
        for (int i = 0; i < equations.size(); i++) {
            // 内层获取 equations 的 i 行元素
            // 外层获取变量名（如 "a"）的唯一整数编号（如 0）：a 在第1行、第1列
            int u = idxMap.get(equations.get(i).get(0)); // 获取第一个变量的索引
            int v = idxMap.get(equations.get(i).get(1)); // 获取第二个变量的索引
            dist[u][v] = values[i]; // 填入已知边
            dist[v][u] = 1.0 / values[i]; // 填入已知边
        }
        System.out.println(Arrays.deepToString(dist));
        // Floyd-Warshall
        for (int k = 0; k < n; k++) { // 遍历所有中间节点
            for (int i = 0; i < n; i++) { // 遍历所有行
                for (int j = 0; j < n; j++) { // 遍历所有列
                    if (dist[i][k] > 0 && dist[k][j] > 0) // 中间节点 k 可达
                        if (dist[i][j] < 0) // dist[i][j] 未更新过
                            dist[i][j] = dist[i][k] * dist[k][j]; // 更新最短路径
                }
            }
        }
        System.out.println(Arrays.deepToString(dist));
        // 回答查询
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String a = queries.get(i).get(0); // 获取查询的除数
            String b = queries.get(i).get(1); // 获取查询的被除数
            if (!idxMap.containsKey(a) || !idxMap.containsKey(b)) {
                // a 或 b 不在图中，返回 -1.0
                res[i] = -1.0;
            } else {
                // a 和 b 在图中，返回 dist[a][b]
                res[i] = dist[idxMap.get(a)][idxMap.get(b)];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        CalcEquation calcEquation = new CalcEquation();
        List<List<String>> equations = new ArrayList<>();
        equations.add(Arrays.asList("a", "b"));
        equations.add(Arrays.asList("b", "c"));
        double[] values = {2.0, 3.0};
        List<List<String>> queries = new ArrayList<>();
        queries.add(Arrays.asList("a", "c"));
        queries.add(Arrays.asList("b", "a"));
        queries.add(Arrays.asList("a", "e"));
        queries.add(Arrays.asList("a", "a"));
        queries.add(Arrays.asList("x", "x"));
        double[] result = calcEquation.calcEquation(equations, values, queries);
        for (double res : result) {
            System.out.print(res + " ");
        }
    }
}