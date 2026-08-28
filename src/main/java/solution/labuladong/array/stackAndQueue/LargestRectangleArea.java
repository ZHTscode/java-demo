package solution.labuladong.array.stackAndQueue;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleArea {
    /* 84. 柱状图中最大的矩形$$ */
    // 对每根柱子，以它的高度作为矩形的高，能向左右延伸的边界就是两侧第一根比它矮的柱子
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        // 在两端各加一个高度为 0 的哨兵，避免边界判断
        int[] h = new int[n + 2];
        for (int i = 0; i < n; i++) h[i + 1] = heights[i];
        Deque<Integer> stk = new ArrayDeque<>(); // 单调递增栈(栈顶元素最大)，存储索引
        int maxArea = 0;
        for (int i = 0; i < n + 2; i++) {
            // 遇到比栈顶更矮的柱子，弹出并计算以弹出柱为高的最大矩形
            while (!stk.isEmpty() && h[stk.peek()] > h[i]) {
                int height = h[stk.pop()];
                // 宽度 = 当前索引 - 新栈顶索引 - 1
                int width = i - stk.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stk.push(i);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        LargestRectangleArea l = new LargestRectangleArea();
        int[] heights = {2, 1, 5, 6, 2, 3};
        int res = l.largestRectangleArea(heights);
        System.out.println(res);
    }
}
