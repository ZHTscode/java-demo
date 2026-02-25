package solution;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class DailyTemperatures {
    /* 739.温度 */
    /* 解法一：暴力解法 提交超时 */
    public int[] dailyTemperatures(int[] temperatures) {
        int pre = 0;
        int cur = 0;
        int[] res = new int[temperatures.length];
        while(pre < temperatures.length){
            if(cur == temperatures.length){
                pre++;
                cur = pre;
            }
            else if(temperatures[pre] < temperatures[cur]){
                res[pre] = cur - pre;
                pre++;
                cur = pre;
            }
            else cur++;
        }
        return res;
    }
    /* 解法二：单调递减栈（栈存下标）
       栈中存储数组的下标，栈内下标的对应温度值单调递减 */
    public int[] dailyTemperatures2(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int curTemp = temperatures[i];
            while (!stack.isEmpty() && curTemp > temperatures[stack.peek()]) {
                int top = stack.pop();
                answer[top] = i - top;
            }
            stack.push(i);
        }
        return answer;
    }
    /* 解法三：单调递减栈（栈存键值对）
       栈中存储 int[]数组，数组[0]=温度值，数组[1]=数组下标
       后面元素不比前面大，前面元素留在栈中，等待更大元素出现 */
    public int[] dailyTemperatures3(int[] temperatures) {
        int len = temperatures.length;
        int[] answer = new int[len];
        Deque<int[]> stack = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            int curTemp = temperatures[i];
            while (!stack.isEmpty() && curTemp > stack.peek()[0]) {
                int[] top = stack.pop();
                answer[top[1]] = i - top[1];  // 计算间隔天数
            }
            stack.push(new int[]{curTemp, i}); // 入栈：存入数组[温度值, 下标]
        }
        return answer;
    }

    public static void main(String[] args) {
        DailyTemperatures dt = new DailyTemperatures();
        int[] temp = {73,74,75,71,69,72,76,73};
        int[] res = dt.dailyTemperatures3(temp);
        System.out.println(Arrays.toString(res));
    }
}

