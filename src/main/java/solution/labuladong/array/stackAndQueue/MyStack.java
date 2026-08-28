package solution.labuladong.array.stackAndQueue;

import java.util.ArrayDeque;
import java.util.Queue;

public class MyStack {
    Queue<Integer> q = new ArrayDeque<>();
    int top_elem = 0;

    // 将元素 x 压入栈顶
    public void push(int x) {
        // x 是队列的队尾，是栈的栈顶
        q.offer(x);
        top_elem = x;
    }

    // 返回栈顶元素
    public int top() {
        return top_elem;
    }

    // 删除栈顶的元素并返回
    public int pop() {
        int size = q.size();
        // 留下队尾 2 个元素
        while (size > 2) {
            q.offer(q.poll());
            size--;
        }
        // 记录新的队尾元素
        top_elem = q.peek();
        q.offer(q.poll());
        // 删除之前的队尾元素
        return q.poll();
    }

    // 判断栈是否为空
    public boolean empty() {
        return q.isEmpty();
    }
}
