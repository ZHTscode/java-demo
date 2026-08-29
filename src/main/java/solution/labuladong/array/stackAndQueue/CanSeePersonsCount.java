package solution.labuladong.array.stackAndQueue;

import java.util.ArrayDeque;
import java.util.Deque;

public class CanSeePersonsCount {
    /* 1944. 队列中可以看到的人数$$ */
    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        int[] res = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        // 从右向左
        for (int i = n - 1; i >= 0; i--) {
            int count = 0; // 每次循环开始置零
            // 把所有比当前矮的全部弹出，这些是能看见的
            while (!stack.isEmpty() && stack.peek() < heights[i]) {
                stack.pop();
                count++;
            }
            // 栈为空，自己就是最高的
            // 栈不为空：还能看见第一个比自己高的那个人，要+1
            if (!stack.isEmpty()) {
                count++;
            }
            res[i] = count;
            stack.push(heights[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        CanSeePersonsCount solution = new CanSeePersonsCount();
        int[] heights = {10,6,8,5,11,9};
        int[] res = solution.canSeePersonsCount(heights);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }
}
