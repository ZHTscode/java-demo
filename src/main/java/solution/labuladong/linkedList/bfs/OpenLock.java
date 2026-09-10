package solution.labuladong.linkedList.bfs;

import java.util.*;

public class OpenLock {
    /* 752. 打开转盘锁 */
    public int openLock(String[] deadends, String target) {
        Set<String> deads = new HashSet<>();
        for(String s : deadends){
            deads.add(s);
        }
        if(deads.contains("0000")) return -1;

        Set<String> visited = new HashSet<>();
        Deque<String> q = new LinkedList<>();
        q.offer("0000");
        visited.add("0000");
        int step = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0; i<size; i++){
                String cur = q.poll(); // 当前状态
                if(cur.equals(target)) return step; // 当前状态是目标状态，返回步数
                for(String next : getNeighbors(cur)){
                    if(!deads.contains(next) && !visited.contains(next)){
                        q.offer(next);
                        visited.add(next);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    // 将 s[j] 向上拨动一次
    String plusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '9')
            ch[j] = '0';
        else
            ch[j] += 1;
        return new String(ch);
    }

    // 将 s[i] 向下拨动一次
    String minusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '0')
            ch[j] = '9';
        else
            ch[j] -= 1;
        return new String(ch);
    }

    // 将 s 的每一位向上拨动一次或向下拨动一次，8 种相邻密码
    List<String> getNeighbors(String s) {
        List<String> neighbors = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            neighbors.add(plusOne(s, i));
            neighbors.add(minusOne(s, i));
        }
        return neighbors;
    }
}
