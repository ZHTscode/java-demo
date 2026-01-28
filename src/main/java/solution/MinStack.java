package solution;

import java.util.Stack;

// 在常数时间 O(1) 内获取栈内最小值的特殊栈结构

// 方法一：基于双栈实现
public class MinStack {
    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;

    public MinStack() {
        dataStack = new Stack<>();
        minStack = new Stack<>();
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
/*class MinStack2 {
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
}*/