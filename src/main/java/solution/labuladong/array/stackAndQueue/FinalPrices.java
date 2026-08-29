package solution.labuladong.array.stackAndQueue;

import java.util.ArrayDeque;
import java.util.Deque;

public class FinalPrices {
    /* 1475. 商品折扣后的最终价格 */
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] res = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() > prices[i]) { // 找到第一个小于等于当前价格的元素
                stack.pop();
            }
            // 栈为空，说明没有找到小于等于当前价格的元素，折扣为0
            res[i] = stack.isEmpty() ? prices[i] : prices[i] - stack.peek();
            stack.push(prices[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        FinalPrices solution = new FinalPrices();
        int[] prices = {8,4,6,2,3};
        int[] res = solution.finalPrices(prices);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }
}
