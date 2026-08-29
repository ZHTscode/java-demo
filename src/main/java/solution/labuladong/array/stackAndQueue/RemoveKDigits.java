package solution.labuladong.array.stackAndQueue;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveKDigits {
    /* 402. 移掉 K 位数字$$ */
    public String removeKdigits(String num, int k) {
        Deque<Character> stk = new ArrayDeque<>();
        for (char c : num.toCharArray()) {
            // 单调栈代码模板
            while (!stk.isEmpty() && c < stk.peek() && k > 0) { // 当前字符比栈顶元素小
                stk.pop(); // 栈顶元素出栈
                k--;
            }
            // 防止 0 作为数字的开头
            if (stk.isEmpty() && c == '0') {
                continue;
            }
            stk.push(c);
        }
        // 此时栈中元素单调递增，若 k 还没用完的话删掉栈顶元素
        while (k > 0 && !stk.isEmpty()) {
            stk.pop();
            k--;
        }
        // 若最后没剩下数字，就是 0
        if (stk.isEmpty()) {
            return "0";
        }
        // 将栈中字符转化成字符串
        StringBuilder sb = new StringBuilder();
        while (!stk.isEmpty()) {
            sb.append(stk.pop());
        }
        // 出栈顺序和字符串顺序是反的
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        RemoveKDigits solution = new RemoveKDigits();
        String num = "412305";
        int k = 3;
        System.out.println(solution.removeKdigits(num, k));
    }
}
