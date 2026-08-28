package solution.labuladong.array.stackAndQueue;

import java.util.ArrayDeque;
import java.util.Deque;

public class IsValid {
    /* 20. 有效的括号 */
    public boolean isValid(String str) {
        Deque<Character> left = new ArrayDeque<>();
        for (char c : str.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                left.push(c); // 字符 c 是左括号，入栈
            } else { // 字符 c 是右括号
                if (!left.isEmpty() && leftOf(c) == left.peek()) { // 和最近的左括号匹配
                    left.pop();
                } else { // 和最近的左括号不匹配
                    return false;
                }
            }
        }
        return left.isEmpty(); // 所有的左括号都被匹配
    }
    char leftOf(char c) {
        if (c == '}') return '{';
        if (c == ')') return '(';
        return '[';
    }

    public static void main(String[] args) {
        IsValid isValid = new IsValid();
        System.out.println(isValid.isValid("()[]{}"));
    }
}
