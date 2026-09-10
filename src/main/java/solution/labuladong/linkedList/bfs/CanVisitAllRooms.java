package solution.labuladong.linkedList.bfs;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class CanVisitAllRooms {
    /* 841. 钥匙和房间 */
    public boolean canVisitAllRooms1(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        dfs(rooms, 0, visited);
        for(boolean v : visited){
            if(!v) return false;
        }
        return true;
    }
    private void dfs(List<List<Integer>> rooms, int room, boolean[] visited){
        if(visited[room]) return;
        visited[room] = true;
        for(int nextRoom : rooms.get(room)){
            dfs(rooms, nextRoom, visited);
        }
    }

    public boolean canVisitAllRooms2(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        Deque<Integer> q = new LinkedList<>();
        q.offer(0);
        visited[0] = true;
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int nextRoom : rooms.get(cur)){
                if(!visited[nextRoom]){
                    visited[nextRoom] = true;
                    q.offer(nextRoom);
                }
            }
        }
        for(boolean v : visited){
            if(!v) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        CanVisitAllRooms canVisitAllRooms = new CanVisitAllRooms();
        List<List<Integer>> rooms = List.of(List.of(1,3), List.of(3,0,1), List.of(2), List.of(0));
        System.out.println(canVisitAllRooms.canVisitAllRooms1(rooms));
        System.out.println(canVisitAllRooms.canVisitAllRooms2(rooms));
    }
}
