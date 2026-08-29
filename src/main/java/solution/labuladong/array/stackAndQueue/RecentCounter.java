package solution.labuladong.array.stackAndQueue;

import java.util.LinkedList;
import java.util.Queue;

public class RecentCounter {
    /* 933. 最近的请求次数 */
    Queue<Integer> q = new LinkedList<>();
    public int ping(int t) {
        q.offer(t); // 将当前请求时间加入队列
        while (q.peek() < t - 3000) {
            // t 是递增的，所以可以从队头删除 3000 毫秒之前的请求
            q.poll();
        }
        return q.size(); // 队列的大小即为最近 3000 毫秒内的请求数
    }
}
