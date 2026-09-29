package solution.labuladong.dataStructure.graph;

import java.util.*;

public class FindOrder {
    /* 210. 课程表 II */
    List<Integer> postorder; // 记录后序遍历结果
    boolean hasCycle;
    boolean[] visited, onPath;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        postorder = new ArrayList<>();
        hasCycle = false;
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];
        List<List<Integer>> graph = buildGraph(numCourses, prerequisites);
        // 遍历图
        for (int i = 0; i < numCourses; i++) {
            traverse(graph, i);
        }
        // 有环图无法进行拓扑排序
        if (hasCycle) return new int[]{};
        // 逆后序遍历结果即为拓扑排序结果
        Collections.reverse(postorder);
        // 转换为数组
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = postorder.get(i);
        }
        return res;
    }

    void traverse(List<List<Integer>> graph, int s) {
        if (onPath[s]) hasCycle = true; // 发现环
        if (visited[s] || hasCycle) return; // 已经遍历过该节点，说明没有环
        // 前序遍历位置
        visited[s] = true;
        onPath[s] = true;
        for (int t : graph.get(s)) {
            traverse(graph, t);
        }
        // 后序遍历位置
        // 所有子节点处理完才记录当前点，拓扑排序必须用后序
        postorder.add(s);
        onPath[s] = false; // 回溯
    }

    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = buildGraph(numCourses, prerequisites);
        // 构建入度数组
        int[] indegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            int from = edge[1], to = edge[0];
            indegree[to]++;
        }
        // 初始化 BFS 队列
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            // 入度为 0 的节点入队
            if (indegree[i] == 0) q.offer(i);
        }
        // 记录拓扑排序结果
        int[] res = new int[numCourses];
        // 记录遍历节点的索引
        int count = 0;
        // 开始执行 BFS 算法
        while (!q.isEmpty()) {
            int cur = q.poll();
            // 弹出节点的顺序即为拓扑排序结果
            res[count] = cur;
            count++;
            for (int next : graph.get(cur)) {
                indegree[next]--;
                if (indegree[next] == 0) q.offer(next);
            }
        }
        if (count != numCourses) return new int[]{}; // 存在环，拓扑排序不存在
        return res;
    }

    // 建图，方便获取邻居节点
    private List<List<Integer>> buildGraph(int numCourses, int[][] prerequisites){
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0; i<numCourses; i++){
            graph.add(new ArrayList<>());
        }
        for(int[] edge : prerequisites){
            int from = edge[1];
            int to = edge[0];
            graph.get(from).add(to);
        }
        return graph;
    }

    public static void main(String[] args) {
        FindOrder solution = new FindOrder();
        int numCourses = 4;
        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
        int[] order = solution.findOrder(numCourses, prerequisites);
        for (int i : order) {
            System.out.print(i + " ");
        }
    }
}