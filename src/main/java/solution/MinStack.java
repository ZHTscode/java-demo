package solution;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

// 在常数时间 O(1) 内获取栈内最小值的特殊栈结构

// 方法一：基于双栈实现，一个栈存储数据，另一个栈存储到栈底的最小值，长度相同
public class MinStack {
    /* 155. 最小栈 */
    private Deque<Integer> dataStack;
    private Deque<Integer> minStack;

    public MinStack() {
        dataStack = new LinkedList<>();
        minStack = new LinkedList<>();
    } // 构造方法

    public void push(int val) {
        dataStack.push(val);
        if (minStack.isEmpty()) {
            minStack.push(val);
        } else {
            minStack.push(Math.min(val, minStack.peek()));
        }
    }

    public void pop() {
        if (!dataStack.isEmpty()) {
            dataStack.pop();
            minStack.pop();
        }
    }

    public int top() {
        if (!dataStack.isEmpty()) {
            return dataStack.peek();
        }
        throw new RuntimeException("Stack is empty");
    }

    public int getMin() {
        if (!minStack.isEmpty()) {
            return minStack.peek();
        }
        throw new RuntimeException("Stack is empty");
    }
}

// 方法二：基于自定义链表节点实现
class MinStack2 {
    private record Node(int val, int min, Node prev){}; // 定义一个内部类，表示栈中的节点
    // 每个节点自身存储 当前值、当前栈内最小值、前驱节点
    private Node head;

    public MinStack2() {
    }

    // 利用链表的头插法模拟栈的入栈/出栈操作
    public void push(int val) {
        head = head == null ?
                new Node(val, val, null) : new Node(val, Math.min(head.min, val), head);
    }

    public void pop() {
        head = head.prev;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }
}

class MinStack3 {
    // 用Deque模拟栈，存储差值
    private Deque<Long> stack;
    // 当前最小值
    private long minVal;

    // 初始化
    public MinStack3() {
        stack = new LinkedList<>(); // 初始化双端队列，使用LinkedList实现
    }

    // 入栈
    public void push(int val) {
        if (stack.isEmpty()) { // 栈为空
            stack.push(0L); // 差值为 0
            minVal = val; // 初始化最小值
        } else {
            long diff = val - minVal; // 计算差值
            stack.push(diff); // 将差值压入栈中
            if (diff < 0) {
                minVal = val; // 更新最小值
            }
        }
    }

    // 出栈
    public void pop() {
        long diff = stack.pop();
        if (diff < 0) {
            minVal -= diff; // 恢复之前的最小值
        }
    }

    // 获取栈顶元素
    public int top() {
        long diff = stack.peek();
        if (diff < 0) {
            return (int) minVal; // 栈顶元素为最小值
        }
        return (int) (minVal + diff); // 计算栈顶元素的值
    }

    // 获取最小值
    public int getMin() {
        return (int) minVal;
    }
}