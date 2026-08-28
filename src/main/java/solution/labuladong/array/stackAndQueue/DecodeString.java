package solution.labuladong.array.stackAndQueue;

import java.util.ArrayDeque;
import java.util.Deque;

public class DecodeString {
    /* 394. 字符串解码 */
    public String decodeString(String s) {
        // 栈存储 (之前的字符串, 重复次数) 的配对
        Deque<String> strStack = new ArrayDeque<>();
        Deque<Integer> cntStack = new ArrayDeque<>();
        // 当前正在构建的字符串
        StringBuilder cur = new StringBuilder();
        // 当前正在构建的数字（可能是多位数）
        int k = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                // 累积多位数字
                k = k * 10 + (c - '0');
            } else if (c == '[') {
                // 压栈，重置当前状态
                strStack.push(cur.toString());
                cntStack.push(k);
                cur = new StringBuilder();
                k = 0;
            } else if (c == ']') {
                // 弹栈，把当前串重复 k 次后拼接到之前的串
                int times = cntStack.pop();
                String prev = strStack.pop();
                StringBuilder sb = new StringBuilder(prev);
                for (int i = 0; i < times; i++) {
                    sb.append(cur);
                }
                cur = sb;
            } else {
                // 普通字符直接追加
                cur.append(c);
            }
        }
        return cur.toString();
    }

    public static void main(String[] args) {
        DecodeString solution = new DecodeString();
        String s = "3[a2[c]]";
        String result = solution.decodeString(s);
        System.out.println(result); // 输出 "accaccacc"
    }
}
