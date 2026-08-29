package solution.labuladong.array.stackAndQueue;

import java.util.Stack;

public class MyQueue {
    /* 232. 用栈实现队列 */
    private Stack<Integer> s1, s2;

    public MyQueue() { // 基于双栈
        s1 = new Stack<>(); // 右
        s2 = new Stack<>(); // 左
    }

    // 添加元素到队尾
    public void push(int x) {
        s1.push(x);
    }

    // 删除队头元素并返回
    public int pop() {
        peek(); // 保证 s2 非空
        return s2.pop();
    }

    // 返回队头元素
    public int peek() {
        if (s2.isEmpty()){
            while (!s1.isEmpty()){
                s2.push(s1.pop()); // 把 s1 元素压入 s2
            }
        }
        return s2.peek();
    }

    // 判断队列是否为空
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}