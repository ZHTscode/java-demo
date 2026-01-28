package solution;

import java.util.Deque;
import java.util.LinkedList;

public class DailyTemperatures {
    // 暴力解法 超时
    /*public static int[] dailyTemperatures(int[] temperatures) {
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
    }*/

    // 最优解：单调递减栈
    //维护一个单调递减栈，栈中存储的是数组的下标，栈内下标的对应温度值严格单调递减
    /*public static int[] dailyTemperatures(int[] temperatures) {
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
*/
    // 栈存键值对KV
    //栈中存储 int[]数组，数组[0]=温度值，数组[1]=数组下标
    // 后面元素不比前面大，前面元素留在栈里，等待更大元素出现
    public static int[] dailyTemperatures(int[] temperatures) {
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

    // 测试main方法
    public static void main(String[] args) {
        // 测试用例1：力扣官方示例
        int[] temp1 = {73,74,75,71,69,72,76,73};
        int[] res1 = dailyTemperatures(temp1);
        System.out.print("测试用例1结果：");
        printArr(res1); // 输出 [1,1,4,2,1,1,0,0]

        // 测试用例2：严格递减数组（无更高温度）
        int[] temp2 = {55,50,45,40,35};
        int[] res2 = dailyTemperatures(temp2);
        System.out.print("测试用例2结果：");
        printArr(res2); // 输出 [0,0,0,0,0]

        // 测试用例3：严格递增数组
        int[] temp3 = {10,20,30,40,50};
        int[] res3 = dailyTemperatures(temp3);
        System.out.print("测试用例3结果：");
        printArr(res3); // 输出 [1,1,1,1,0]

        // 测试用例4：单个元素
        int[] temp4 = {30};
        int[] res4 = dailyTemperatures(temp4);
        System.out.print("测试用例4结果：");
        printArr(res4); // 输出 [0]
    }

    // 辅助打印数组的方法
    private static void printArr(int[] arr) {
        for (int num : arr) { // 遍历 int[] 数组 arr，每次循环把数组的当前元素值赋值给变量 num
            System.out.print(num + " ");
        }
        System.out.println();
    }
}

