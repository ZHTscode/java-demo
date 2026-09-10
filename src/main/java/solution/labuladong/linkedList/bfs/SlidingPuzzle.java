package solution.labuladong.linkedList.bfs;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class SlidingPuzzle {
    /* 773. 滑动谜题$$ */
    public int slidingPuzzle(int[][] board) {
        int m = board.length, n = board[0].length;
        StringBuilder sb = new StringBuilder();
        String target = "123450";
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                sb.append(board[i][j]);
            }
        }
        String start = sb.toString();

        int[][] neighbor = new int[][]{
                {1, 3}, {0, 4, 2}, {1, 5}, {0, 4}, {3, 1, 5}, {4, 2}
        };

        Deque<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.offer(start);
        visited.add(start);
        int step = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0; i<size; i++){
                String cur = q.poll(); // 当前状态
                if(cur.equals(target)) return step; // 当前状态是目标状态，返回步数
                int idx = cur.indexOf('0'); // 找到数字0的索引
                for(int nextIdx : neighbor[idx]){ // 遍历所有相邻状态
                    String next = swap(cur.toCharArray(), idx, nextIdx); // 生成相邻状态
                    if(!visited.contains(next)){ // 如果没有访问过
                        q.offer(next);
                        visited.add(next);
                    }
                }
            }
            step++; // BFS，走相邻状态中的任一步
        }
        return -1;
    }

    private String swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }

    public static void main(String[] args) {
        SlidingPuzzle sp = new SlidingPuzzle();
        int[][] board = {{4,1,2},{5,0,3}};
        System.out.println(sp.slidingPuzzle(board));
    }
}
