package solution;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CanFinish {
    // 解法一：kahn算法
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 1. 构建邻接表和入度数组
        List<List<Integer>> adj = new ArrayList<>();
        // 元素是列表，表示邻接表
        int[] inDegree = new int[numCourses];
        // 初始化邻接表
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        // 填充邻接表和入度数组
        for (int[] p : prerequisites) {
            int a = p[0]; // 后续课程
            int b = p[1]; // 先修课程
            // 学完课程 b 后，可以学习课程 a（有向边 b -> a）
            adj.get(b).add(a);
            inDegree[a]++; // a的入度+1 (a的先修课程数+1)
        }
        System.out.println(adj);
        for (int i = 0; i < numCourses; i++) {
            System.out.println("课程 " + i + " 的入度为 " + inDegree[i]);
        }
        // 2. 初始化队列：入度为0的节点
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i); // 入度为0的节点入队
            }
        }
        // 3. 拓扑排序
        int count = 0; // 记录已完成拓扑排序的节点数
        while (!queue.isEmpty()) {
            int curr = queue.poll(); // 队首元素出队
            // 此时修curr课程不需要依赖其他课程
            // 立即修读curr课程
            count++;
            // 遍历当前节点的所有邻接节点，减少入度
            for (int next : adj.get(curr)) {
                inDegree[next]--;
                // 入度为0时加入队列
                if (inDegree[next] == 0) {
                    queue.offer(next); // 入度为0的节点入队
                }
            }
        }
        // 4. 判断是否无环：完成拓扑排序的节点数等于总课程数
        return count == numCourses;
    }
    // 解法二：dfs递归
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        // 1. 构建邻接表
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] p : prerequisites) {
            int a = p[0];
            int b = p[1];
            adj.get(b).add(a);
        }
        System.out.println(adj);
        // 2. 状态数组：0-未访问，1-正在访问，2-已访问
        int[] status = new int[numCourses];
        // 3. 遍历所有节点，检测环
        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(i, adj, status)) {
                return false;
            }
        }
        return true;
    }
    // 递归检测环
    private boolean hasCycle(int curr, List<List<Integer>> adj, int[] status) {
        if (status[curr] == 1) return true; // 状态1：当前递归栈中存在，说明有环
        if (status[curr] == 2) return false; // 状态2：已检测无环，无需重复检测
        status[curr] = 1; // 标记为正在访问
        // 遍历邻接节点
        for (int next : adj.get(curr)) {
            if (hasCycle(next, adj, status)) return true; // 只要递归到返回，全部函数栈返回
            // 一 true 到底，全部返回
        }
        // 回溯：标记为已访问
        status[curr] = 2; // 当前课程对其他课程无约束
        return false;
    }

    public static void main(String[] args) {
        CanFinish canFinish = new CanFinish();
        System.out.println(canFinish.canFinish2(4, new int[][]
                {{2,0}, {0, 1}, {1, 3}}));
    }
}