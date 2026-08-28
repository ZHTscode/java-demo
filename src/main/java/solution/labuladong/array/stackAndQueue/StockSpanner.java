package solution.labuladong.array.stackAndQueue;

import java.util.ArrayDeque;
import java.util.Deque;

public class StockSpanner {
    /* 901. 股票价格跨度$$ */
    private Deque<int[]> stack; // (价格，小于等于该价格的天数)
    public StockSpanner() {
        stack = new ArrayDeque<>();
    }
    public int next(int price) {
        int res = 1;
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            res += stack.pop()[1];  // 累加小于等于该价格的天数
        }
        stack.push(new int[]{price, res}); // 将当前价格和天数入栈
        return res;
    }

    public static void main(String[] args) {
        StockSpanner solution = new StockSpanner();
        int[] prices = {100,80,60,60,60,75,85};
        for (int price : prices) {
            System.out.print(solution.next(price) + " ");
        }
    }
}
