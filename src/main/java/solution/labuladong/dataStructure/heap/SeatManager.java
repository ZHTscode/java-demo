package solution.labuladong.dataStructure.heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class SeatManager {
    /* 1845. 座位预约管理系统 */
    // 优先级队列自动排序，队头元素最小
    Queue<Integer> pq = new PriorityQueue<>();
    public SeatManager(int n) {
        // 初始化所有空闲座位
        for (int i = 1; i <= n; i++) {
            pq.offer(i);
        }
    }
    // 返回最小编号的座位（队头元素）
    public int reserve() {
        return pq.poll();
    }
    // 将编号为 i 的座位变成空闲
    public void unreserve(int i) {
        pq.offer(i);
    }
}