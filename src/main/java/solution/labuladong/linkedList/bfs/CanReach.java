package solution.labuladong.linkedList.bfs;

import java.util.Deque;
import java.util.LinkedList;

public class CanReach {
    /* 1306. 跳跃游戏 III */
    public boolean canReach(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];
        Deque<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;
        while(!q.isEmpty()){
            int cur = q.poll();
            if(arr[cur] == 0) return true;
            // 向左跳
            int left = cur - arr[cur];
            if(left >=0 && !visited[left]){
                q.offer(left);
                visited[left] = true;
            }
            // 向右跳
            int right = cur + arr[cur];
            if(right < arr.length && !visited[right]){
                q.offer(right);
                visited[right] = true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CanReach canReach = new CanReach();
        int[] arr = {4,2,3,0,3,1,2};
        int start = 5;
        System.out.println(canReach.canReach(arr, start));
    }
}
