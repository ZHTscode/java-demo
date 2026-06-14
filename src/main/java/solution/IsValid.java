package solution;

import java.util.Deque;
import java.util.LinkedList;

public class IsValid {
    /* 20. 有效的括号 */
    public boolean isValid(String s) {
        Deque<Character> stack = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '[') stack.push(']');
            else if (c == '{') stack.push('}');
            else if (stack.isEmpty() || stack.pop() != c) return false;
            System.out.println(stack);
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        IsValid isValid = new IsValid();
        System.out.println(isValid.isValid("()[]{}"));
    }
}
