package test;

import solution.MinStack;

public class TestMinStack {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        
        // 测试用例1：基本功能
        System.out.println("=== 基本功能测试 ===");
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println("当前最小值: " + minStack.getMin()); // 应该输出 -3
        
        minStack.pop();
        System.out.println("顶部元素: " + minStack.top());      // 应该输出 0
        System.out.println("当前最小值: " + minStack.getMin()); // 应该输出 -2
        
        // 测试用例2：重复最小值
        System.out.println("\n=== 重复最小值测试 ===");
        minStack.push(-5);
        minStack.push(-5);
        System.out.println("当前最小值: " + minStack.getMin()); // 应该输出 -5
        minStack.pop();
        System.out.println("当前最小值: " + minStack.getMin()); // 应该输出 -5
        minStack.pop();
        System.out.println("当前最小值: " + minStack.getMin()); // 应该输出 -2
        
        // 测试用例3：单个元素
        System.out.println("\n=== 单个元素测试 ===");
        MinStack singleStack = new MinStack();
        singleStack.push(5);
        System.out.println("顶部元素: " + singleStack.top());   // 应该输出 5
        System.out.println("当前最小值: " + singleStack.getMin()); // 应该输出 5
        
        System.out.println("\n所有测试通过！");
    }
}