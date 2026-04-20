package solution;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class DecodeString {
    /* 394. 字符串解码 */
    // 解法一：栈 + 双向队列
    public String decodeString(String s) {
        Deque<Integer> numStack = new ArrayDeque<>(); // 当前要重复的次数（数字栈）
        Deque<StringBuilder> strStack = new ArrayDeque<>(); // 当前已构建的部分字符串（字符串栈）
        StringBuilder currStr = new StringBuilder();
        int currNum = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) { // 当前字符为数字
                currNum = currNum * 10 + (c - '0');
            }
            else if (c == '[') {
                numStack.push(currNum); // 压栈：保存当前状态
                strStack.push(currStr);
                currNum = 0; // 重置
                currStr = new StringBuilder(); // 重置
            }
            else if (c == ']') {
                int k = numStack.pop(); // 弹栈：重复当前字符串
                StringBuilder prevStr = strStack.pop(); // 弹栈：获取之前构建的部分字符串
                for (int i = 0; i < k; i++) {
                    prevStr.append(currStr);
                }
                currStr = prevStr;
            }
            else {
                currStr.append(c);
            }
        }
        return currStr.toString();
    }
    // 解法二：递归
    int index = 0; // 全局指针
    public String decodeString2(String s) {
        return dfs(s).toString();
    }
    private StringBuilder dfs(String s) {
        StringBuilder res = new StringBuilder();
        int num = 0;
        while (index < s.length()) {
            char c = s.charAt(index); // 获取指定索引的字符
            if (Character.isDigit(c))
                num = num * 10 + (c - '0');
            else if (c == '[') {
                index++; // 跳过 '['
                StringBuilder sub = dfs(s); // 递归处理子串
                res.append(String.valueOf(sub).repeat(Math.max(0, num)));
                num = 0; // 重置
            }
            else if (c == ']')
                return res; // 结束当前层
            else
                res.append(c);
            index++;
        }
        index = 0; // 重置全局指针
        return res;
    }

    public static void main(String[] args) {
        DecodeString ds = new DecodeString();
        System.out.println(ds.decodeString2("3[a]2[bc]"));
        System.out.println(ds.decodeString2("3[a2[c]]"));
    }
}
