package solution;

// 核心：对于任意位置 i，它能接的雨水量 = min(左侧最大高度, 右侧最大高度) - height[i]

import java.util.Deque;
import java.util.LinkedList;

public class TrapRain {
    /* 42.接雨水 */
    /* 解法一：双指针（最优）时间 O(n) 空间O(1)
       对每一列计算积水后累加 */
    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        int left = 0, right = height.length - 1;  // 左右边界指针
        int leftMax = 0, rightMax = 0;            // 记录左侧和右侧已遍历部分的最大高度
        int ans = 0;                              // 累计雨水量
        while (left < right) {
            // 总是选择较矮的一侧处理
            if (height[left] < height[right]) { // 左侧较矮，处理左边
                if (height[left] >= leftMax) {
                    // 当前高度不小于左侧最大值：不能积水，更新左侧最大值
                    leftMax = height[left];
                } else {
                    // 当前高度小于左侧最大值：可以积水，积水量 = 左侧最大值 - 当前高度
                    ans += leftMax - height[left];
                }
                left++; // 左指针右移
            }
            else { // 右侧较矮，处理右边
                if (height[right] >= rightMax) {
                    // 当前高度不小于右侧最大值：不能积水，更新右侧最大值
                    rightMax = height[right];
                }
                else {
                    // 当前高度小于右侧最大值：可以积水，积水量 = 右侧最大值 - 当前高度
                    ans += rightMax - height[right];
                }
                right--; // 右指针左移
            }
        }
        return ans;
    }
    /* 解法二：单调栈（最优）时间 O(n) 空间O(n)
       栈存下标，单调递减栈 = 从栈底到栈顶，高度越来越小
       对每一层计算积水后累加 */
    public int trap2(int[] height) {
        int ans = 0;
        Deque<Integer> stack = new LinkedList<>();
        int n = height.length;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) { // 当前高度大于栈顶高度
                int top = stack.pop(); // 弹出栈顶元素
                if (stack.isEmpty())    break; // 栈为空，表示没有左侧边界，无法积水
                int left = stack.peek(); // 获取左侧边界下标
                int currWidth = i - left - 1; // 当前宽度
                int currHeight = Math.min(height[left], height[i]) - height[top]; // 当前高度
                ans += currWidth * currHeight; // 累加雨水量
            }
            stack.push(i); // 将当前下标入栈
            System.out.println("i = " + i + ", stack = " + stack);
        }
        return ans;
    }
    /* 解法三：维护左右最大高度数组 时间 O(n) 空间O(n)
       leftMax[i]：下标 i 及其左边的位置中，height 的最大高度
       rightMax[i]：下标 i 及其右边的位置中，height 的最大高度
       对每一列计算积水后累加 */
    public int trap3(int[] height) {
        int n = height.length;
        if (n == 0) return 0;
        // leftMax[i]：下标 i 及其左边的位置中的最大高度
        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        // rightMax[i]：下标 i 及其右边的位置中的最大高度
        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        TrapRain solution = new TrapRain();
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        int result = solution.trap2(height);
        System.out.println(result);
    }
}
