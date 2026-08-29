package solution.labuladong.array.stackAndQueue;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class DailyTemperatures {
    /* 739. 每日温度 */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Deque<Integer> stack = new LinkedList<>(); // 栈存下标
        for(int i=n-1; i>=0; i--){
            while(!stack.isEmpty() && temperatures[i] >= temperatures[stack.peek()]){
                stack.pop();
            }
            res[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        DailyTemperatures solution = new DailyTemperatures();
        int[] temperatures = {73,74,75,71,69,72,76,73};
        int[] res = solution.dailyTemperatures(temperatures);
        System.out.println(Arrays.toString(res));
    }
}
