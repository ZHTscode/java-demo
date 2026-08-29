package solution.labuladong.array.stackAndQueue;

import java.util.LinkedList;
import java.util.Queue;

public class TimeRequiredToBuy {
    /* 2073. 买票需要的时间 */
    public int timeRequiredToBuy(int[] tickets, int k) {
        int res = 0;
        for (int i = 0; i < tickets.length; i++) {
            if (i <= k) {
                // 前面的人最多买了 tickets[k] 张票
                res += Math.min(tickets[k], tickets[i]);
            } else {
                // 后面的人最多买了 tickets[k] - 1 张票
                res += Math.min(tickets[k] - 1, tickets[i]);
            }
        }
        return res;
    }

    public int timeRequiredToBuy2(int[] tickets, int k) {
        // 用队列模拟整个过程
        // 初始化队列，存储每个人的编号 id
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < tickets.length; i++) {
            queue.offer(i);
        }
        int time = 0;
        while (!queue.isEmpty()) {
            // 队头的人买票
            int front = queue.poll();
            time++;
            tickets[front]--;

            if (front == k && tickets[front] == 0) {
                // 如果是 k 号买完票了，返回总时间
                return time;
            }

            if (tickets[front] == 0) {
                continue;
            }

            // 如果还要继续买票，重新排到队尾
            queue.offer(front);
        }
        return time;
    }

    public static void main(String[] args) {
        TimeRequiredToBuy solution = new TimeRequiredToBuy();
        int[] tickets = {2, 3, 2};
        int k = 2;
        System.out.println(solution.timeRequiredToBuy2(tickets, k));
    }
}
