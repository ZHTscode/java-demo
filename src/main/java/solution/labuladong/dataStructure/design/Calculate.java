package solution.labuladong.dataStructure.design;

import java.util.*;

public class Calculate {
    /* 224. 基本计算器 */
    public int calculate(String s) {
        // key 是左括号的索引，value 是对应的右括号的索引
        Map<Integer, Integer> rightIndex = new HashMap<>();
        // 用栈处理嵌套括号
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                rightIndex.put(stack.pop(), i); // （左括号索引，右括号索引）
            }
        }
        return _calculate(s, 0, s.length() - 1, rightIndex);
    }

    // 定义：返回 s[start..end] 内的表达式的计算结果
    private int _calculate(String s, int start, int end, Map<Integer, Integer> rightIndex) {
        // 需要把字符串转成双端队列方便操作
        Deque<Integer> stk = new ArrayDeque<>();
        // 记录算式中的数字
        int num = 0;
        // 记录 num 前的符号，初始化为 +
        char sign = '+';
        for (int i = start; i <= end; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = 10 * num + (c - '0');
            }
            if (c == '(') {
                // 递归计算括号内的表达式
                num = _calculate(s, i + 1, rightIndex.get(i) - 1, rightIndex);
                i = rightIndex.get(i); // 括号内已计算，跳过整个括号
            }
            if (c == '+' || c == '-' || c == '*' || c == '/' || i == end) {
                int pre;
                switch (sign) {
                    case '+':
                        stk.push(num);
                        break;
                    case '-':
                        stk.push(-num);
                        break;
                    // 只要拿出前一个数字做对应运算即可
                    case '*':
                        pre = stk.pop();
                        stk.push(pre * num);
                        break;
                    case '/':
                        pre = stk.pop();
                        stk.push(pre / num);
                        break;
                }
                // 更新符号为当前符号，数字清零
                sign = c;
                num = 0;
            }
        }
        // 将栈中所有结果求和就是答案
        int res = 0;
        while (!stk.isEmpty()) {
            res += stk.pop();
        }
        return res;
    }

    public static void main(String[] args) {
        Calculate calculate = new Calculate();
        System.out.println(calculate.calculate("-1 + 1"));
    }
}