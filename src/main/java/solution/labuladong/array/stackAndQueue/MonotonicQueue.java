package solution.labuladong.array.stackAndQueue;

import java.util.LinkedList;

public class MonotonicQueue {
    LinkedList<Integer> q = new LinkedList<>();
    public void push(int n) {
        // 将小于 n 的元素全部删除
        while (!q.isEmpty() && q.getLast() < n) {
            q.pollLast(); // 删除尾部元素
        }
        q.addLast(n); // 元素加入尾部
    }

    public int max() {
        return q.getFirst(); // 返回头部元素
    }

    public void pop(int n) {
        if (n == q.getFirst()) { // 判断头部元素是否等于 n
            q.pollFirst(); // 删除头部元素
        }
    }
}