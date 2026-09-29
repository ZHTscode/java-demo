package solution.labuladong.dataStructure.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CanFinish {
    /* 207. 课程表 */
    boolean[] onPath;
    boolean[] visited;
    boolean hasCycle;
    public boolean canFinish(int numCourses, int[][] prerequisites){
        List<List<Integer>> graph = buildGraph(numCourses, prerequisites);
        onPath = new boolean[numCourses];
        visited = new boolean[numCourses];
        hasCycle = false;
        for(int i=0; i<numCourses; i++){
            traverse(graph, i);
        }
        return !hasCycle;
    }
    private void traverse(List<List<Integer>> graph, int s){
        if(hasCycle) return; // 此前已经找到了环，不用再遍历
        if(onPath[s]){ // 如果已经遍历过该节点，说明找到了环
            hasCycle = true;
            return;
        }
        if(visited[s]) return; // 如果已经遍历过该节点，说明没有环

        visited[s] = true;
        onPath[s] = true;
        for(int t : graph.get(s)){
            traverse(graph, t); // 遍历相邻节点
        }
        onPath[s] = false; // 回溯
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

    public boolean canFinishBfs(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = buildGraph(numCourses, prerequisites);
        // 构建入度数组
        int[] indegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            int from = edge[1], to = edge[0];
            indegree[to]++; // 节点 to 的入度 +1
        }
        // 初始化 BFS 队列
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            // 入度为 0 的节点入队
            if (indegree[i] == 0) q.offer(i);
        }
        // 记录遍历的节点个数
        int count = 0;
        // 开始执行 BFS 循环
        while (!q.isEmpty()) {
            // 弹出节点 cur，并将其指向的节点的入度 -1
            int cur = q.poll();
            count++;
            for (int next : graph.get(cur)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    // 入度为 0 的节点入队
                    q.offer(next);
                }
            }
        }
        // 如果所有节点都被遍历过，说明不成环
        return count == numCourses;
    }
    
    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{1,0}, {0,1}};
        CanFinish solution = new CanFinish();
        boolean res = solution.canFinish(numCourses, prerequisites);
        System.out.println(res);
    }
}
