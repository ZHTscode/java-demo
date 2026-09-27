package solution.labuladong.dataStructure.graph;

import java.util.Deque;
import java.util.LinkedList;

public class IsBipartite {
    /* 785. 判断二分图 */
    private boolean ok;
    private boolean[] color;
    private boolean[] visited;
    public boolean isBipartite(int[][] graph){
        int n = graph.length; // 节点数
        ok = true;
        color = new boolean[n];
        visited = new boolean[n];
        for(int v =0; v<n; v++){ // 可能存在多个连通分量，需要遍历所有节点
            if(!visited[v]){
                traverse(graph, v);
                // bfs(graph, v);
            }
        }
        return ok;
    }
    private void traverse(int[][] graph, int v){
        if(!ok) return; // 已经确定不是二分图，不用再继续递归
        visited[v] = true;
        for(int w : graph[v]){ // 访问邻居
            if(!visited[w]){
                color[w] = !color[v];
                traverse(graph, w);
            } else{
                if(color[w] == color[v]) // 邻居颜色相同，不是二分图
                    ok = false;
            }
        }
    }

    private void bfs(int[][] graph, int start) {
        Deque<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;
        while (!q.isEmpty() && ok) {
            int v = q.poll();
            for (int w : graph[v]) { // 访问邻居
                if (!visited[w]) {
                    color[w] = !color[v];
                    q.offer(w);
                    visited[w] = true;
                } else {
                    if (color[w] == color[v]) {
                        ok = false; // 邻居颜色相同，不是二分图
                        return;
                    }
                }
            }
        }
    }
    public static void main(String[] args){
        IsBipartite bipartite = new IsBipartite();
        int[][] graph = {{1,2,3},{0,2},{0,1,3},{0,2}};
        System.out.println(bipartite.isBipartite(graph));
    }


}
