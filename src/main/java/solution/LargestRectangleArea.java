package solution;

import java.util.Stack;

public class LargestRectangleArea {
    /* 84.图中最大的矩形 */
    /* 解法一：暴力解法（提交超时）
       对每个柱子向左右扩展 O(n²) */
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        if (len == 0)   return 0;
        int res = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    min = Math.min(min, heights[k]);
                }
                res = Math.max(res, min * (j - i + 1));
            }
        }
        return res;
    }
    /* 解法二：单调递减栈（栈存下标，按高度递减排序）（提交超时）
       对每个柱子 i，找到它能向左右扩展的最大宽度
       左边界：左边第一个小于 heights[i] 的柱子
       右边界：右边第一个小于 heights[i] 的柱子
       宽度 = 右边界 - 左边界 - 1
       面积 = heights[i] × 宽度
       一次遍历找到左右边界 O(n) */
    public int largestRectangleArea2(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0; // 全局变量
        for (int i = 0; i <= n; i++) { // 多一个 n 作为哨兵
            // 当前高度（i=n 时为 0，确保所有柱子都被处理）
            int h = (i == n) ? 0 : heights[i];
            // 维护单调递减栈
            while (!stack.isEmpty() && h < heights[stack.peek()]) { // 当前高度小于栈顶高度
                int height = heights[stack.pop()]; // 弹出栈顶，计算以它为高的矩形面积
                /* 弹出柱子索引 index = stack.pop()
                   左边界：stack.peek()（弹出后的新栈顶）
                   右边界：i（当前索引）
                   宽度 = 右边界 - 左边界 - 1 = i - stack.peek() - 1
                        左边界                右边界
                          ↓                    ↓
                         [ ] [新弹出] [此前弹出] [i]
                              ← 宽度 →
                   栈为空，左边没有比当前高度小的柱子，左边界为 -1，宽度为 i */
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
            System.out.println(stack);
        }
        return maxArea;
    }
    /* 解法三：数组模拟栈（提交最优）
       用一个指针 top 维护栈顶，栈底固定为 -1
       一次遍历找到左右边界 O(n) */
    public int largestRectangleArea3(int[] heights) {
        int n = heights.length;
        // 1. 哨兵数组
        int[] newHeights = new int[n + 2];
        System.arraycopy(heights, 0, newHeights, 1, n);
        // 2. 数组模拟栈：只需要一个指针 top
        // 数组大小设为 n+2 绝对够用
        int[] stack = new int[n + 2];
        int top = -1;
        int maxArea = 0;
        for (int i = 0; i < newHeights.length; i++) {
            // 用 stack[top] 代替 stack.peek()
            while (top != -1 && newHeights[i] < newHeights[stack[top]]) {
                int h = newHeights[stack[top--]]; // 出栈并获取高度
                // 此时栈顶元素就是左边界，i 是右边界
                int w = i - stack[top] - 1;
                maxArea = Math.max(maxArea, h * w);
            }
            stack[++top] = i; // 入栈
        }
        return maxArea;
    }

    public static void main(String[] args) {
        LargestRectangleArea solution = new LargestRectangleArea();
        int[] heights = {2,1,5,6,2,3};
        System.out.println(solution.largestRectangleArea2(heights));
    }
}
